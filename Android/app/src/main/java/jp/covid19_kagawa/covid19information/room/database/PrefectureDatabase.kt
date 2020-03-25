package jp.digital_future.cameraxsample.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jp.digital_future.cameraxsample.room.dao.PrefectureDao
import jp.digital_future.cameraxsample.room.entity.PrefectureEntity

@Database(
    entities = [PrefectureEntity::class],
    version = PrefectureDatabase.DATABASE_VERSION
)
abstract class PrefectureDatabase : RoomDatabase() {

    abstract fun prefectureDao(): PrefectureDao

    companion object {

        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "chocolate_pref.db"
        private lateinit var instance: PrefectureDatabase
        var TEST_MODE = false
        fun init(context: Context) {
            if (PrefectureDatabase.TEST_MODE) {
                Room.inMemoryDatabaseBuilder(context, PrefectureDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()
                    .also { instance = it }
            } else {
                Room.databaseBuilder(
                    context,
                    PrefectureDatabase::class.java,
                    PrefectureDatabase.DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }

        fun getInstance() = instance
    }
}