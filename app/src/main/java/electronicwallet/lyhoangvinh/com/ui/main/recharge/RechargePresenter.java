package electronicwallet.lyhoangvinh.com.ui.main.recharge;

import android.content.Context;

import javax.inject.Inject;

import electronicwallet.lyhoangvinh.com.base.presenter.BasePresenter;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.di.scopes.PerFragment;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;

@PerFragment
public class RechargePresenter extends BasePresenter<RechargeView> {

    @Inject
    RechargePresenter(@ActivityContext Context context, DatabaseManager databaseManager) {
        super(context, databaseManager);

    }

}
