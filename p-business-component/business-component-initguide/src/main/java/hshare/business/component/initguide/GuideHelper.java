package hshare.business.component.initguide;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import hshare.base.component.general.sp.SPUtils;
import hshare.base.component.view.indicator.spring.SpringIndicator;
import hshare.base.component.view.viewpager.adapter.NormalViewPageAdapter;
import hshare.base.component.view.viewpager.base.NormalViewPageBaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for GuideFragment
 *
 * @author hzl
 */
public class GuideHelper {

    private int stateCount;
    private static final String TABLE_WELCOME_GUIDE = "table_welcome_guide";
    private static final String KEY_IS_FIRST_START = "table_welcome_guide";

    private Context context;
    private GuideBean guideBean;
    private WelcomeImageBean welcomeImageBean;

    private boolean isFirstStart() {
        return (boolean) SPUtils.get(context, TABLE_WELCOME_GUIDE, KEY_IS_FIRST_START, true);
    }

    private void setFirstStart() {
        SPUtils.put(context, TABLE_WELCOME_GUIDE, KEY_IS_FIRST_START, false);
    }

    public static GuideHelper getInstance() {
        return new GuideHelper();
    }

    public void init(Context context, final WelcomeImageBean welcomeImageBean, final GuideBean guideBean) {
        this.context = context;
        this.welcomeImageBean = welcomeImageBean;
        this.guideBean = guideBean;
        if (isFirstStart()) {
            if (guideBean.getOnPageClickListener() == null) {
                guideBean.setOnPageClickListener(new OnPageClickListener() {
                    @Override
                    public void onPageClick(int pageNo, int pageSum) {

                    }

                    @Override
                    public void onFinish(boolean isFinish) {
                        if (isFinish){
                            initWelcome();
                        }
                    }
                });
            }
            initGuide();
        } else {
            initWelcome();
        }

    }

    public void initWelcome() {
        setFirstStart();
        WelcomeFragment welcomeFragment = WelcomeFragment.newInstance(welcomeImageBean);
        FragmentTransaction fragmentTransaction = guideBean.getfManager().beginTransaction();
        fragmentTransaction.replace(welcomeImageBean.getReplaceRes(), welcomeFragment);
        fragmentTransaction.commit();
        if (welcomeImageBean.getOnGuideFinishListener() != null){
            welcomeImageBean.getOnGuideFinishListener().onGuideFinished();
        }
    }

    public void initGuide() {
        GuideBean.checkIsNull(guideBean);
        List<NormalViewPageBaseBean> list = new ArrayList<>();
        for (int i = 0; i < guideBean.getRes().length; i++) {
            list.add(new NormalViewPageBaseBean(guideBean.getTitles()[i], guideBean.getRes()[i], i, guideBean.getRes().length));
        }
        final NormalViewPageAdapter adapter = new NormalViewPageAdapter(guideBean.getfManager(), list, GuideFragment.class);
        final List<Fragment> fragments = adapter.getFragments();
        for (Fragment fragment : fragments) {
            ((GuideFragment) fragment).setOnPageClickListener(guideBean.getOnPageClickListener());
        }
        guideBean.getViewPager().setOffscreenPageLimit(guideBean.getTitles().length);
        guideBean.getViewPager().addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < fragments.size(); i++) {
                    ((GuideFragment) fragments.get(i)).onPageChanged(position, i);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int currentItem = guideBean.getViewPager().getCurrentItem();
                if (state == 1)
                    stateCount = 1;
                else
                    stateCount++;
                if (currentItem == adapter.getCount() - 1) {
                    if (guideBean.getOnPageClickListener() != null) {
                        if (state == 0 && stateCount == 2) {
                            guideBean.getOnPageClickListener().onFinish(true);
                        } else {
                            guideBean.getOnPageClickListener().onFinish(false);
                        }
                    } else {
                    }

                }
            }
        });
        guideBean.getViewPager().setAdapter(adapter);
        guideBean.getSpringIndicator().setViewPager(guideBean.getViewPager());
    }

}
