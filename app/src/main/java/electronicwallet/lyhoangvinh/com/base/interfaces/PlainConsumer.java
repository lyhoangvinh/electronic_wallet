package electronicwallet.lyhoangvinh.com.base.interfaces;

import android.support.annotation.NonNull;

public interface PlainConsumer<T> {
    void accept(@NonNull T t);
}
