package com.example.darshan.isd.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.darshan.isd.adapters.ClassAdapter;
import com.example.darshan.isd.models.ClassList;
import com.example.darshan.isd.R;

import java.util.ArrayList;
import java.util.List;

public class ClassDetailsActivity extends AppCompatActivity {

    private GridLayoutManager lLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        List<ClassList> classListItem = getAllItemList();
        lLayout = new GridLayoutManager(ClassDetailsActivity.this, 2);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        ClassAdapter rcAdapter = new ClassAdapter(ClassDetailsActivity.this, classListItem);
        rView.setAdapter(rcAdapter);

    }

    private List<ClassList> getAllItemList() {

        List<ClassList> allItems = new ArrayList<ClassList>();
        allItems.add(new ClassList(getString(R.string.bharatnatyam)));
        allItems.add(new ClassList(getString(R.string.kathak)));
        allItems.add(new ClassList(getString(R.string.zumba)));
        allItems.add(new ClassList(getString(R.string.yoga)));
        allItems.add(new ClassList(getString(R.string.tabla)));
        allItems.add(new ClassList(getString(R.string.keyboard)));
        allItems.add(new ClassList(getString(R.string.drawing)));
        allItems.add(new ClassList(getString(R.string.guitar)));
        allItems.add(new ClassList(getString(R.string.chess)));
        allItems.add(new ClassList(getString(R.string.handwriting)));

        return allItems;
    }
}

