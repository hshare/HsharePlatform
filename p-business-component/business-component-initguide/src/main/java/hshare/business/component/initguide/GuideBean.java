package hshare.business.component.initguide;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import hshare.base.component.view.indicator.spring.SpringIndicator;

/**
 * Created by hcare on 2018/4/2.
 */

public class GuideBean {
    private FragmentManager fManager;
    private ViewPager viewPager;
    private SpringIndicator springIndicator;
    private String[] titles;
    private int[] res;
    private OnPageClickListener onPageClickListener;

    public GuideBean(FragmentManager fManager, ViewPager viewPager,
                     SpringIndicator springIndicator, String[] titles, int[] res) {
        this.fManager = fManager;
        this.viewPager = viewPager;
        this.springIndicator = springIndicator;
        this.titles = titles;
        this.res = res;
    }

    public static void checkIsNull(GuideBean guideBean) {
        if (guideBean == null || guideBean.checkIsNull()) {
            throw new IllegalStateException("GuideBean should be initialized fully!");
        }
    }

    public boolean checkIsNull() {
        if (fManager == null) {
            return true;
        }
        if (viewPager == null) {
            return true;
        }
        if (springIndicator == null) {
            return true;
        }
        if (onPageClickListener == null) {
            return true;
        }
        if (titles == null || titles.length == 0) {
            return true;
        }
        if (res == null || res.length == 0) {
            return true;
        }
        if (res.length != titles.length) {
            return true;
        }
        return false;
    }

    public FragmentManager getfManager() {
        return fManager;
    }

    public void setfManager(FragmentManager fManager) {
        this.fManager = fManager;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public SpringIndicator getSpringIndicator() {
        return springIndicator;
    }

    public void setSpringIndicator(SpringIndicator springIndicator) {
        this.springIndicator = springIndicator;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public int[] getRes() {
        return res;
    }

    public void setRes(int[] res) {
        this.res = res;
    }

    public OnPageClickListener getOnPageClickListener() {
        return onPageClickListener;
    }

    public void setOnPageClickListener(OnPageClickListener onPageClickListener) {
        this.onPageClickListener = onPageClickListener;
    }
}
