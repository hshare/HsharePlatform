package hshare.business.module.j3new.ui.adapter.provider;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import cn.bingoogolapple.bgabanner.BGABanner;
import hshare.business.module.j3new.R;
import hshare.business.module.j3new.ui.bean.InfoContentListBean;
import hshare.business.module.j3new.ui.bean.NormalMultipleEntity;

import static hshare.business.module.j3new.ui.bean.NormalMultipleEntity.BOTTOM_LIST;
import static hshare.business.module.j3new.ui.bean.NormalMultipleEntity.TOP_BANNER;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: Text ItemProvider
 * @date 2018/3/30  11:39
 */
@ItemProviderTag(
        viewType = BOTTOM_LIST,
        layout = R.layout.recycle_list_hots
)
public class TextItemProvider1 extends BaseItemProvider<NormalMultipleEntity, BaseViewHolder> {

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        helper.setText(R.id.tv_name,data.getInfoContentBean().getTitle());
        helper.setText(R.id.tv_content,data.getInfoContentBean().getIntro());
        Glide.with(mContext).load(data.getInfoContentBean().getIcon_url()).into((ImageView) helper.getView(R.id.iv_avatar));
    }

    @Override
    public void onClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "click:" + data.getInfoContentListBeans().get(position).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
