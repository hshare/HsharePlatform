package hshare.base.component.simplemvp;

/**
 *@author huzeliang
 */
interface IActivity {
    /**
     * init presenter
     */
    void initPresenter();

    /**
     * init data
     */
    void initData();

    /**
     * init view
     */
    void initView();

    /**
     * get layout id
     *
     * @return layout id
     */
    int getLayoutId();
}
