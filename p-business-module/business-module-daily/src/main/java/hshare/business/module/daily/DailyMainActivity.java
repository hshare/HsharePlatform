package hshare.business.module.daily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hshare.base.component.view.indicator.spring.SpringIndicator;
import hshare.base.component.view.viewpager.ScrollerViewPager;
import hshare.business.component.initguide.GuideHelper;
import hshare.business.component.initguide.SimpleOnPageClickListener;

public class DailyMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_activity_main);
//        GuideHelper.getInstance().init(getSupportFragmentManager()
//                , (ScrollerViewPager) findViewById(R.id.view_pager)
//                , (SpringIndicator) findViewById(R.id.indicator)
//                , getResources().getStringArray(R.array.indicator_titles)
//                , new int[]{
//                        R.mipmap.guide5,
//                        R.mipmap.guide6,
//                        R.mipmap.guide7,
//                        R.mipmap.guide8}, new SimpleOnPageClickListener() {
//                    @Override
//                    public void onFinish(boolean isFinish) {
//                        if (isFinish) {
////                            startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
////                            SplashActivity.this.finish();
//                        }
//                    }
//                });
    }
}
