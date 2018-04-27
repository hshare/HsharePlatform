package hshare.business.module.j3new.ui.fragment;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.aspsine.swipetoloadlayout.callback.SimpleSwipeTrigger;
import com.aspsine.swipetoloadlayout.demo.view.header.GoogleCircleHookRefreshHeaderView;
import com.aspsine.swipetoloadlayout.demo.view.header.GoogleCircleHookRefreshHeaderView_H1;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import hshare.base.component.general.ScreenUtil;
import hshare.base.component.view.statusbar.StatusBarUtil;
import hshare.business.module.j3new.R;
import hshare.business.module.j3new.ui.adapter.DemoMultipleItemRvAdapter;
import hshare.business.module.j3new.ui.bean.Api;
import hshare.business.module.j3new.ui.bean.HotBean;
import hshare.business.module.j3new.ui.bean.InfoContentListBean;
import hshare.business.module.j3new.ui.bean.NormalMultipleEntity;

import static hshare.business.module.j3new.ui.bean.NormalMultipleEntity.BOTTOM_LIST;
import static hshare.business.module.j3new.ui.bean.NormalMultipleEntity.TOP_BANNER;
import static hshare.business.module.j3new.ui.bean.NormalMultipleEntity.TOP_GRID;

//http://www.jx3tong.com/?m=api&c=info&a=content_list&num=30&p=1&category_id=0
//http://www.jx3tong.com/?m=api&c=info&a=category_list
public class J3HomeFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private RecyclerView recyclerView;
    private View homeTitleBarBgView;
    private View scanningLayout;
    private View advisoryLayout;
    private Activity mActivity;
    private static int DISTANCE_WHEN_TO_SELECTED = 40;
    private int distanceY;
    private SwipeToLoadLayout swipeToLoadLayout;
    private FrameLayout homeTitleBarLayout;
    List<NormalMultipleEntity> mData;

    public static J3HomeFragment newInstance() {
        return new J3HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        return inflater.inflate(R.layout.jian3_fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View fake_status_bar = view.findViewById(R.id.fake_status_bar);
        fake_status_bar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,StatusBarUtil.getStatusBarHeight(getContext())));


        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        homeTitleBarBgView = (View) view.findViewById(R.id.home_title_bar_bg_view);
        advisoryLayout = (View) view.findViewById(R.id.advisoryLayout);
        scanningLayout = (View) view.findViewById(R.id.scanningLayout);
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        homeTitleBarLayout = (FrameLayout) view.findViewById(R.id.homeTitleBarLayout);


        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setSwipeStyle(SwipeToLoadLayout.STYLE.ABOVE);
        View view1 = getActivity().getLayoutInflater().inflate(R.layout.layout_google_hook_header_h1, swipeToLoadLayout, false);
        swipeToLoadLayout.setRefreshHeaderView(view1);
        View view2 = getActivity().getLayoutInflater().inflate(R.layout.layout_google_hook_footer, swipeToLoadLayout, false);
        swipeToLoadLayout.setLoadMoreFooterView(view2);

        ((GoogleCircleHookRefreshHeaderView_H1) view1).setOnSwipeTrigger(new SimpleSwipeTrigger() {
            @Override
            public void onMove(int i, boolean b, boolean b1) {
                Log.i("huzeliang", i + "");
                super.onMove(i, b, b1);
                if (i > 10) {
                    homeTitleBarLayout.setVisibility(View.GONE);
                } else {
                    homeTitleBarLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                distanceY += dy;
                if (distanceY > ScreenUtil.dip2px(mActivity, 20)) {
                    homeTitleBarBgView.setBackgroundColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT > 10) {
                        homeTitleBarBgView.setAlpha(distanceY * 1.0f / ScreenUtil.dip2px(mActivity, 100));
                    } else {
                        DISTANCE_WHEN_TO_SELECTED = 20;
                    }
                } else {
                    homeTitleBarBgView.setBackgroundColor(0);
                }

                if (distanceY > ScreenUtil.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED) && !scanningLayout.isSelected()) {
                    scanningLayout.setSelected(true);
                    advisoryLayout.setSelected(true);
                    StatusBarUtil.setColor(getActivity(), Color.BLACK, 255);
                } else if (distanceY <= ScreenUtil.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED) && scanningLayout.isSelected()) {
                    scanningLayout.setSelected(false);
                    advisoryLayout.setSelected(false);
                    StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), 0, null);
                }
            }
        });

        GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(manager);

        mData = getNormalMultipleEntities();
        DemoMultipleItemRvAdapter adapter = new DemoMultipleItemRvAdapter(mData);


        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int type = mData.get(position).getItemType();
                if (type == TOP_BANNER) {
                    return 5;
                } else if (type == BOTTOM_LIST) {
                    return 5;
                } else if (type == TOP_GRID) {
                    return 1;
                }
                return 5;
            }
        });

        recyclerView.setAdapter(adapter);
    }

    /**
     *
     * @return
     */
    public static List<NormalMultipleEntity> getNormalMultipleEntities() {
        List<NormalMultipleEntity> list = new ArrayList<>();

        HotBean hotBean = (new Gson()).fromJson(Api.HOT_STRING_P1, HotBean.class);
        list.add(new NormalMultipleEntity(TOP_BANNER, hotBean.getInfo_content_list().subList(0, 5)));

        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("新闻","gg01.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("公告","gg02.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("娱乐","gg03.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("美图","gg04.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("COS","gg05.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("漫画","gg06.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("视频","gg07.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("音乐","gg08.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("周边","gg09.png")));
        list.add(new NormalMultipleEntity(TOP_GRID, new InfoContentListBean("攻略","gg10.png")));


        for (int i = 5; i < hotBean.getInfo_content_list().size(); i++) {
            list.add(new NormalMultipleEntity(BOTTOM_LIST, hotBean.getInfo_content_list().get(i)));
        }

        return list;
    }


    @Override
    public void onRefresh() {
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        }, 2000);
    }
}
