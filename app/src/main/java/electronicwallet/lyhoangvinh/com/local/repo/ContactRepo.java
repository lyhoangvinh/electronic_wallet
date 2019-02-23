package electronicwallet.lyhoangvinh.com.local.repo;

import android.arch.lifecycle.LifecycleOwner;

import electronicwallet.lyhoangvinh.com.base.data.BaseRepo;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;

public class ContactRepo extends BaseRepo {

    public ContactRepo(LifecycleOwner owner, DatabaseManager databaseManager) {
        super(owner, databaseManager);
    }
}
