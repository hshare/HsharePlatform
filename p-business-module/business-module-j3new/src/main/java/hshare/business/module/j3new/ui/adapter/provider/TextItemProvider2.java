package hshare.business.module.j3new.ui.adapter.provider;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import cn.bingoogolapple.bgabanner.BGABanner;
import hshare.base.component.general.AssetUtil;
import hshare.business.module.j3new.R;
import hshare.business.module.j3new.ui.bean.InfoContentListBean;
import hshare.business.module.j3new.ui.bean.NormalMultipleEntity;

import static hshare.business.module.j3new.ui.bean.NormalMultipleEntity.TOP_BANNER;
import static hshare.business.module.j3new.ui.bean.NormalMultipleEntity.TOP_GRID;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: Text ItemProvider
 * @date 2018/3/30  11:39
 */
@ItemProviderTag(
        viewType = TOP_GRID,
        layout = R.layout.item_text_view2
)
public class TextItemProvider2 extends BaseItemProvider<NormalMultipleEntity, BaseViewHolder> {

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        helper.setText(R.id.tvTitle,data.getInfoContentBean().getTitle());
//        Glide.with(mContext).load(data.getInfoContentBean().getIcon_url()).into((ImageView) helper.getView(R.id.ivAvatar));
        helper.setImageBitmap(R.id.ivAvatar, AssetUtil.getImageFromAssetsFile(mContext, data.getInfoContentBean().getIcon_url()));
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
