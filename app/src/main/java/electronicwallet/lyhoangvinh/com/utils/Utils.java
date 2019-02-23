package electronicwallet.lyhoangvinh.com.utils;

import android.view.View;

import java.lang.ref.WeakReference;

public class Utils {
    public static void setClickable(View view, boolean clickable) {
        if (view != null) {
            view.setClickable(clickable);
        }
    }

    public static void setClickable(View view, boolean clickable, long delayedMillis) {
        if (view != null) {
            if (delayedMillis > 0) {
                WeakReference<View> weakView = new WeakReference<>(view);
                weakView.get().postDelayed(() -> {
                    if (weakView.get() != null) {
                        weakView.get().setClickable(clickable);
                    }
                }, delayedMillis);
            } else {
                view.setClickable(clickable);
            }
        }
    }
}
