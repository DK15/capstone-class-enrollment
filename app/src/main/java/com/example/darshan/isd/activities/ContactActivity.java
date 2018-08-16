package com.example.darshan.isd.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.darshan.isd.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.email_tv)
    TextView email_textView;
    @BindView(R.id.ph_tv)
    TextView phone_textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);

        email_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(getString(R.string.emailTo)));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailSub));
                startActivity(emailIntent);
            }
        });

        phone_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(getString(R.string.ph)));
                startActivity(phoneIntent);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng mumbai = new LatLng(19.155576, 72.884959);
        googleMap.addMarker(new MarkerOptions().position(mumbai).title(getString(R.string.map_addr)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mumbai));
        googleMap.setMinZoomPreference(R.integer.minZoom);
        googleMap.setMaxZoomPreference(R.integer.maxZoom);

    }


}
