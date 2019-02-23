package electronicwallet.lyhoangvinh.com.base.presenter;


import android.content.Context;
import android.support.annotation.CallSuper;

import electronicwallet.lyhoangvinh.com.base.interfaces.LoadMoreable;
import electronicwallet.lyhoangvinh.com.base.interfaces.Refreshable;
import electronicwallet.lyhoangvinh.com.base.view.BaseView;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;


/**
 * Base presenter with paging
 */

public abstract class BaseListPresenter<V extends BaseView> extends BasePresenter<V> implements Refreshable, LoadMoreable {

    private boolean isRefreshed = false;

    public BaseListPresenter(Context context, DatabaseManager databaseManager) {
        super(context, databaseManager);
    }

    public void setRefreshed(boolean refreshed) {
        isRefreshed = refreshed;
    }

    public boolean isRefreshed() {
        return isRefreshed;
    }



    /**
     * refresh all paging date and re-fetch data
     */
    @CallSuper
    @Override
    public void refresh() {
        isRefreshed = true;
        fetchData();
    }

    /**
     * load next page
     */
    @CallSuper
    public void loadMore() {
        if (canLoadMore()) {
            fetchData();
        }
    }

    /**
     * Fetch data from server
     */
    protected abstract void fetchData();


}
