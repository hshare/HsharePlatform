package hshare.base.component.simplemvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * base activity
 *
 * @author huzeliang
 */
public abstract class SimpleMvpActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {

    /**
     * present
     */
    protected P presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initPresenter();
        initView();
        presenter.onStart();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
