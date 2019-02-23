package electronicwallet.lyhoangvinh.com.ui.splash;

import android.content.Context;
import android.os.Handler;

import java.util.List;

import javax.inject.Inject;

import electronicwallet.lyhoangvinh.com.base.presenter.BasePresenter;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.di.scopes.PerActivity;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;
import electronicwallet.lyhoangvinh.com.local.model.MyContact;

@PerActivity
class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    SplashPresenter(@ActivityContext Context context, DatabaseManager databaseManager) {
        super(context, databaseManager);
    }

    void insertContact(List<MyContact> list) {
        getDatabaseManager().getMyContact().upSert(list);
        new Handler().postDelayed(() -> {
            if (getView() == null) {
                return;
            }
            getView().openPhoneNumberPage();
        }, 1000);
    }
}
