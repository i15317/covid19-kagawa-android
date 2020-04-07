package jp.covid19_kagawa.covid19information2.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jp.covid19_kagawa.covid19information2.room.dao.PrefectureDao
import jp.covid19_kagawa.covid19information2.room.entity.PrefectureEntity

//class PrefectureConverter {
//    @TypeConverter
//    fun fromPrefecture(value: Prefecture): String {
//        return value.prefCode.toString()
//    }
//
//    @TypeConverter
//    fun toPrefecture(value: String): Prefecture {
//        return Prefecture.valueOf(value)
//    }
//}

@Database(
    entities = [PrefectureEntity::class],
    version = PrefectureDatabase.DATABASE_VERSION
)
//@TypeConverters(PrefectureConverter::class)
abstract class PrefectureDatabase : RoomDatabase() {
    abstract fun prefectureDao(): PrefectureDao

    companion object {

        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "chocolate_pref.db"
        private lateinit var instance: PrefectureDatabase
        private var TEST_MODE = false
        fun init(context: Context) {
            if (TEST_MODE) {
                Room.inMemoryDatabaseBuilder(context, PrefectureDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()
                    .also { instance = it }
            } else {
                Room.databaseBuilder(
                    context,
                    PrefectureDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build()
                    .also { instance = it }
            }
        }

        fun getInstance() = instance
    }
}