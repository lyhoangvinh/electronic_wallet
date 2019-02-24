package electronicwallet.lyhoangvinh.com.ui.main.contacts;

import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import electronicwallet.lyhoangvinh.com.base.presenter.BaseListPresenter;
import electronicwallet.lyhoangvinh.com.di.qualifier.ActivityContext;
import electronicwallet.lyhoangvinh.com.di.scopes.PerFragment;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;
import lyhoangvinh.com.myutil.androidutils.CommonUtils;

@PerFragment
class ContactsPresenter extends BaseListPresenter<ContactsView> {

    private final ContactAdapter adapter;

    @Inject
    ContactsPresenter(@ActivityContext Context context, DatabaseManager databaseManager) {
        super(context, databaseManager);
        adapter = new ContactAdapter(new ArrayList<>(), s -> {
            if (getView() != null){
                getView().openRecharger(s);
            }
        });
    }

    @Override
    protected void fetchData() {
        observeContact();
    }

    ContactAdapter getAdapter() {
        return adapter;
    }

    private void observeContact() {
        Transformations.map(getDatabaseManager().getMyContact().getAll(), input -> input).observe(getLifeCircleOwner(), contacts -> {
            if (getView() == null) {
                return;
            }
            if (contacts == null) {
                return;
            }
            adapter.setDataSetToAdapter(contacts);
            getView().hideProgress();
        });
    }

    void sendPhoneNumber(String number) {
        if (getView() == null) {
            return;
        }
        getView().showProgress();
        new Handler().postDelayed(() -> {
            if (!TextUtils.isEmpty(number)) {
                CommonUtils.hideSoftKeyboard(getContext());
                getView().success("OK");
            } else {
                getView().failed("Faild");
            }
            if (getView() != null) {
                getView().hideProgress();
            }
        }, 500);
    }

    void search(String text) {
        addRequestFollowable(true, getDatabaseManager().getMyContact().search(text), adapter::setDataSetToAdapter);
    }

    @Override
    public boolean canLoadMore() {
        return false;
    }
}
