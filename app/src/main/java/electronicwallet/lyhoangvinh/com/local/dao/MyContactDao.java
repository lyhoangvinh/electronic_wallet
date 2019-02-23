package electronicwallet.lyhoangvinh.com.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import electronicwallet.lyhoangvinh.com.local.model.MyContact;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MyContactDao {

    @Query("SELECT * FROM MyContact")
    LiveData<List<MyContact>> getAll();

    @Query("SELECT * FROM MyContact")
    Single<List<MyContact>> getAllContact();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MyContact> contact);

    @Delete
    void remove(List<MyContact> notes);

//    @Query("SELECT * FROM MyContact WHERE number or name LIKE :text")
//    Flowable<List<MyContact>> xxx(String text);

    @Query("SELECT * FROM MyContact WHERE number or name = :text")
    Flowable<List<MyContact>> search(String text);

    @Query("SELECT count(*) FROM MyContact where id LIKE :id")
    int isContact(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert2(List<MyContact> entities);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(List<MyContact> entities);

    @Query("DELETE FROM MyContact")
    void clearAll();

    default void upSert(List<MyContact> entities) {
        clearAll();
        insert2(entities);
        update(entities);
    }
}
