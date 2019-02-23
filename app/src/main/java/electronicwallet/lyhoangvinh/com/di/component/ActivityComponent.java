package electronicwallet.lyhoangvinh.com.di.component;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v4.app.FragmentManager;

import dagger.Component;
import electronicwallet.lyhoangvinh.com.di.module.ActivityModule;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityFragmentManager;
import electronicwallet.lyhoangvinh.com.di.scopes.PerActivity;
import electronicwallet.lyhoangvinh.com.ui.main.MainActivity;
import electronicwallet.lyhoangvinh.com.ui.splash.SplashActivity;

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ActivityFragmentManager
    FragmentManager defaultFragmentManager();

    LifecycleOwner lifeCycleOwner();

    void inject(MainActivity activity);

    void inject(SplashActivity activity);

}
