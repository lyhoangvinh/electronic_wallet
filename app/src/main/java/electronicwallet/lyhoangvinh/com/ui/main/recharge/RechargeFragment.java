package electronicwallet.lyhoangvinh.com.ui.main.recharge;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.fragment.BasePresenterFragment;
import electronicwallet.lyhoangvinh.com.base.interfaces.Item;
import electronicwallet.lyhoangvinh.com.events.MoneyEvent;
import electronicwallet.lyhoangvinh.com.local.model.BankItem;
import electronicwallet.lyhoangvinh.com.local.model.DividerItem;
import electronicwallet.lyhoangvinh.com.local.model.Money;
import electronicwallet.lyhoangvinh.com.local.model.MoneyItem;
import electronicwallet.lyhoangvinh.com.local.model.TitleItem;
import electronicwallet.lyhoangvinh.com.local.model.TotalItem;
import electronicwallet.lyhoangvinh.com.utils.NavigatorHelper;

public class RechargeFragment extends BasePresenterFragment<RechargeView, RechargePresenter> implements RechargeView {

    @Inject
    NavigatorHelper navigatorHelper;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvRecharger)
    TextView tvRecharger;

    @BindView(R.id.rcv)
    RecyclerView rcv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_recharge;
    }

    @Override
    public boolean isEvenbus() {
        return true;
    }

    @Override
    protected void initialize(View view, Context ctx) {
        super.initialize(view, ctx);
        tvTitle.setText(getString(R.string.mua_ma_the));
        RechargeItemAdapter adapter = new RechargeItemAdapter();
        rcv.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(adapter);
        adapter.setData(hasItem());
    }

    @OnClick(R.id.imvBack)
    public void back() {
        onBackPressed();
    }

    private List<Item> hasItem() {
        List<Item> items = new ArrayList<>();
        List<Money> moneys = new ArrayList<>();
        moneys.add(new Money(10000, "10.000", true));
        moneys.add(new Money(20000, "20.000", false));
        moneys.add(new Money(30000, "30.000", false));
        moneys.add(new Money(50000, "50.000", false));
        moneys.add(new Money(100000, "100.000", false));
        moneys.add(new Money(200000, "200.000", false));
        moneys.add(new Money(300000, "300.000", false));
        moneys.add(new Money(500000, "500.000", false));
        items.add(new TitleItem(getString(R.string.menh_gia_the)));
        items.add(new MoneyItem(moneys));
        items.add(new DividerItem());
        items.add(new TotalItem());
        items.add(new DividerItem());
        items.add(new BankItem(getString(R.string.chon_phuong_thuoc_thanh_toan), "->"));
        return items;
    }

    private int count = 1;
    private int money = 0;

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MoneyEvent event) {
        if (event == null)
            return;

        if (event.getMoney() != null) {
            money = event.getMoney().getPrice();
        }

        if (event.getTotal() != null) {
            count = event.getTotal().getCount();
        }

        int sum = money * count;

        tvRecharger.setText(String.format(getString(R.string.money), String.valueOf(sum)));
    }

    @Override
    public boolean onBackPressed() {
        navigatorHelper.navigatePhoneNumberFragment();
        return true;
    }
}
