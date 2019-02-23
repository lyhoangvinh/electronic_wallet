package electronicwallet.lyhoangvinh.com.di.module;

import android.content.Context;

import dagger.Module;
import electronicwallet.lyhoangvinh.com.di.qualifier.ApplicationContext;

@Module
public class ServiceModule {
    private final Context mContext;

    public ServiceModule(@ApplicationContext Context context) {
        this.mContext = context;
    }


}
