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
import electronicwallet.lyhoangvinh.com.local.model.Bank;
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

        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank("https://www.usbank.com/dam/images/illustration/checking-easy-illustration.png", "Linked account", false));
        banks.add(new Bank("https://cdn3.iconfinder.com/data/icons/business-and-seo-1-1/512/3-512.png", "International card", false));
        banks.add(new Bank("https://www.miifotos.com/thumbs/GaiCMhh2zQuJeGHJy8Y4P2xyw78VCoLxtMy70PTYHXrquACp5K3Zxhvcf37RSC8WXLdtW0zVtvSnCs9RzhwJ7A.jpg", "Domestic card", false));
        banks.add(new Bank("https://www.fnb.co.za/03images/promotions/eWallet/illi.png", "eWallet", false));

        items.add(new BankItem(context.getString(R.string.chon_phuong_thuoc_thanh_toan), "->", banks));
        items.add(new TitleItem(context.getString(R.string.menh_gia_the)));
        items.add(new MoneyItem(moneys));
        items.add(new DividerItem());
        items.add(new TotalItem());
        items.add(new DividerItem());
        return items;
    }
}
