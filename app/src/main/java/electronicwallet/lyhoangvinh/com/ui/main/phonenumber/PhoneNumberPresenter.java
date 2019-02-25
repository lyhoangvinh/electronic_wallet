package electronicwallet.lyhoangvinh.com.ui.main.phonenumber;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import javax.inject.Inject;

import electronicwallet.lyhoangvinh.com.base.presenter.BasePresenter;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.di.scopes.PerFragment;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;
import lyhoangvinh.com.myutil.androidutils.CommonUtils;

@PerFragment
class PhoneNumberPresenter extends BasePresenter<PhoneNumberView> {

    @Inject
    PhoneNumberPresenter(@ActivityContext Context context, DatabaseManager databaseManager) {
        super(context, databaseManager);
    }

    void sendPhoneNumber(String number) {
        if (getView() == null) {
            return;
        }
        getView().showProgress();
        new Handler().postDelayed(() -> {
            if (!TextUtils.isEmpty(number)) {
                CommonUtils.hideSoftKeyboard(getContext());
                getView().success(number);
            } else {
                getView().failed("Faild");
            }
            if (getView() != null) {
                getView().hideProgress();
            }
        }, 500);
    }
}
