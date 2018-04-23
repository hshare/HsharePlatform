package hshare.business.module.j3new.ui.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: NormalEntity, need not implements MultiItemEntity interface
 * @date 2018/3/30  11:13
 */

public class NormalMultipleEntity implements MultiItemEntity {

    public static final int TOP_BANNER = 1;
    public static final int TOP_GRID = 2;
    public static final int BOTTOM_LIST = 3;

    private int type = 0;
    private List<InfoContentListBean> infoContentListBeans;
    private InfoContentListBean infoContentBean;

    public NormalMultipleEntity(int type, List<InfoContentListBean> infoContentListBeans) {
        this.type = type;
        this.infoContentListBeans = infoContentListBeans;
    }

    public NormalMultipleEntity(int type, InfoContentListBean infoContentBean) {
        this.type = type;
        this.infoContentBean = infoContentBean;
    }

    public List<InfoContentListBean> getInfoContentListBeans() {
        return infoContentListBeans;
    }

    public InfoContentListBean getInfoContentBean() {
        return infoContentBean;
    }

    public List<String> getTitles(){
        List<String> list = new ArrayList<>();
        if (infoContentListBeans != null){
            for (int i = 0; i < infoContentListBeans.size(); i++) {
                list.add(infoContentListBeans.get(i).getTitle());
            }
        }
        return list;
    }

    public void setInfoContentListBeans(List<InfoContentListBean> infoContentListBeans) {
        this.infoContentListBeans = infoContentListBeans;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
