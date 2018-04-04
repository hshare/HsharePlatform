package hshare.business.module.read;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import hshare.base.component.view.recyclerview.adapter.BaseRecyclerAdapter;
import hshare.base.component.view.viewpager.base.NormalViewPageBaseBean;
import hshare.business.module.read.adapter.ReadAdapter;
import hshare.business.module.read.bean.WenZhangBean;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hcare on 2018/4/3.
 */

public class ReadMainFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener, BaseRecyclerAdapter.OnRecyclerViewItemClickListener<WenZhangBean> {

    private SwipeToLoadLayout swipeToLoadLayout;
    private BaseRecyclerAdapter mAdapter;
    private List<WenZhangBean> wenZhangBeans;
    private RecyclerView recyclerView;
    private NormalViewPageBaseBean normalViewPageBaseBean;
    private int page = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            normalViewPageBaseBean = (NormalViewPageBaseBean) getArguments().getSerializable("databean");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.read_fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeToLoadLayout = view.findViewById(R.id.swipeToLoadLayout);
        recyclerView = view.findViewById(R.id.swipe_target);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        wenZhangBeans = new ArrayList<>();
        mAdapter = new ReadAdapter(wenZhangBeans);
        recyclerView.setAdapter(mAdapter);

        swipeToLoadLayout.setRefreshing(true);
        mAdapter.setOnItemClickListener(this);
    }

    private void getData(){
        page = 1;
        getData(1);
    }
    private String loadUrl;
    private void getData(final int page) {
        loadUrl = normalViewPageBaseBean.getArg1();
        if (page != 1){
            loadUrl = normalViewPageBaseBean.getArg1() + "list_" + normalViewPageBaseBean.getArg2() +"_" + page + ".html";
        }
        Observable.create(new ObservableOnSubscribe<List<WenZhangBean>>() { // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<WenZhangBean>> e) throws Exception {

                Document doc = Jsoup.connect(loadUrl).get();
                Elements elements = doc.select("center table tbody tr td table tbody tr td table.tbspan");
                List<WenZhangBean> wenZhangBeans = new ArrayList<>();
                for (int i = 0; i < elements.size(); i++) {
                    Elements es = elements.get(i).select("tr td");
                    if (es.size() == 6) {

                        String title = es.get(2).text();
                        String url = es.get(2).select("a.ulink").attr("href");
                        Elements titles = es.get(2).select("a");
                        if (titles.size() == 2) {
                            title = titles.get(1).text();
                            url = titles.get(1).attr("href");
                        }


                        String date = es.get(4).text();
                        String content = es.get(5).text();
                        String pic = es.get(5).select("img").attr("src");
                        Log.i("hzl", title + "\n" + date + "\n" + url + "\n" + pic + "\n" + content + "\n-------------------\n");

                        WenZhangBean wenZhangBean = new WenZhangBean();
                        wenZhangBean.setContent(content);
                        wenZhangBean.setDate(date);
                        wenZhangBean.setJumpUrl(url);
                        wenZhangBean.setPicUrl(pic);
                        wenZhangBean.setTitle(title);
                        wenZhangBeans.add(wenZhangBean);
                    }
                }
                e.onNext(wenZhangBeans);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<WenZhangBean>>() { // 第三步：订阅
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull List<WenZhangBean> wenZhangBeans) {
                        Log.i("hzl", wenZhangBeans.size() + "");
                        if (page == 1){
                            ReadMainFragment.this.wenZhangBeans.clear();
                            ReadMainFragment.this.wenZhangBeans.addAll(wenZhangBeans);
                        }else {
                            ReadMainFragment.this.wenZhangBeans.addAll(wenZhangBeans);
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        swipeToLoadLayout.setRefreshing(false);
                        swipeToLoadLayout.setLoadingMore(false);
                    }
                });
    }

    @Override
    public void onRefresh() {
        getData();
    }

    @Override
    public void onLoadMore() {
        page ++ ;
        getData(page);
    }


    @Override
    public void onItemClick(View view, int viewType, WenZhangBean data, int position) {
        Intent intent = new Intent(getActivity(),ReadDetailActivity.class);
        intent.putExtra("jumpUrl",data.getJumpUrl());
        startActivity(intent);
    }
}
