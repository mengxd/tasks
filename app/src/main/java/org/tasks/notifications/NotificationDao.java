package org.tasks.notifications;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface NotificationDao {
    @Query("SELECT * FROM notification")
    List<Notification> getAll();

    @Query("SELECT * FROM notification ORDER BY timestamp DESC")
    List<Notification> getAllOrdered();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Notification> notifications);

    @Query("SELECT COUNT(*) FROM notification")
    int count();

    @Query("DELETE FROM notification WHERE task = :taskId")
    void delete(long taskId);

    @Query("SELECT MAX(timestamp) FROM notification")
    long latestTimestamp();
}
