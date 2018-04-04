package hshare.business.module.read;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import hshare.base.component.view.indicator.spring.SpringIndicator;
import hshare.base.component.view.viewpager.ScrollerViewPager;
import hshare.base.component.view.viewpager.adapter.NormalViewPageAdapter;
import hshare.base.component.view.viewpager.base.NormalViewPageBaseBean;
import hshare.business.component.initguide.GuideBean;
import hshare.business.component.initguide.GuideFragment;
import hshare.business.component.initguide.GuideHelper;
import hshare.business.component.initguide.WelcomeImageBean;
import hshare.business.module.read.bean.Constants;

public class ReadMainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private static Activity activity;
    public static Activity getThis() {
        return activity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity_main);
        activity = this;
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewpager);


        List<NormalViewPageBaseBean> list = new ArrayList<>();

        for (int i = 0; i < Constants.READ_TABS.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(Constants.READ_TABS[i]));
            list.add(new NormalViewPageBaseBean(Constants.READ_TABS[i], Constants.READ_URL[i],Constants.READ_KEYS[i]));
        }
        NormalViewPageAdapter adapter = new NormalViewPageAdapter(getSupportFragmentManager(), list, ReadMainFragment.class);
        mViewPager.setOffscreenPageLimit(Constants.READ_TABS.length);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }
}
