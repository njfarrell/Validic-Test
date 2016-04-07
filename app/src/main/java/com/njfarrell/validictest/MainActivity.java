package com.njfarrell.validictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.njfarrell.validictest.data.HeaderItem;
import com.njfarrell.validictest.data.Item;
import com.njfarrell.validictest.data.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomRecyclerAdapter customAdapter;

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

        List<Item> data = new ArrayList<>();
        data.add(new HeaderItem("Boston"));
        data.add(new ListItem("- Python: 25%"));
        data.add(new ListItem("- Ruby: 10%"));
        data.add(new ListItem("- C++: 10%"));
        data.add(new ListItem("- C: 5%"));
        data.add(new HeaderItem("San Francisco"));
        data.add(new ListItem("- Node: 35%"));
        data.add(new ListItem("- Scala: 10%"));
        data.add(new ListItem("- Ruby: 15%"));
        customAdapter.updateData(data);
    }
}
