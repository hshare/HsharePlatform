package hshare.business.module.read;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hshare.base.component.view.viewpager.adapter.NormalViewPageAdapter;
import hshare.base.component.view.viewpager.base.NormalViewPageBaseBean;
import hshare.business.module.read.bean.Constants;
import hshare.business.module.read.bean.WenZhangBean;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/read/detail")
public class ReadDetailActivity extends AppCompatActivity {

    private WebView webview;
    private String jumpUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity_detail);

        jumpUrl = getIntent().getExtras().getString("jumpUrl");
        Log.i("hzl", "jumpUrl:" + jumpUrl);
        webview = findViewById(R.id.webview);

        Observable.create(new ObservableOnSubscribe<String>() { // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) {

                Document doc = null;
                try {
                    doc = Jsoup.connect(jumpUrl).get();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Elements elements = doc.select("center table tbody tr td table tbody tr td table #wenzhangziti");
                String html = "";
                for (int i = 0; i < elements.size(); i++) {
                    html = elements.html();
                }
                e.onNext(html);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() { // 第三步：订阅
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull String html) {
                        Log.i("hzl", html + "");
//                        if (!TextUtils.isEmpty(html)){
//                            Pattern p = Pattern.compile("<a href([^:]*?)>");
//                            Matcher m = p.matcher(html);
//                            Log.i("hzl", m.group() + "");
//                        }
                        webview.loadData(html, "text/html; charset=UTF-8", null);//这种写法可以正确解码
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
