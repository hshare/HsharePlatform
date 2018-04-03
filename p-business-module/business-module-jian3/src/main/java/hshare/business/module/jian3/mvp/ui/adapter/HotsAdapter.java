package hshare.business.module.jian3.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import hshare.business.module.jian3.R;
import hshare.business.module.jian3.mvp.model.entity.InfoContentListBean;
import hshare.business.module.jian3.mvp.ui.holder.HotsItemHolder;

/**
 * Created by jess on 9/4/16 12:57
 * Contact with jess.yan.effort@gmail.com
 */
public class HotsAdapter extends DefaultAdapter<InfoContentListBean> {
    public HotsAdapter(List<InfoContentListBean> infos) {
        super(infos);
    }

    public void setData(List<InfoContentListBean> infos){
        this.mInfos = infos;
        notifyDataSetChanged();
    }

    @Override
    public BaseHolder<InfoContentListBean> getHolder(View v, int viewType) {
        return new HotsItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.recycle_list_hots;
    }
}
