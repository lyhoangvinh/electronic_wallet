package electronicwallet.lyhoangvinh.com.di.module;


import android.content.Context;

import dagger.Module;
import electronicwallet.lyhoangvinh.com.di.qualifier.ApplicationContext;

@Module
public class NetworkModule {

    protected Context context;

    public NetworkModule(@ApplicationContext Context context) {
        this.context = context;
    }

}
