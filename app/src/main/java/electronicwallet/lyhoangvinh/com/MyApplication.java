package electronicwallet.lyhoangvinh.com;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import electronicwallet.lyhoangvinh.com.di.component.AppComponent;
import electronicwallet.lyhoangvinh.com.di.component.DaggerAppComponent;
import electronicwallet.lyhoangvinh.com.di.module.AppModule;
import electronicwallet.lyhoangvinh.com.di.module.DataModule;
import electronicwallet.lyhoangvinh.com.di.module.NetworkModule;
import electronicwallet.lyhoangvinh.com.di.module.ServiceModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MyApplication extends Application {

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initFont();
    }

    // component
    protected void setupAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .serviceModule(new ServiceModule(this))
                .networkModule(new NetworkModule(this))
                .dataModule(new DataModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            setupAppComponent();
        }
        return appComponent;
    }


    public static MyApplication get(Activity activity) {
        return (MyApplication)activity.getApplication();
    }

    public static MyApplication get(Fragment fragment) {
        return get(fragment.getActivity());
    }

    public static MyApplication get(Context context) {
        return (MyApplication)context.getApplicationContext();
    }

    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getApplicationContext().getResources().getString(R.string.font_medium))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}

