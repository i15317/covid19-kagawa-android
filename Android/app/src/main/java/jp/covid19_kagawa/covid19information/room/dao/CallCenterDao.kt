package jp.covid19_kagawa.covid19information.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import jp.covid19_kagawa.covid19information.room.entity.CallEntity

@Dao
interface CallCenterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entities: List<CallEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entity: CallEntity)

    @Query("SELECT count(*) FROM call_data")
    fun getDashboardItemSize(): Int

    @Query("SELECT * FROM call_data")
    fun getAllItem(): Single<List<CallEntity>>

    @Query("select * from call_data where opening_time=:flag")
    fun getItemsByOpeningFlag(flag: Boolean)

    @Query("select * from call_data where center_pref=:prefCode")
    fun getItemsByPrefectureCode(prefCode: String)

    @Query("delete from japan_top")
    fun deleteAllDatas(): Completable

}