package hshare.business.module.jian3.mvp.ui.holder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.App;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;
import hshare.business.module.jian3.R;
import hshare.business.module.jian3.mvp.model.entity.InfoContentListBean;
import io.reactivex.Observable;

/**
 * Created by jess on 9/4/16 12:56
 * Contact with jess.yan.effort@gmail.com
 */
public class HotsItemHolder extends BaseHolder<InfoContentListBean> {

    @Nullable
    @BindView(R.id.iv_avatar)
    ImageView mAvater;
    @Nullable
    @BindView(R.id.tv_name)
    TextView mName;
    @Nullable
    @BindView(R.id.tv_content)
    TextView tvContent;

    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架

    public HotsItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到Application的地方,拿到AppComponent,从而得到用Dagger管理的单例对象
        mAppComponent = ((App) itemView.getContext().getApplicationContext()).getAppComponent();
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(InfoContentListBean data, int position) {
        Observable.just(data.getTitle())
                .subscribe(s -> {
                    mName.setText(s);
                });
        Observable.just(data.getIntro())
                .subscribe(s -> {
                    tvContent.setText(s);
                });

        mImageLoader.loadImage(mAppComponent.appManager().getCurrentActivity() == null
                        ? mAppComponent.Application() : mAppComponent.appManager().getCurrentActivity(),
                GlideImageConfig
                        .builder()
                        .url(data.getIcon_url())
                        .imageView(mAvater)
                        .errorPic(R.mipmap.ic_jianxiaqingyuan)
                        .build());
    }


    @Override
    protected void onRelease() {
        mImageLoader.clear(mAppComponent.Application(), GlideImageConfig.builder()
                .imageViews(mAvater)
                .build());
    }
}
