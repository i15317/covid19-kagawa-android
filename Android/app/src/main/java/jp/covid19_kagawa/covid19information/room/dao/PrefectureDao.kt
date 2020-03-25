package jp.covid19_kagawa.covid19information.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import jp.covid19_kagawa.covid19information.room.entity.PrefectureEntity

@Dao
interface PrefectureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entity: PrefectureEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entities: List<PrefectureEntity>): Completable

    @Query("select * from pref_name where class_code = :classCode")
    fun findByClassCode(classCode: String): Single<List<PrefectureEntity>>

    @Query("delete from pref_name")
    fun deleteAllDatas(): Completable
}