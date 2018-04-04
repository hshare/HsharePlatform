package hshare.base.component.view.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hshare.base.component.view.recyclerview.holder.BaseRecyclerHolder;

/**
 * base adapter for recyclerView
 *
 * @param <T> t
 * @author huzeliang
 * @version 1.0 2017-11-7 09:52:32
 * @see ***
 * @since ***
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder<T>> {

    /**
     * databean
     */
    private List<T> beans;

    /**
     * click listener
     */
    private OnRecyclerViewItemClickListener itemClickListener = null;

    /**
     * none
     *
     * @param beans beans
     */
    public BaseRecyclerAdapter(List<T> beans) {
        this.beans = beans;
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        BaseRecyclerHolder<T> BaseRecyclerHolder = getBaseRecyclerHolder(view, viewType);
        BaseRecyclerHolder.setOnItemClickListener(new BaseRecyclerHolder.OnViewClickListener() {//设置Item点击事件
            @Override
            public void onViewClick(View view, int position) {
                if (itemClickListener != null && beans.size() > 0) {
                    itemClickListener.onItemClick(view, viewType, beans.get(position), position);
                }
            }
        });
        return BaseRecyclerHolder;
    }

    /**
     * init base holder for adapter
     *
     * @param v        view
     * @param viewType viewtype
     * @return base holder
     */
    protected abstract BaseRecyclerHolder<T> getBaseRecyclerHolder(View v, int viewType);

    /**
     * get layout id for item
     *
     * @param viewType view type
     * @return layout id
     */
    public abstract int getLayoutId(int viewType);


    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        holder.updateData(beans.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (beans != null && beans.size() > 0) {
            return beans.size();
        }
        return 0;
    }

    /**
     * click listener
     *
     * @param <T> bean
     */
    public interface OnRecyclerViewItemClickListener<T> {
        /**
         * item click
         *
         * @param view     view
         * @param viewType viewType
         * @param data     data
         * @param position position
         */
        void onItemClick(View view, int viewType, T data, int position);
    }

    /**
     * set click listener
     *
     * @param listener listener for click
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
