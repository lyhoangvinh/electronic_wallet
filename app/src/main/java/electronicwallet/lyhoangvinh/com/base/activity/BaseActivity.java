package electronicwallet.lyhoangvinh.com.base.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.interfaces.UiRefreshable;
import electronicwallet.lyhoangvinh.com.base.response.ErrorEntity;
import electronicwallet.lyhoangvinh.com.base.view.BaseView;
import electronicwallet.lyhoangvinh.com.di.InjectionHelper;
import electronicwallet.lyhoangvinh.com.di.component.ActivityComponent;
import electronicwallet.lyhoangvinh.com.di.component.DaggerActivityComponent;
import electronicwallet.lyhoangvinh.com.di.module.ActivityModule;
import electronicwallet.lyhoangvinh.com.utils.LogHelper;
import pub.devrel.easypermissions.EasyPermissions;

public abstract class BaseActivity extends AppCompatActivity implements BaseView, EasyPermissions.PermissionCallbacks  {

    private ActivityComponent mActivityComponent;

    private Dialog progress_dialog = null;

    public abstract int getLayout();

    public abstract void initialize(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initialize(savedInstanceState);
    }

    public void addFragment(@IdRes int res, Fragment fragment, @Nullable String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(res, fragment, tag)
                .commit();
    }

    protected void finishWithAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    protected void finished() {
        finish();
    }

    // activity component, activity may or may not need this
    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(InjectionHelper.getAppComponent(this))
                    .build();
        }
        return mActivityComponent;
    }

    public void showProgressDialog(boolean cancelable) {
        if (progress_dialog == null) {
            progress_dialog = new Dialog(this);
            progress_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progress_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progress_dialog.setContentView(R.layout.progress_dialog);
        }

        if (!progress_dialog.isShowing()) {
            progress_dialog.setCanceledOnTouchOutside(cancelable);
            progress_dialog.setCancelable(cancelable);
            progress_dialog.show();
        }

        if (!isNetworkAvailable(this)) {
            progress_dialog.dismiss();
        }
    }

    public void hideProgressDialog() {
        if (progress_dialog != null && progress_dialog.isShowing()) {
            progress_dialog.dismiss();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    @Override
    public void showProgress() {
        showProgressDialog(false);
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
        if (this instanceof UiRefreshable) {
            ((UiRefreshable)this).doneRefresh();
        }
    }

    @Override
    public void onError(ErrorEntity error) {
        showToastLongMessage(error.getMessage());
    }

    @Override
    public void setProgress(boolean show) {
        if (show) {
            showProgress();
        } else {
            hideProgress();
        }
    }


    public void showToastLongMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void showToastShortMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        showToastLongMessage(message);
    }

    //---------------------implements EasyPermissions.PermissionCallbacks-------------------------//

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (LogHelper.isLog())
            LogHelper.d("BaseActivity", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (LogHelper.isLog())
            LogHelper.d("BaseActivity", "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }

    //---------------------implements EasyPermissions.PermissionCallbacks-------------------------//
}
