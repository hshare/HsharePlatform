package hshare.business.module.read.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import hshare.base.component.view.viewpager.adapter.NormalViewPageAdapter;
import hshare.base.component.view.viewpager.base.NormalViewPageBaseBean;
import hshare.business.module.read.R;
import hshare.business.module.read.bean.Constants;
import hshare.business.module.read.ui.fragment.ReadMainFragment;

public class ReadMainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity_main);

        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewpager);


        List<NormalViewPageBaseBean> list = new ArrayList<>();

        String[] titles = getResources().getStringArray(R.array.read_duwenzhang_configs_titles);

        for (int i = 0; i < titles.length; i++) {

            mTabLayout.addTab(mTabLayout.newTab().setText(titles[i]));
            list.add(new NormalViewPageBaseBean(titles[i],
                    getResources().getStringArray(R.array.read_duwenzhang_configs_urls)[i],
                    getResources().getStringArray(R.array.read_duwenzhang_configs_keys)[i]));
        }
        NormalViewPageAdapter adapter = new NormalViewPageAdapter(getSupportFragmentManager(), list, ReadMainFragment.class);
        mViewPager.setOffscreenPageLimit(titles.length);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }
}
