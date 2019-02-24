package electronicwallet.lyhoangvinh.com.ui.main.payment;

import android.content.Context;

import javax.inject.Inject;

import electronicwallet.lyhoangvinh.com.base.presenter.BasePresenter;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.di.scopes.PerFragment;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;

@PerFragment
public class PaymentPresenter extends BasePresenter<PaymentView> {

    @Inject
    public PaymentPresenter(@ActivityContext Context context, DatabaseManager databaseManager) {
        super(context, databaseManager);
    }

}
