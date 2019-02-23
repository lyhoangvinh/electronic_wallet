package electronicwallet.lyhoangvinh.com.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import electronicwallet.lyhoangvinh.com.local.DatabaseManager;
import electronicwallet.lyhoangvinh.com.local.dao.MyContactDao;

@Module
public class DataModule {

    private DatabaseManager databaseManager;

    public DataModule(Application application) {
        databaseManager = Room.databaseBuilder(application, DatabaseManager.class, "notes-db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    DatabaseManager providesRoomDatabase() {
        return databaseManager;
    }

    @Singleton
    @Provides
    MyContactDao providesMyContactDao(DatabaseManager databaseManager) {
        return databaseManager.getMyContact();
    }
}
