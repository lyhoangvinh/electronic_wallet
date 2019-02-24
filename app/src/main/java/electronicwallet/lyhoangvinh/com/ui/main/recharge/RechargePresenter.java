package electronicwallet.lyhoangvinh.com.ui.main.recharge;

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
import electronicwallet.lyhoangvinh.com.local.model.BankItem;
import electronicwallet.lyhoangvinh.com.local.model.DividerItem;
import electronicwallet.lyhoangvinh.com.local.model.Money;
import electronicwallet.lyhoangvinh.com.local.model.MoneyItem;
import electronicwallet.lyhoangvinh.com.local.model.TitleItem;
import electronicwallet.lyhoangvinh.com.local.model.TotalItem;

@PerFragment
public class RechargePresenter extends BasePresenter<RechargeView> {

    @Inject
    RechargePresenter(@ActivityContext Context context, DatabaseManager databaseManager) {
        super(context, databaseManager);

    }

    List<Item> hasItem() {
        List<Item> items = new ArrayList<>();
        List<Money> moneys = new ArrayList<>();
        moneys.add(new Money(10000, true));
        moneys.add(new Money(20000, false));
        moneys.add(new Money(30000, false));
        moneys.add(new Money(50000, false));
        moneys.add(new Money(100000, false));
        moneys.add(new Money(200000, false));
        moneys.add(new Money(300000, false));
        moneys.add(new Money(500000, false));
        items.add(new TitleItem(context.getString(R.string.menh_gia_the)));
        items.add(new MoneyItem(moneys));
        items.add(new DividerItem());
        items.add(new TotalItem());
        items.add(new DividerItem());
        items.add(new BankItem(context.getString(R.string.chon_phuong_thuoc_thanh_toan), "->"));
        return items;
    }
}
