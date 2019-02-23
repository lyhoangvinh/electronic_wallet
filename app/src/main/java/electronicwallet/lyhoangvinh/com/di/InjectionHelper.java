package electronicwallet.lyhoangvinh.com.di;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import electronicwallet.lyhoangvinh.com.MyApplication;
import electronicwallet.lyhoangvinh.com.di.component.AppComponent;


public class InjectionHelper {

    public static AppComponent getAppComponent(Context context) {
        return MyApplication.get(context).getAppComponent();
    }
    public static AppComponent getAppComponent(Fragment fragment) {
        return getAppComponent(fragment.getActivity());
    }

    public static AppComponent getAppComponent(Activity activity) {
        return MyApplication.get(activity).getAppComponent();
    }
}
