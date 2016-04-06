package com.example.asus.newsdemo1.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asus.newsdemo1.Base.BaseActivity;
import com.example.asus.newsdemo1.Presenter.ContentFragmentAdapter;
import com.example.asus.newsdemo1.R;
import com.example.asus.newsdemo1.View.ContentFragment;
import com.example.asus.newsdemo1.View.EntertainmentFragment;
import com.example.asus.newsdemo1.View.HeadlineFragment;
import com.example.asus.newsdemo1.View.JokeFragment;
import com.example.asus.newsdemo1.View.MilitaryFragment;
import com.example.asus.newsdemo1.View.PhoneFragment;
import com.example.asus.newsdemo1.View.ScienceFragment;
import com.example.asus.newsdemo1.View.SocialFragment;
import com.example.asus.newsdemo1.View.SportFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private FloatingActionButton floatingActionButton;
    private TabLayout tabsLayout;
    private ViewPager viewPager;
    private HeadlineFragment fragmentHeadline;
    private SportFragment fragmentSport;
    private EntertainmentFragment fragmentEntertainment;
    private ScienceFragment fragmentScience;
    private JokeFragment fragmentJoke;
    private PhoneFragment fragmentPhone;
    private SocialFragment fragmentSocial;
    private MilitaryFragment fragmentMilitary;
    public static ProgressDialog progressDialogLoading;

    private ContentFragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MainActivity.this);
        inits();
        progressDialogLoading.setCancelable(false);
        progressDialogLoading.show();
    }

    @Override
    public void setContentView() {
        this.setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMainAty);
        setSupportActionBar(toolbar);
    }

    @Override
    public void findViews() {
        tabsLayout= (TabLayout) this.findViewById(R.id.tabsLayoutContent);
        viewPager= (ViewPager) this.findViewById(R.id.viewPagerContent);
        progressDialogLoading = new ProgressDialog(MainActivity.this);
        Toast.makeText(MainActivity.this, "正在刷新最新新闻，请稍等...", Toast.LENGTH_LONG).show();
    }

    @Override
    public void getData() {
    }

    @Override
    public void bindData() {
        fragmentHeadline=new HeadlineFragment();
        fragmentSport=new SportFragment();
        fragmentEntertainment=new EntertainmentFragment();
        fragmentScience=new ScienceFragment();
        fragmentJoke=new JokeFragment();
        fragmentPhone=new PhoneFragment();
        fragmentSocial=new SocialFragment();
        fragmentMilitary=new MilitaryFragment();
        List<String> titles=new ArrayList<>();
        titles.add("头条");
        titles.add("娱乐");
        titles.add("科技");
        titles.add("体育");
        titles.add("手机");
        titles.add("社会");
        titles.add("军事");
        titles.add("笑话");
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(fragmentHeadline);
        fragments.add(fragmentEntertainment);
        fragments.add(fragmentScience);
        fragments.add(fragmentSport);
        fragments.add(fragmentPhone);
        fragments.add(fragmentSocial);
        fragments.add(fragmentMilitary);
        fragments.add(fragmentJoke);
        fragmentAdapter=new ContentFragmentAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(fragments.size()-1);
        tabsLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//

        }
    }
}
