package electronicwallet.lyhoangvinh.com.ui.main.phonenumber;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.fragment.BasePresenterFragment;
import electronicwallet.lyhoangvinh.com.utils.NavigatorHelper;
import electronicwallet.lyhoangvinh.com.utils.Utils;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PhoneNumberFragment extends BasePresenterFragment<PhoneNumberView, PhoneNumberPresenter> implements PhoneNumberView {

    @BindView(R.id.edtNumber)
    EditText edtNumber;

    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    @Inject
    NavigatorHelper navigatorHelper;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public int getLayout() {
        return R.layout.fragment_phone_number;
    }

    @Override
    public boolean isEvenbus() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initialize(View view, Context ctx) {
        super.initialize(view, ctx);
        RxTextView.textChanges(edtNumber).debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    if (TextUtils.isEmpty(charSequence)) {
                        btnConfirm.setEnabled(false);
                    } else {
                        btnConfirm.setEnabled(true);
                    }
                });

        edtNumber.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                conFirmInThread();
                return true;
            }
            return false;
        });
    }

    @OnClick({R.id.btnConfirm, R.id.tvContacts})
    public void sendPhoneNumber(View v) {
        switch (v.getId()) {
            case R.id.btnConfirm:
                conFirmInThread();
                break;
            case R.id.tvContacts:
                navigatorHelper.navigateContactsFragment();
                break;
        }

    }

    @Override
    public void success(String message) {
        showMessage(message);
        navigatorHelper.navigateRechargeFragment();
    }

    @Override
    public void failed(String message) {
        showMessage(message);
    }

    private void conFirmInThread() {
        Utils.setClickable(btnConfirm, false);
        getPresenter().sendPhoneNumber(edtNumber.getText().toString());
        Utils.setClickable(btnConfirm, true, 300L);
    }

    @Override
    public boolean onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            return super.onBackPressed();
        }
        this.doubleBackToExitPressedOnce = true;
        showMessage("Please click BACK again to exit");
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        return doubleBackToExitPressedOnce;
    }
}
