package com.example.darshan.isd.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.darshan.isd.R;
import com.example.darshan.isd.adapters.AchAdapter;
import com.example.darshan.isd.models.Achievements;

import java.util.ArrayList;
import java.util.List;

public class AchieveActivity extends AppCompatActivity {

    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve);


        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        List<Achievements> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(AchieveActivity.this);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        AchAdapter rcAdapter = new AchAdapter(AchieveActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<Achievements> getAllItemList() {

        List<Achievements> allItems = new ArrayList<Achievements>();
        allItems.add(new Achievements(getString(R.string.achieveOne)));
        allItems.add(new Achievements(getString(R.string.achieveTwo)));
        allItems.add(new Achievements(getString(R.string.achieveThree)));
        allItems.add(new Achievements(getString(R.string.achieveFour)));
        //   allItems.add(new Achievements(getString(R.string.achieveFive)));
        allItems.add(new Achievements(getString(R.string.achieveSix)));

        return allItems;
    }
}
