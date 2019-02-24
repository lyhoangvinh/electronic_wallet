package electronicwallet.lyhoangvinh.com.di.component;


import android.arch.lifecycle.LifecycleOwner;

import dagger.Component;
import electronicwallet.lyhoangvinh.com.di.module.FragmentModule;
import electronicwallet.lyhoangvinh.com.di.scopes.PerFragment;
import electronicwallet.lyhoangvinh.com.ui.main.contacts.ContactsFragment;
import electronicwallet.lyhoangvinh.com.ui.main.payment.PaymentFragment;
import electronicwallet.lyhoangvinh.com.ui.main.phonenumber.PhoneNumberFragment;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.RechargeFragment;

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
@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    LifecycleOwner lifeCycleOwner();
//
    void inject(PhoneNumberFragment fragment);
//
    void inject(ContactsFragment fragment);

    void inject(RechargeFragment fragment);

    void inject(PaymentFragment fragment);
}
