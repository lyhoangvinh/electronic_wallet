package electronicwallet.lyhoangvinh.com.utils;


import android.content.Intent;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.ui.main.MainActivity;
import electronicwallet.lyhoangvinh.com.ui.main.contacts.ContactsFragment;
import electronicwallet.lyhoangvinh.com.ui.main.phonenumber.PhoneNumberFragment;
import electronicwallet.lyhoangvinh.com.ui.main.recharge.RechargeFragment;
import lyhoangvinh.com.myutil.navigation.ActivityNavigator;
import lyhoangvinh.com.myutil.navigation.FragmentNavigator;
import lyhoangvinh.com.myutil.navigation.Navigator;

public class NavigatorHelper {

    private static final String TAG_DOC = "TAG_DOC";

    private Navigator mNavigator;

    public NavigatorHelper(FragmentNavigator mNavigator) {
        this.mNavigator = mNavigator;
    }

    public NavigatorHelper(ActivityNavigator mNavigator) {
        this.mNavigator = mNavigator;
    }

    public NavigatorHelper(Navigator mNavigator) {
        this.mNavigator = mNavigator;
    }

    public void navigateMainActivity(boolean clearAllPrevious) {
        mNavigator.startActivity(MainActivity.class, intent -> {
            if (clearAllPrevious) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
        if (clearAllPrevious) {
            mNavigator.finishActivity();
        }
    }

    public void navigateContactsFragment() {
        mNavigator.replaceFragment(R.id.container, new ContactsFragment());
    }

    public void navigatePhoneNumberFragment() {
        mNavigator.replaceFragment(R.id.container, new PhoneNumberFragment());
    }

    public void navigateRechargeFragment() {
        mNavigator.replaceFragment(R.id.container, new RechargeFragment());
    }


}
