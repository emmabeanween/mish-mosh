package com.example.emma.mishmosh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emma on 7/12/17.
 */

public class SingleAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments = new ArrayList<>();
    List<String> mTitles = new ArrayList<>();


    public SingleAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragments(Fragment fragment, String title){

        mFragments.add(fragment);
        mTitles.add(title);



     }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
