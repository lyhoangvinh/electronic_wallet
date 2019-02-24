package electronicwallet.lyhoangvinh.com.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
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

    public static void setBackground(Context context, View view, @DrawableRes int id){
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(ContextCompat.getDrawable(context, id) );
        } else {
            view.setBackground(ContextCompat.getDrawable(context, id));
        }
    }
}
