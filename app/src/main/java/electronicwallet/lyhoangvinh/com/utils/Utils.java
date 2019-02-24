package electronicwallet.lyhoangvinh.com.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import electronicwallet.lyhoangvinh.com.R;

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

    public static void setBackground(Context context, View view, @DrawableRes int id) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(ContextCompat.getDrawable(context, id));
        } else {
            view.setBackground(ContextCompat.getDrawable(context, id));
        }
    }

    public static String formatVnCurrence(String price) {
        NumberFormat format = new DecimalFormat("#,##0.00");// #,##0.00 ¤ (¤:// Currency symbol)
        format.setCurrency(Currency.getInstance(Locale.US));//Or default locale

        price = (!TextUtils.isEmpty(price)) ? price : "0";
        price = price.trim();
        price = format.format(Double.parseDouble(price));
        price = price.replaceAll(",", "\\.");

        if (price.endsWith(".00")) {
            int centsIndex = price.lastIndexOf(".00");
            if (centsIndex != -1) {
                price = price.substring(0, centsIndex);
            }
        }
        price = String.format("%s đ", price);
        return price;
    }

    public static void setImageFromUrl(String url, ImageView imv) {
        Glide.with(imv.getContext()).load(url).apply(new RequestOptions().centerCrop()
                .placeholder(R.drawable.icons_mobile_payment_64)
                .error(R.drawable.icons_mobile_payment_64))
                .into(imv);
    }

    public static String setOnChangeHtmlColor(String color, String text) {
        return "<font color='" + color + "'>(" + text + ")</font>";
    }
}
