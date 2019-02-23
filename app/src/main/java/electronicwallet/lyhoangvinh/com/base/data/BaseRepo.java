package electronicwallet.lyhoangvinh.com.base.data;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.Nullable;

import electronicwallet.lyhoangvinh.com.base.interfaces.PlainConsumer;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;

public abstract class BaseRepo {

    private final LifecycleOwner owner;

    private final DatabaseManager databaseManager;

    public BaseRepo(LifecycleOwner owner, DatabaseManager databaseManager) {
        this.owner = owner;
        this.databaseManager = databaseManager;
    }

    /**
     * For single data
     * @param remote
     * @param onSave
     * @param <T>
     * @return
     */
    protected <T> Flowable<Resource<T>> createResource(@Nullable Single<T> remote,
                                                       @Nullable PlainConsumer<T> onSave) {
        return Flowable.create(emitter -> {
            new SimpleNetworkBoundSource<T>(emitter, true) {

                @Override
                public Single<T> getRemote() {
                    return remote;
                }

                @Override
                public void saveCallResult(T data, boolean isRefresh) {
                    if (onSave != null) {
                        onSave.accept(data);
                    }
                }
            };
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * For a list of data
     * @param isRefresh
     * @param remote
     * @param onSave
     * @param <T>
     * @return
     */
    protected <T> Flowable<Resource<T>> createResource(boolean isRefresh, @Nullable Single<T> remote,
                                                       @Nullable OnSaveResultListener<T> onSave) {
        return Flowable.create(emitter -> {
            new SimpleNetworkBoundSource<T>(emitter, isRefresh) {

                @Override
                public Single<T> getRemote() {
                    return remote;
                }

                @Override
                public void saveCallResult(T data, boolean isRefresh) {
                    if (onSave != null) {
                        onSave.onSave(data, isRefresh);
                    }
                }
            };
        }, BackpressureStrategy.BUFFER);
    }

    protected interface OnSaveResultListener<T> {
        void onSave(T data, boolean isRefresh);
    }
}
