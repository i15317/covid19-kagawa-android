package jp.digital_future.cameraxsample.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import jp.digital_future.cameraxsample.room.entity.AreaEntity

@Dao
interface JapanTopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entities: List<AreaEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entity: AreaEntity): Completable

    @Query("SELECT count(*) FROM japan_top")
    fun getDashboardItemSize(): Int

    @Query("SELECT * FROM japan_top")
    fun getAllItem(): Single<List<AreaEntity>>

    @Query("delete from japan_top")
    fun deleteAllDatas(): Completable

}