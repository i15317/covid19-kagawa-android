package jp.covid19_kagawa.covid19information.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import jp.covid19_kagawa.covid19information.room.dao.JapanTopDao
import jp.covid19_kagawa.covid19information.room.entity.AreaEntity

@Database(entities = [AreaEntity::class], version = JapanTopDatabase.DATABASE_VERSION)
abstract class JapanTopDatabase : RoomDatabase() {
    abstract fun japanTopDao(): JapanTopDao

    companion object {
        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "chocolate_japan.db"
        private lateinit var instance: JapanTopDatabase
        private var TEST_MODE = false
        fun init(context: Context) {
            if (TEST_MODE) {
                Room.inMemoryDatabaseBuilder(context, JapanTopDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()
                    .also { instance = it }
            } else {
                Room.databaseBuilder(context, JapanTopDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build()
                    .also { instance = it }
            }
        }

        fun getInstance() = instance
    }
}

