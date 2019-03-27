package electronicwallet.lyhoangvinh.com.utils;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.local.model.PaymentData;
import electronicwallet.lyhoangvinh.com.ui.main.MainActivity;
import electronicwallet.lyhoangvinh.com.ui.main.contacts.ContactsFragment;
import electronicwallet.lyhoangvinh.com.ui.main.payment.PaymentFragment;
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

    public void navigateMainActivity(Activity ctx) {
        final Pair<View, String>[] pairs = TransitionHelper2.createSafeTransitionParticipants(ctx, false);
        Intent intent = new Intent(ctx, MainActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ctx, pairs);
        ctx.startActivity(intent, options.toBundle());
        ctx.finish();
    }

    public void navigateContactsFragment() {
        mNavigator.replaceFragment(R.id.container, new ContactsFragment());
    }

    public void navigateContactsFragment(Activity activity) {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(activity.getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        ContactsFragment contactsFragment = new ContactsFragment();
        contactsFragment.setReenterTransition(slideTransition);
        contactsFragment.setExitTransition(slideTransition);
        contactsFragment.setSharedElementEnterTransition(new ChangeBounds());
        mNavigator.replaceFragment(R.id.container, contactsFragment);

    }

    public void navigatePhoneNumberFragment() {
        mNavigator.replaceFragment(R.id.container, new PhoneNumberFragment());
    }

    public void navigateRechargeFragment(String phone) {
        mNavigator.replaceFragment(R.id.container, RechargeFragment.newInstance(phone));
    }

    public void navigateRechargeFragment() {
        mNavigator.replaceFragment(R.id.container, new RechargeFragment());
    }

    public void navigatePaymentFragment(PaymentData event) {
        mNavigator.replaceFragment(R.id.container, PaymentFragment.newInstance(event));
    }
}
