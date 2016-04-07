package com.njfarrell.validictest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.njfarrell.validictest.data.DataEvent;
import com.njfarrell.validictest.data.TrendAsyncTask;
import com.njfarrell.validictest.data.models.Item;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomRecyclerAdapter customAdapter;
    private List<Item> data;
    private List<TrendAsyncTask> tasks;
    private String[] locations = {"Boston", "San+Francisco", "Los+Angeles", "Denver", "Boulder",
            "Chicago", "New+York"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        customAdapter = new CustomRecyclerAdapter();
        if (recyclerView != null) {
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        tasks = new ArrayList<>();
        for (String location : locations) {
            TrendAsyncTask task = new TrendAsyncTask(location);
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            tasks.add(task);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        for (TrendAsyncTask task : tasks) {
            task.cancel(true);
        }
        super.onStop();
    }

    /**
     * Handle data event callback from async task.
     */
    @Subscribe
    public void onDataEvent(DataEvent event) {
        List<Item> itemList = event.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll(itemList);
        customAdapter.updateData(data);
    }
}
