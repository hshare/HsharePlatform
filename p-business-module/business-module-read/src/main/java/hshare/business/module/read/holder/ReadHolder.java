package hshare.business.module.read.holder;

import android.view.View;
import android.widget.TextView;

import hshare.base.component.view.recyclerview.holder.BaseRecyclerHolder;
import hshare.business.module.read.R;
import hshare.business.module.read.bean.WenZhangBean;

/**
 * hot holder
 *
 * @author huzeliang
 * @version 1.0 2017-11-7 10:31:23
 * @see ***
 * @since ***
 */
public class ReadHolder extends BaseRecyclerHolder<WenZhangBean> {

    /**
     * title
     */
    private TextView tvTitle;


    public ReadHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
    }

    @Override
    public void updateData(WenZhangBean infoContentListBean, int position) {
        tvTitle.setText(infoContentListBean.getTitle());
    }
}
