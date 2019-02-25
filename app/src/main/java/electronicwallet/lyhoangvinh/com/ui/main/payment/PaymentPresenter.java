package electronicwallet.lyhoangvinh.com.ui.main.payment;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.interfaces.Item;
import electronicwallet.lyhoangvinh.com.base.presenter.BasePresenter;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.di.scopes.PerFragment;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;
import electronicwallet.lyhoangvinh.com.local.item.BankPaymentItem;
import electronicwallet.lyhoangvinh.com.local.item.DividerItem;
import electronicwallet.lyhoangvinh.com.local.item.PaymentDetailItem;
import electronicwallet.lyhoangvinh.com.local.item.TitleItem;
import electronicwallet.lyhoangvinh.com.local.model.Bank;

@PerFragment
public class PaymentPresenter extends BasePresenter<PaymentView> {

    @Inject
    public PaymentPresenter(@ActivityContext Context context, DatabaseManager databaseManager) {
        super(context, databaseManager);
    }

    List<Item> hasItem(int count, int money, Bank bank) {
        List<Item> items = new ArrayList<>();
        items.add(new TitleItem(context.getString(R.string.nguon_tien)));
        items.add(new BankPaymentItem(bank));
        items.add(new TitleItem(context.getString(R.string.chi_tiet_giao_dich)));
        items.add(new DividerItem());
        items.add(new PaymentDetailItem(count, money));
        items.add(new DividerItem());
        return items;
    }
}
