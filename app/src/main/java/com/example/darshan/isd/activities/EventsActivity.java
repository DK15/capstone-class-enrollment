package com.example.darshan.isd.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.darshan.isd.models.Events;
import com.example.darshan.isd.adapters.EventsAdapter;
import com.example.darshan.isd.R;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        List<Events> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(EventsActivity.this);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        EventsAdapter rcAdapter = new EventsAdapter(EventsActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<Events> getAllItemList() {

        List<Events> allItems = new ArrayList<Events>();
        allItems.add(new Events(getString(R.string.event1), getString(R.string.event_dt)));
        allItems.add(new Events(getString(R.string.event2), getString(R.string.event_dt)));
        allItems.add(new Events(getString(R.string.event3), getString(R.string.event_dt)));

        return allItems;
    }
}
