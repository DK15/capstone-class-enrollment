package com.example.darshan.isd.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.darshan.isd.R;
import com.example.darshan.isd.utils.utils;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.fb_link_about)
    TextView fb_textView;
    @BindView(R.id.isdm_logo1)
    ImageView logo;
    @BindView(R.id.profile_image)
    ImageView founder_image;
    @BindView(R.id.profile_image1)
    ImageView founder_image1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.isdm_logo1).into(logo);
        Picasso.with(this).load(R.drawable.founder_profile).into(founder_image);
        Picasso.with(this).load(R.drawable.founder_profile1).into(founder_image1);


        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        fb_textView = (TextView) findViewById(R.id.fb_link_about);

        fb_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = utils.openFaceBookUrl(view.getContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
    }
}
