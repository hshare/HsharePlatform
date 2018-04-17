package hshare.business.module.j3new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hshare.base.component.view.statusbar.StatusBarUtil;

public class J3NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gan);
        StatusBarUtil.setTranslucentForImageView(this,110,null);
    }
}
