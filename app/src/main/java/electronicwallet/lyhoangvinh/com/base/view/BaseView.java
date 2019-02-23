package electronicwallet.lyhoangvinh.com.base.view;


import electronicwallet.lyhoangvinh.com.base.response.ErrorEntity;

public interface BaseView {
    void showProgress();

    void hideProgress();

    void onError(ErrorEntity error);

    void setProgress(boolean show);

    void showMessage(String message);
}
