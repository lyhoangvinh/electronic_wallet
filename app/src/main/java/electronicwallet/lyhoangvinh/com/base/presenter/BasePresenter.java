package electronicwallet.lyhoangvinh.com.base.presenter;


import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import electronicwallet.lyhoangvinh.com.base.interfaces.Lifecycle;
import electronicwallet.lyhoangvinh.com.base.interfaces.PlainConsumer;
import electronicwallet.lyhoangvinh.com.base.interfaces.Refreshable;
import electronicwallet.lyhoangvinh.com.base.view.BaseView;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;
import electronicwallet.lyhoangvinh.com.utils.RxUtil;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

public abstract class BasePresenter<V extends BaseView> implements Lifecycle, Refreshable {

    @Nullable
    private V view;

    protected Context context;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private EventBus eventBus;

    private DatabaseManager databaseManager;

    public BasePresenter(@ActivityContext Context context , DatabaseManager databaseManager) {
        this.context = context;
        this.databaseManager = databaseManager;
        mCompositeDisposable = new CompositeDisposable();
        eventBus = EventBus.getDefault();
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    @Nullable
    public V getView() {
        return view;
    }

    public Context getContext() {
        return context;
    }

    public void bindView(V view) {
        this.view = view;
    }

    public void unbindView() {
        this.view = null;
    }

    /**
     * @return {@link LifecycleOwner} associate with this presenter (host activities, fragments)
     */
    protected LifecycleOwner getLifeCircleOwner() {
        return (LifecycleOwner) view;
    }

    protected <T> void addRequestFollowable(
            Flowable<T> request, final boolean showProgress,
            final boolean forceResponseWithoutCheckNullView,
            @Nullable final PlainConsumer<T> responseConsumer) {

        boolean shouldUpdateUI = showProgress || responseConsumer != null;

        if (showProgress && getView() != null) {
            getView().showProgress();
        }

        Disposable disposable = RxUtil.makeRequestFollowable(request, shouldUpdateUI, t -> {
            if (responseConsumer != null && (forceResponseWithoutCheckNullView || getView() != null)) {
                responseConsumer.accept(t);
            }
        }, () -> {
            if (showProgress && getView() != null) {
                getView().hideProgress();
            }

        });

        if (mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected <T> void addRequestFollowable(boolean showProgress, Flowable<T> request,
                                            @Nullable PlainConsumer<T> responseConsumer) {
        addRequestFollowable(request, showProgress, false, responseConsumer);
    }

    protected <T> void addRequestSingle(
            Single<T> request, final boolean showProgress,
            final boolean forceResponseWithoutCheckNullView,
            @Nullable final PlainConsumer<T> responseConsumer){
        boolean shouldUpdateUI = showProgress || responseConsumer != null;

        if (showProgress && getView() != null) {
            getView().showProgress();
        }

        Disposable disposable = RxUtil.makeRequestSingle(request, shouldUpdateUI, t -> {
            if (responseConsumer != null && (forceResponseWithoutCheckNullView || getView() != null)) {
                responseConsumer.accept(t);
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                if (showProgress && getView() != null) {
                    getView().hideProgress();
                }

            }
        });

        if (mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected <T> void addRequestSingle(boolean showProgress, Single<T> request,
                                        @Nullable PlainConsumer<T> responseConsumer) {
        addRequestSingle(request, showProgress, false, responseConsumer);
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void refresh(){

    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null){
            mCompositeDisposable.dispose();
        }
        unbindView();
    }
}
