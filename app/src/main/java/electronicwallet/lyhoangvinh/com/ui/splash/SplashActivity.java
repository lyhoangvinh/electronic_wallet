package electronicwallet.lyhoangvinh.com.ui.splash;

import android.Manifest;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import electronicwallet.lyhoangvinh.com.R;
import electronicwallet.lyhoangvinh.com.base.activity.BasePresenterActivity;
import electronicwallet.lyhoangvinh.com.local.model.MyContact;
import electronicwallet.lyhoangvinh.com.utils.NavigatorHelper;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;


public class SplashActivity extends BasePresenterActivity<SplashView, SplashPresenter> implements SplashView, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int RC_PERMISSIONS_CONTACTS = 20;

    @Inject
    NavigatorHelper navigatorHelper;

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initialize(Bundle savedInstanceState) {
        activityComponent().inject(this);
        initialize();
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] perms = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};
        if (EasyPermissions.hasPermissions(this, perms)) {
            getSupportLoaderManager().initLoader(1, null, this);
        } else {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, RC_PERMISSIONS_CONTACTS, perms)
                            .setRationale(R.string.permission_rationale_file_access)
                            .setPositiveButtonText(R.string.action_allow)
                            .setNegativeButtonText(R.string.action_deny)
                            .build());
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        Uri CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        List<MyContact> myContacts = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
            String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
            MyContact myContact = new MyContact();
            myContact.setName(cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)));
            myContact.setNumber(cursor.getString(cursor.getColumnIndex(NUMBER)));
            myContacts.add(myContact);
            cursor.moveToNext();
        }
        if (getPresenter() != null) {
            getPresenter().insertContact(myContacts);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void openPhoneNumberPage() {
        navigatorHelper.navigateMainActivity(true);
    }
}
