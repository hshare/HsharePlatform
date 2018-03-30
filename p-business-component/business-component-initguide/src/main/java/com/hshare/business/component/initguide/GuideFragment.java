package com.hshare.business.component.initguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hshare.base.component.view.viewpager.base.NormalViewPageBaseBean;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * 标配的fragment，可自定义
 *
 * @author hzl
 */
public class GuideFragment extends Fragment implements OnPageChangeListener{

    protected OnPageClickListener onPageClickListener;

    /**
     * 当前页面的图片资源
     */
    private int bgRes;
    /**
     * 总页数
     */
    private int bgSum;
    /**
     * 当前页面编号
     */
    private int pageNo;
    /**
     * 当前界面的图片
     */
    private GifImageView imageView;

    GifDrawable gifDrawable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            NormalViewPageBaseBean normalViewPageBaseBean = (NormalViewPageBaseBean) getArguments().getSerializable("databean");
            bgRes = normalViewPageBaseBean.getWhat();
            pageNo = normalViewPageBaseBean.getWhat1();
            bgSum = normalViewPageBaseBean.getWhat2();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.business_component_fragment_guide, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = getView().findViewById(R.id.image);

        try {
            gifDrawable = new GifDrawable(getResources(), bgRes);
            imageView.setImageDrawable(gifDrawable);
            if (pageNo != 0){
                gifDrawable.pause();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (onPageClickListener != null) {
                    onPageClickListener.onPageClick(pageNo, bgSum);
                    if (pageNo + 1 == bgSum) {
                        onPageClickListener.onFinish(true);
                    } else {
                        onPageClickListener.onFinish(false);
                    }

                } else {
                    if (pageNo + 1 == bgSum) {
                        getActivity().finish();
                    }
                }
            }
        });
    }

    public void setOnPageClickListener(OnPageClickListener onPageClickListener) {
        this.onPageClickListener = onPageClickListener;
    }

    @Override
    public void onPageChanged(int showPageNo, int selfPageNo) {
        if (gifDrawable == null){
            return;
        }
        if (showPageNo == selfPageNo){
            gifDrawable.start();
        }else {
            gifDrawable.pause();
        }
    }
}
