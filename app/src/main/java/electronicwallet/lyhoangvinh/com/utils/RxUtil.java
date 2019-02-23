package electronicwallet.lyhoangvinh.com.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import electronicwallet.lyhoangvinh.com.base.interfaces.PlainConsumer;
import electronicwallet.lyhoangvinh.com.base.response.ErrorEntity;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {

    public static <T> Disposable makeRequestFollowable(
            Flowable<T> request, boolean shouldUpdateUi,
            @NonNull final PlainConsumer<T> responseConsumer,
            @Nullable final Action onComplete) {

        Flowable<T> flyable = request.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        if (shouldUpdateUi) {
            flyable = flyable.observeOn(AndroidSchedulers.mainThread());
        }

        return flyable.subscribe(t -> {
            responseConsumer.accept(t);
            if (onComplete != null) {
                onComplete.run();
            }
        }, throwable -> {
            if (onComplete != null) {
                onComplete.run();
            }
        });
    }

    public static <T> Disposable makeRequestSingle(
            Single<T> request, boolean shouldUpdateUi,
            @NonNull final PlainConsumer<T> responseConsumer,
            @Nullable final Action onComplete) {

        Single<T> single = request.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        if (shouldUpdateUi) {
            single = single.observeOn(AndroidSchedulers.mainThread());
        }
        return single.subscribe(t -> {
            responseConsumer.accept(t);
            if (onComplete != null) {
                onComplete.run();
            }
        }, throwable -> {
            if (onComplete != null) {
                onComplete.run();
            }
        });
    }

    public static <T> Disposable makeRequest(
            Single<T> request, boolean shouldUpdateUi,
            @NonNull PlainConsumer<T> responseConsumer,
            @Nullable PlainConsumer<ErrorEntity> errorConsumer,
            @Nullable Action onComplete) {

        Single<T> single = request.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
        if (shouldUpdateUi) {
            single = single.observeOn(AndroidSchedulers.mainThread());
        }

        return single.subscribe(response -> {
            responseConsumer.accept(response);
            if (onComplete != null) {
                onComplete.run();
            }
        }, throwable -> {
            if (throwable instanceof RuntimeException) {
                // must be fixed while developing
//                throw new Exception(throwable);
            }
            // handle error
            if (errorConsumer != null) {
                errorConsumer.accept(ErrorEntity.getError(throwable));
            }
            if (onComplete != null) {
                onComplete.run();
            }
        });
    }

    public static <T> Disposable makeRequest(Single<T> request, boolean shouldUpdateUi,
                                             @NonNull PlainConsumer<T> responseConsumer,
                                             @Nullable PlainConsumer<ErrorEntity> errorConsumer) {
        return makeRequest(request, shouldUpdateUi, responseConsumer, errorConsumer, null);
    }
}
