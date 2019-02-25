package electronicwallet.lyhoangvinh.com.ui.main.recharge;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.fragment.BasePresenterFragment;
import electronicwallet.lyhoangvinh.com.constants.Constants;
import electronicwallet.lyhoangvinh.com.events.MoneyEvent;
import electronicwallet.lyhoangvinh.com.local.model.Bank;
import electronicwallet.lyhoangvinh.com.local.model.PaymentData;
import electronicwallet.lyhoangvinh.com.utils.NavigatorHelper;
import electronicwallet.lyhoangvinh.com.utils.Utils;
import lyhoangvinh.com.myutil.androidutils.AlertUtils;
import lyhoangvinh.com.myutil.navigation.NavigationUtils;

public class RechargeFragment extends BasePresenterFragment<RechargeView, RechargePresenter> implements RechargeView {

    public static RechargeFragment newInstance(@Nullable String event) {
        return NavigationUtils.createFragmentInstance(new RechargeFragment(), bundle -> {
            bundle.putString(Constants.EXTRA_DATA, event);
        });
    }

    private String phoneNumber;

    private int count = 1;

    private int money = 10000;

    private Bank bank;

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
    public boolean isRegisterEvenBus() {
        return true;
    }

    @Override
    protected void initialize(View view, Context ctx) {
        super.initialize(view, ctx);
        if (getArguments() != null) {
            phoneNumber = getArguments().getString(Constants.EXTRA_DATA);
        }
        tvTitle.setText(getString(R.string.mua_ma_the));
        RechargeItemAdapter adapter = new RechargeItemAdapter();
        rcv.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false));
        rcv.setAdapter(adapter);
        adapter.setData(getPresenter().hasItem());
        tvRecharger.setText(Utils.formatVnCurrence(String.valueOf(money)));
    }

    @OnClick({R.id.imvBack, R.id.btnConfirm})
    public void onClicks(View v) {
        switch (v.getId()) {
            case R.id.imvBack:
                onBackPressed();
                break;
            case R.id.btnConfirm:
                confirm();
                break;
        }
    }

    private void confirm() {
        if (bank != null) {
            if (!TextUtils.isEmpty(phoneNumber) && phoneNumber.startsWith("090") && bank.getTitle().equals("Linked account")) {
                AlertUtils.showAlertDialog(getActivity(), getString(R.string.thong_bao_failed_linked));
            } else {
                navigatorHelper.navigatePaymentFragment(new PaymentData(count, money, bank));
            }
        } else {
            showMessage(getString(R.string.vui_long));
        }
    }

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

        if (event.getBank() != null) {
            bank = event.getBank();
        }

        int sum = money * count;

        tvRecharger.setText(Utils.formatVnCurrence(String.valueOf(sum)));
    }

    @Override
    public boolean onBackPressed() {
        navigatorHelper.navigatePhoneNumberFragment();
        return true;
    }
}
