package hshare.base.component.simplemvp;

import java.util.List;

/**
 * model
 *
 * @author huzeliang
 * @version 1.0 2017-11-7 09:30:24
 * @see ***
 * @since ***
 */
public interface IModel {

    /**
     * get hot data callback
     * @param <T> t
     */
    interface GetDataCallback<T> {
        /**
         * success
         *
         * @param beans hot list bean
         */
        void onSuccess(List<T> beans);

        /**
         * error
         *
         * @param s error msg
         */
        void onError(String s);

        /**
         * complete
         */
        void onComplete();
    }
}
