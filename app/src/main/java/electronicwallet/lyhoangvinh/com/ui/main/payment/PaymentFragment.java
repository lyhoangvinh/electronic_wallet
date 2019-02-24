package electronicwallet.lyhoangvinh.com.ui.main.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.fragment.BasePresenterFragment;
import electronicwallet.lyhoangvinh.com.constants.Constants;
import electronicwallet.lyhoangvinh.com.events.MoneyEvent;
import lyhoangvinh.com.myutil.navigation.NavigationUtils;

public class PaymentFragment extends BasePresenterFragment<PaymentView, PaymentPresenter> implements PaymentView {

    public static PaymentFragment newInstance(@Nullable MoneyEvent event) {
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
    public boolean isRegisterEvenBus() {
        return false;
    }


}
