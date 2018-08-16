package com.example.darshan.isd.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.darshan.isd.fragments.FragmentEight;
import com.example.darshan.isd.fragments.FragmentFive;
import com.example.darshan.isd.fragments.FragmentFour;
import com.example.darshan.isd.fragments.FragmentNine;
import com.example.darshan.isd.fragments.FragmentOne;
import com.example.darshan.isd.R;
import com.example.darshan.isd.fragments.FragmentSeven;
import com.example.darshan.isd.fragments.FragmentSix;
import com.example.darshan.isd.fragments.FragmentTen;
import com.example.darshan.isd.fragments.FragmentThree;
import com.example.darshan.isd.fragments.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class PageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_pageview);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        MyPageAdapter myPageAdapter = new MyPageAdapter(getSupportFragmentManager());


        myPageAdapter.addFragment(new FragmentOne());
        myPageAdapter.addFragment(new FragmentTwo());
        myPageAdapter.addFragment(new FragmentThree());
        myPageAdapter.addFragment(new FragmentFour());
        myPageAdapter.addFragment(new FragmentFive());
        myPageAdapter.addFragment(new FragmentSix());
        myPageAdapter.addFragment(new FragmentSeven());
        myPageAdapter.addFragment(new FragmentEight());
        myPageAdapter.addFragment(new FragmentNine());
        myPageAdapter.addFragment(new FragmentTen());

        viewPager.setAdapter(myPageAdapter);
    }

    class MyPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        public MyPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {

            mFragmentList.add(fragment);
        }
    }
}
