package electronicwallet.lyhoangvinh.com.di.module;


import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import electronicwallet.lyhoangvinh.com.base.fragment.BaseFragment;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityFragmentManager;
import electronicwallet.lyhoangvinh.com.di.qualifier.ChildFragmentManager;
import electronicwallet.lyhoangvinh.com.di.scopes.PerFragment;
import electronicwallet.lyhoangvinh.com.utils.NavigatorHelper;
import lyhoangvinh.com.myutil.navigation.ActivityNavigator;
import lyhoangvinh.com.myutil.navigation.ChildFragmentNavigator;
import lyhoangvinh.com.myutil.navigation.FragmentNavigator;
import lyhoangvinh.com.myutil.navigation.Navigator;


@Module
public class FragmentModule {

    private final BaseFragment mFragment;

    public FragmentModule(BaseFragment fragment) {
        mFragment = fragment;
    }

    @PerFragment
    @Provides
    Fragment provideFragment() {
        return mFragment;
    }

    @Provides
    @PerFragment
    @ChildFragmentManager
    FragmentManager provideChildFragmentManager() { return mFragment.getChildFragmentManager(); }


    @Provides
    FragmentActivity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @ActivityFragmentManager
    FragmentManager provideFragmentManager() {
        return mFragment.getActivity().getSupportFragmentManager();
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mFragment.getContext();
    }

    @Provides
    @PerFragment
    FragmentNavigator provideFragmentNavigator() { return new ChildFragmentNavigator(mFragment); }

    @Provides
    @PerFragment
    NavigatorHelper provideNavigatorHelper(FragmentNavigator navigator) {
        return new NavigatorHelper(navigator);
    }

    @Provides
    Navigator provideNavigator() {
        return new ActivityNavigator(mFragment.getActivity());
    }

    @Provides
    LifecycleOwner provideLifeCycleOwner() {
        return mFragment;
    }
}
