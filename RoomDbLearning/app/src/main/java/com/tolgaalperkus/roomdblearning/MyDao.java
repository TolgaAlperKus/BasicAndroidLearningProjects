package com.tolgaalperkus.roomdblearning;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable addUser(final User user);

    @Query("SELECT * FROM users ")
    public  Single<List<User>>  getUsers();

    @Delete
    public Completable deleteUser(final User user);

}
