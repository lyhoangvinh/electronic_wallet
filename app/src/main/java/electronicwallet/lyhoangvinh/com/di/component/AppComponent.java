package electronicwallet.lyhoangvinh.com.di.component;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import electronicwallet.lyhoangvinh.com.MyApplication;
import electronicwallet.lyhoangvinh.com.di.module.AppModule;
import electronicwallet.lyhoangvinh.com.di.module.DataModule;
import electronicwallet.lyhoangvinh.com.di.module.NetworkModule;
import electronicwallet.lyhoangvinh.com.di.module.ServiceModule;
import electronicwallet.lyhoangvinh.com.di.qualifier.ApplicationContext;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        ServiceModule.class,
        DataModule.class
})
public interface AppComponent {
    @ApplicationContext
    Context context();

    DatabaseManager getDataBaseManager();

    void inject(MyApplication application);
}
