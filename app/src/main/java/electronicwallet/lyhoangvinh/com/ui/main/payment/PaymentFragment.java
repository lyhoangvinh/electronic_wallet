package electronicwallet.lyhoangvinh.com.ui.main.payment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.fragment.BasePresenterFragment;
import electronicwallet.lyhoangvinh.com.constants.Constants;
import electronicwallet.lyhoangvinh.com.local.model.PaymentData;
import electronicwallet.lyhoangvinh.com.utils.NavigatorHelper;
import lyhoangvinh.com.myutil.navigation.NavigationUtils;

public class PaymentFragment extends BasePresenterFragment<PaymentView, PaymentPresenter> implements PaymentView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.rcv)
    RecyclerView rcv;

    private PaymentData data;

    @Inject
    NavigatorHelper navigatorHelper;

    public static PaymentFragment newInstance(@Nullable PaymentData event) {
        return NavigationUtils.createFragmentInstance(new PaymentFragment(), bundle -> {
            bundle.putParcelable(Constants.EXTRA_DATA, event);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_payment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Override
    protected void initialize(View view, Context ctx) {
        super.initialize(view, ctx);
        tvTitle.setText(getString(R.string.thanh_toan_an_toan));
        if (getArguments() != null) {
            data = getArguments().getParcelable(Constants.EXTRA_DATA);
            PaymentItemAdapter adapter = new PaymentItemAdapter();
            adapter.setData(getPresenter().hasItem(data.getCount(), data.getMoney(), data.getBank()));
            rcv.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false));
            rcv.setAdapter(adapter);
        }
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

    }

    @Override
    public boolean isRegisterEvenBus() {
        return false;
    }


    @Override
    public boolean onBackPressed() {
        navigatorHelper.navigateRechargeFragment();
        return true;
    }
}
