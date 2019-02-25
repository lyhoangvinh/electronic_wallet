package electronicwallet.lyhoangvinh.com.ui.main.contacts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.fragment.BaseSwipeRecyclerViewFragment;
import electronicwallet.lyhoangvinh.com.utils.NavigatorHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import lyhoangvinh.com.myutil.androidutils.AlertUtils;

public class ContactsFragment extends BaseSwipeRecyclerViewFragment<ContactAdapter, ContactsView, ContactsPresenter> implements ContactsView {

    @BindView(R.id.edtSearch)
    EditText edtSearch;

    @Inject
    NavigatorHelper navigatorHelper;

    @Override
    protected int getLayout() {
        return R.layout.fragment_contacts;
    }

    @Override
    public boolean isRegisterEvenBus() {
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
        RxTextView.textChanges(edtSearch).debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    if (TextUtils.isEmpty(charSequence.toString())) {
                        refreshWithUi(300);
                    } else {
                        getPresenter().search(charSequence.toString());
                    }
                });
    }

    @OnClick(R.id.imvBack)
    public void back() {
        onBackPressed();
    }

    @NonNull
    @Override
    protected ContactAdapter createAdapter() {
        return getPresenter().getAdapter();
    }

    @Override
    public boolean onBackPressed() {
        navigatorHelper.navigatePhoneNumberFragment();
        return true;
    }

    @Override
    public void success(String message) {
        navigatorHelper.navigateRechargeFragment(message);
    }

    @Override
    public void failed(String message) {
        showMessage(message);
    }

    @Override
    public void openRecharger(String s) {
        AlertUtils.showAlertDialog(getActivity(), getString(R.string.xac_nhan),
                String.format(getString(R.string.dung_sdt), s),
                getString(R.string.dialog_ok), getString(R.string.dialog_cancel), (dialogInterface, i) ->
                        getPresenter().sendPhoneNumber(s), null);
    }
}
