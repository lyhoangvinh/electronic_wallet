package electronicwallet.lyhoangvinh.com.ui.main.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.fragment.BasePresenterFragment;

public class PaymentFragment extends BasePresenterFragment<PaymentView, PaymentPresenter> implements PaymentView {

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
    public boolean isRegisterEvenBus() {
        return false;
    }


}
