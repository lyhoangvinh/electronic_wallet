package electronicwallet.lyhoangvinh.com.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.activity.BaseSingleFragmentActivity;
import electronicwallet.lyhoangvinh.com.ui.main.phonenumber.PhoneNumberFragment;

public class MainActivity extends BaseSingleFragmentActivity<PhoneNumberFragment> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();
    }

    @Override
    protected PhoneNumberFragment createFragment() {
        // Transition for PhoneNumberFragment
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        PhoneNumberFragment phoneNumberFragment = new PhoneNumberFragment();
        phoneNumberFragment.setReenterTransition(slideTransition);
        phoneNumberFragment.setExitTransition(slideTransition);
        phoneNumberFragment.setSharedElementEnterTransition(new ChangeBounds());
        return phoneNumberFragment;
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }


}
