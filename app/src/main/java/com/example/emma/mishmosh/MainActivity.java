package com.example.emma.mishmosh;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpAdapter();

    }



        public void setUpAdapter() {

         SingleAdapter mApdapter = new SingleAdapter(getSupportFragmentManager());
         // populate the adapter
         mApdapter.addFragments(new FragmentOne(), "World Times");
         mApdapter.addFragments(new FragmentTwo(), "Timer");
         mApdapter.addFragments(new FragmentThree(), "BMI");



         // find the viewpager and attach it to the adapter

            ViewPager mPager = (ViewPager)findViewById(R.id.container);
            mPager.setAdapter(mApdapter);



        // find the tabbed layout and attach it to the viewpager, which holds the fragments


            TabLayout mLayout = (TabLayout)findViewById(R.id.tabs);
            mLayout.setupWithViewPager(mPager);

    }





}
