package com.example.asus.newsdemo1.Presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.asus.newsdemo1.R;
import java.util.List;

/**
 * Created by ASUS on 2016/4/1.
 */
public class ContentFragmentAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragments;
    private List<String> titles;

    public ContentFragmentAdapter(FragmentManager fm, List<String> titles1, List<Fragment> fragments1) {
        super(fm);
        fragments=fragments1;
        titles=titles1;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
