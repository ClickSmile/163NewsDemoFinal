package com.example.asus.newsdemo1.Activities;

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
import com.example.asus.newsdemo1.Base.BaseActivity;
import com.example.asus.newsdemo1.Presenter.ContentFragmentAdapter;
import com.example.asus.newsdemo1.R;
import com.example.asus.newsdemo1.View.ContentFragment;
import com.example.asus.newsdemo1.View.HeadlineFragment;
import com.example.asus.newsdemo1.View.SportFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private FloatingActionButton floatingActionButton;
    private TabLayout tabsLayout;
    private ViewPager viewPager;
    private HeadlineFragment fragment1;
    private SportFragment fragment2;
    private ContentFragment fragment3;
    private ContentFragment fragment4;
    private ContentFragment fragment5;
    private ContentFragment fragment6;
    private ContentFragment fragment7;
    private ContentFragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MainActivity.this);
        inits();
    }

    @Override
    public void setContentView() {
        this.setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMainAty);
        setSupportActionBar(toolbar);
    }

    @Override
    public void findViews() {
        floatingActionButton= (FloatingActionButton) this.findViewById(R.id.fab);
        tabsLayout= (TabLayout) this.findViewById(R.id.tabsLayoutContent);
        viewPager= (ViewPager) this.findViewById(R.id.viewPagerContent);
    }

    @Override
    public void getData() {
    }

    @Override
    public void bindData() {
        fragment1=new HeadlineFragment();
        fragment2=new SportFragment();
        fragment3=new ContentFragment();
        fragment4=new ContentFragment();
        fragment5=new ContentFragment();
        fragment6=new ContentFragment();
        fragment7=new ContentFragment();
        floatingActionButton.setOnClickListener(this);
        List<String> titles=new ArrayList<>();
        titles.add("头条");
        titles.add("体育");
        titles.add("第三页");
        titles.add("第四页");
        titles.add("第五页");
        titles.add("第六页");
        titles.add("第七页");
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        fragments.add(fragment6);
        fragments.add(fragment7);
        fragmentAdapter=new ContentFragmentAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(fragmentAdapter);
        viewPager .setOffscreenPageLimit(titles.size()-1);
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
            case R.id.fab:
                Snackbar.make(floatingActionButton,"FloatingButton Click",Snackbar.LENGTH_SHORT)
                        .setAction("I Konw",null).show();
                break;

        }
    }
}
