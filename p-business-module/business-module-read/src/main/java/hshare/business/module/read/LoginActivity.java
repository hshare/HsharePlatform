package hshare.business.module.read;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * 功能/模块 ：***
 *
 * @author huzeliang
 * @version 1.0 ${date} ${time}
 * @see ***
 * @since ***
 */
@Route(path = "/read/login")
public class LoginActivity  extends AppCompatActivity {

    public static boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity_login);
        isLogin = false;
    }

    public void login(View view){
        isLogin = true;
        Test1Interceptor.callback1.onContinue(Test1Interceptor.postcard1);
        finish();
    }
}
