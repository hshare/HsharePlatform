package hshare.business.module.read.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;

import hshare.base.component.view.indicator.spring.SpringIndicator;
import hshare.base.component.view.statusbar.StatusBarUtil;
import hshare.base.component.view.viewpager.ScrollerViewPager;
import hshare.business.component.initguide.GuideBean;
import hshare.business.component.initguide.GuideHelper;
import hshare.business.component.initguide.OnGuideFinishListener;
import hshare.business.component.initguide.WelcomeImageBean;
import hshare.business.module.read.R;

@Route(path = "/read/init")
public class ReadInitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity_init);

        StatusBarUtil.setTranslucent(this,255);

        WelcomeImageBean welcomeImageBean = new WelcomeImageBean(R.id.rlRoot,R.mipmap.read_welcome_default);
        welcomeImageBean.setOnGuideFinishListener(new OnGuideFinishListener() {
            @Override
            public void onGuideFinished() {
                handler.sendEmptyMessageDelayed(100,2500);
            }
        });
        GuideBean guideBean = new GuideBean(getSupportFragmentManager()
                , (ScrollerViewPager) findViewById(R.id.view_pager)
                , (SpringIndicator) findViewById(R.id.indicator)
                , getResources().getStringArray(R.array.indicator_titles)
                , new int[]{
                R.mipmap.read_guide_page_1,
                R.mipmap.read_guide_page_3,
                R.mipmap.read_guide_page_2,
                R.mipmap.read_guide_page_3});
        GuideHelper.getInstance().init(this,welcomeImageBean,guideBean);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(ReadInitActivity.this, ReadMainActivity.class));
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(100);
    }
}
