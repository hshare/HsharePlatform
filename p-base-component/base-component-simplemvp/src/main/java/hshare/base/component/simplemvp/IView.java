package hshare.base.component.simplemvp;

import android.support.v7.widget.RecyclerView;

/**
 * view
 *
 * @param <T> t
 * @author huzeliang
 * @version 1.0 2017-11-6 13:39:34
 * @see ***
 * @since ***
 */
public interface IView<T> {

    /**
     * none
     *
     * @param iPresenter persenter
     */
    void setPresenter(T iPresenter);

    /**
     * set hot adapter
     *
     * @param adapter adapter
     */
    void setAdapter(RecyclerView.Adapter adapter);

    /**
     * show toast
     *
     * @param s msg
     */
    void showToast(String s);

    /**
     * show waiting dialog
     *
     * @param s msg
     */
    void showDialog(String s);

    /**
     * hide dialog
     */
    void hideDialog();
}
