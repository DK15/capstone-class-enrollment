package com.example.darshan.isd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.darshan.isd.models.Hub;
import com.example.darshan.isd.R;
import com.example.darshan.isd.adapters.RecyclerViewAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HubActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private LinearLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        List<Hub> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(HubActivity.this);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(HubActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_new_quote) {
            Intent enrolledIntent = new Intent(this, EnrolledActivity.class);
            startActivity(enrolledIntent);
            //  return true;
        }
        if (id == R.id.menu_item_signout) {
            mAuth.signOut();
            Intent signOutIntent = new Intent(this, MainActivity.class);
            startActivity(signOutIntent);
            Toast.makeText(this, getString(R.string.logout), Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Hub> getAllItemList() {

        List<Hub> allItems = new ArrayList<Hub>();
        allItems.add(new Hub(getString(R.string.hub1)));
        allItems.add(new Hub(getString(R.string.hub2)));
        allItems.add(new Hub(getString(R.string.hub3)));
        allItems.add(new Hub(getString(R.string.hub4)));
        allItems.add(new Hub(getString(R.string.hub5)));
        allItems.add(new Hub(getString(R.string.hub6)));

        return allItems;
    }
}
