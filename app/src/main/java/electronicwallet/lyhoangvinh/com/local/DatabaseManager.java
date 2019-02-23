package electronicwallet.lyhoangvinh.com.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import electronicwallet.lyhoangvinh.com.local.dao.MyContactDao;
import electronicwallet.lyhoangvinh.com.local.model.MyContact;


@Database(entities = {MyContact.class}, version = 1, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {

    public abstract MyContactDao getMyContact();
}
