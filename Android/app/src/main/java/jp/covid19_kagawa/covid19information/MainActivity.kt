package jp.covid19_kagawa.covid19information

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import boolean
import com.google.android.material.bottomnavigation.BottomNavigationView
import jp.digital_future.cameraxsample.room.database.JapanTopDatabase
import jp.digital_future.cameraxsample.room.database.PrefectureDatabase

private val PREFERENCES_NAME = "jp.covid19_kagawa.covid19information_preferences"
private val IS_FIRST_FLAG = "is_first_flag"
private val IS_UPDATE_FLAG = "is_update_flag"
val DB_UPDATE_FLAG = "db_update_flag"

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    private val preferences by lazy { getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE) }
    private var isFirstFlag: Boolean by preferences.boolean(true, IS_FIRST_FLAG)
    private var isUpdateFlag: Boolean by preferences.boolean(true, IS_UPDATE_FLAG)
    private var dbUpdateFlag: Boolean by preferences.boolean(false, DB_UPDATE_FLAG)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkFirstFlag()
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_setting -> {
                navView.clearFocus()
                navView.clearAnimation()
                navController.navigate(R.id.navigation_settings)
                return true
            }
            else -> {
                return true
            }
        }
    }

    //更新時はデータベースの初期化
    private fun checkFirstFlag() {
        if (isFirstFlag) {
            JapanTopDatabase.getInstance().japanTopDao().deleteAllDatas()
            PrefectureDatabase.getInstance().prefectureDao().deleteAllDatas()
            dbUpdateFlag = true
            isFirstFlag = false
        }
        if (isUpdateFlag) {
            JapanTopDatabase.getInstance().japanTopDao().deleteAllDatas()
            PrefectureDatabase.getInstance().prefectureDao().deleteAllDatas()
            dbUpdateFlag = true
            isUpdateFlag = false
        }
    }
}
