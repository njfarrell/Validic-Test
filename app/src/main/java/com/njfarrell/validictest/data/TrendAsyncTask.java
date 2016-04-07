package com.njfarrell.validictest.data;

import android.os.AsyncTask;

import com.njfarrell.validictest.data.models.HeaderItem;
import com.njfarrell.validictest.data.models.Item;
import com.njfarrell.validictest.data.models.ListItem;
import com.njfarrell.validictest.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TrendAsyncTask extends AsyncTask<Void, Void, List<Item>> {

    private final String location;
    private final String[] languages = {"Python", "Ruby", "C++", "C", "Node", "Scala"};

    public TrendAsyncTask(String location) {
        this.location = location;
    }

    @Override
    protected List<Item> doInBackground(Void... params) {
        List<Item> itemList = null;
        int locationJobs = getJobCount(
                String.format("https://jobs.github.com/positions.json?location=%s",
                        location));
        if (locationJobs > 0) {
            itemList = new ArrayList<>();
            itemList.add(new HeaderItem(location.replace("+", " ")));

            for (String language : languages) {
                int languageJobs = getJobCount(String.format(
                        "https://jobs.github.com/positions.json?location=%s&description=%s",
                        location,
                        language));
                if (languageJobs > 0) {
                    int percentTrend = Utils.getPercentage(languageJobs,
                            locationJobs);
                    itemList.add(new ListItem("- " + language + ": " + percentTrend + "%"));
                }
            }
            if (itemList.size() == 1) {
                return null;
            }
        }

        return itemList;
    }

    @Override
    protected void onPostExecute(List<Item> itemList) {
        if (itemList != null && itemList.size() > 0) {
            EventBus.getDefault().post(new DataEvent(itemList));
        }
    }

    private int getJobCount(String urlString) {
        // TODO: Make sure to check for pagination
        JSONArray jobs = httpRequest(urlString);
        if (jobs == null) {
            return 0;
        }
        return jobs.length();
    }

    private JSONArray httpRequest(String urlString) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int status = connection.getResponseCode();

            switch (status) {
                case 200:
                    String response = Utils.readInputStream(connection.getInputStream());
                    return new JSONArray(response);
            }

        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
}
