package electronicwallet.lyhoangvinh.com.di.module;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import electronicwallet.lyhoangvinh.com.base.activity.BaseActivity;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityFragmentManager;
import electronicwallet.lyhoangvinh.com.utils.NavigatorHelper;
import lyhoangvinh.com.myutil.navigation.ActivityNavigator;
import lyhoangvinh.com.myutil.navigation.Navigator;

@Module
public class ActivityModule {

    private final BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        mActivity = activity;
    }

    @Provides
    protected FragmentActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    protected Context provideContext() {
        return mActivity;
    }

    @Provides
    @ActivityFragmentManager
    protected FragmentManager provideFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }


    @Provides
    protected Navigator provideNavigator() { return new ActivityNavigator(mActivity); }

    @Provides
    protected NavigatorHelper provideNavigatorHelper(Navigator navigator) {
        return new NavigatorHelper(navigator);
    }

    @Provides
    protected LifecycleOwner provideLifeCycleOwner() {
        return mActivity;
    }
}