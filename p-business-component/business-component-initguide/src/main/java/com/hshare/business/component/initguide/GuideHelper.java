package com.hshare.business.component.initguide;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.hshare.base.component.view.indicator.spring.SpringIndicator;
import com.hshare.base.component.view.viewpager.adapter.NormalViewPageAdapter;
import com.hshare.base.component.view.viewpager.base.NormalViewPageBaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for GuideFragment
 *
 * @author hzl
 */
public class GuideHelper {

    private int stateCount;

    public static GuideHelper getInstance() {
        return new GuideHelper();
    }

    public void init(FragmentManager fManager, final ViewPager viewPager,
                     SpringIndicator springIndicator, String[] titles, int[] res, final OnPageClickListener onPageClickListener) {
        List<NormalViewPageBaseBean> list = new ArrayList<>();
        for (int i = 0; i < res.length; i++) {
            list.add(new NormalViewPageBaseBean(titles[i], res[i], i, res.length));
        }
        final NormalViewPageAdapter adapter = new NormalViewPageAdapter(fManager, list, GuideFragment.class);
        final List<Fragment> fragments = adapter.getFragments();
        for (Fragment fragment : fragments) {
            ((GuideFragment) fragment).setOnPageClickListener(onPageClickListener);
        }
        viewPager.setOffscreenPageLimit(titles.length);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < fragments.size(); i++) {
                    ((GuideFragment) fragments.get(i)).onPageChanged(position,i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int currentItem = viewPager.getCurrentItem();
                if (state == 1)
                    stateCount = 1;
                else
                    stateCount++;
                if (currentItem == adapter.getCount() - 1) {
                    if (onPageClickListener != null){
                        if (state == 0 && stateCount == 2) {
                            onPageClickListener.onFinish(true);
                        } else {
                            onPageClickListener.onFinish(false);
                        }
                    }else {
                    }

                }
            }
        });
        viewPager.setAdapter(adapter);
        springIndicator.setViewPager(viewPager);
    }

}
