package hshare.business.module.read.ui.adapter;

import android.view.View;

import java.util.List;

import hshare.base.component.view.recyclerview.adapter.BaseRecyclerAdapter;
import hshare.base.component.view.recyclerview.holder.BaseRecyclerHolder;
import hshare.business.module.read.R;
import hshare.business.module.read.bean.WenZhangBean;
import hshare.business.module.read.ui.holder.ReadHolder;


/**
 * hot adapter
 *
 * @author huzeliang
 * @version 1.0 2017-11-6 18:19:01
 * @see ***
 * @since ***
 */
public class ReadAdapter extends BaseRecyclerAdapter<WenZhangBean> {


    /**
     * none
     *
     * @param beans beans
     */
    public ReadAdapter(List<WenZhangBean> beans) {
        super(beans);
    }

    @Override
    protected BaseRecyclerHolder<WenZhangBean> getBaseRecyclerHolder(View v, int viewType) {
        return new ReadHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.read_item_main;
    }
}
