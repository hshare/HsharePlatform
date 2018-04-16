package hshare.multiple.module.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jaeger.statusbarutil.MainActivity;


public class HsharePfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hshare_pf);

    }

    public void onStatusBarClick(View view){
        startActivity(new Intent(HsharePfActivity.this, MainActivity.class));
    }

    public void onSwipeClick(View view){
        startActivity(new Intent(HsharePfActivity.this, com.aspsine.swipetoloadlayout.demo.MainActivity.class));
    }
}
