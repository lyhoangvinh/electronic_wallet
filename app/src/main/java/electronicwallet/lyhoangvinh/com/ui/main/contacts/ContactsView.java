package electronicwallet.lyhoangvinh.com.ui.main.contacts;

import electronicwallet.lyhoangvinh.com.base.view.BaseView;

public interface ContactsView extends BaseView {
    void success(String message);

    void failed(String message);

    void openRecharger(String s);
}
