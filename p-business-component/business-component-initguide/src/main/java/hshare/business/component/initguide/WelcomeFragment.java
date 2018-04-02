package hshare.business.component.initguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

import hshare.base.component.view.viewpager.base.NormalViewPageBaseBean;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author hzl
 */
public class WelcomeFragment extends Fragment {
    /**
     * 当前界面的图片
     */
    private GifImageView imageView;

    private WelcomeImageBean welcomeImageBean;

    public static WelcomeFragment newInstance(WelcomeImageBean welcomeImageBean) {
        if (welcomeImageBean == null) {
            throw new IllegalStateException("WelcomeImageBean should be Initialized!");
        }
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("databean", welcomeImageBean);
        welcomeFragment.setArguments(args);
        return welcomeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            welcomeImageBean = (WelcomeImageBean) getArguments().getSerializable("databean");
        }
        if (welcomeImageBean == null) {
            throw new IllegalStateException("WelcomeImageBean should be Initialized!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.initguide_fragment_guide, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = getView().findViewById(R.id.image);

        if (welcomeImageBean.hasLocalImage()) {
            try {
                imageView.setImageDrawable(new GifDrawable(welcomeImageBean.getLocalImage()));
            } catch (IOException e) {
                e.printStackTrace();
                imageView.setImageResource(welcomeImageBean.getDefaultWelcomeImageRes());
            }
        } else {
            imageView.setImageResource(welcomeImageBean.getDefaultWelcomeImageRes());
        }
    }


}
