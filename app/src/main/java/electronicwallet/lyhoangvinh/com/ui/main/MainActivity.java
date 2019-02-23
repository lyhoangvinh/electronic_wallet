package electronicwallet.lyhoangvinh.com.ui.main;

import electronicwallet.lyhoangvinh.com.base.activity.BaseSingleFragmentActivity;
import electronicwallet.lyhoangvinh.com.ui.main.phonenumber.PhoneNumberFragment;

public class MainActivity extends BaseSingleFragmentActivity<PhoneNumberFragment> {

    @Override
    protected PhoneNumberFragment createFragment() {
        return new PhoneNumberFragment();
    }
}
