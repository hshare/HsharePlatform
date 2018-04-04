package hshare.base.component.view.recyclerview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * base holder for recyclerview
 *
 * @param <T> t
 * @author huzeliang
 * @version 1.0 2017-11-7 09:53:51
 * @see ***
 * @since ***
 */
public abstract class BaseRecyclerHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    /**
     * view click listener
     */
    private OnViewClickListener onViewClickListener = null;

    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        initView(itemView);
    }

    /**
     * init view
     *
     * @param itemView view
     */
    protected abstract void initView(View itemView);

    /**
     * update data
     *
     * @param t        t
     * @param position pos
     */
    public abstract void updateData(T t, int position);

    @Override
    public void onClick(View view) {
        if (onViewClickListener != null) {
            onViewClickListener.onViewClick(view, this.getPosition());
        }
    }

    /**
     * listener interface for view click
     */
    public interface OnViewClickListener {
        /**
         * @param view     view
         * @param position position
         */
        void onViewClick(View view, int position);
    }

    /**
     * set listener for item
     *
     * @param listener listener
     */
    public void setOnItemClickListener(OnViewClickListener listener) {
        this.onViewClickListener = listener;
    }

    /**
     * release resource
     */
    protected void onRelease() {

    }

}
