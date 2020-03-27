package jp.covid19_kagawa.covid19information

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AppLaunchChecker
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import jp.covid19_kagawa.covid19information.actioncreator.AreaActionCreator
import jp.covid19_kagawa.covid19information.room.database.JapanTopDatabase
import jp.covid19_kagawa.covid19information.room.database.PrefectureDatabase
import org.koin.android.ext.android.inject

val PREFERENCES_NAME = "jp.covid19_kagawa.covid19information_preferences"
val IS_FIRST_FLAG = "is_first_flag"
val IS_UPDATE_FLAG = "is_update_flag"
val DB_UPDATE_FLAG = "db_update_flag"

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    private val actionCreator: AreaActionCreator by inject()

    //    private var isFirstFlag: Boolean by preferences.boolean(true, IS_FIRST_FLAG)
//    private var isUpdateFlag: Boolean by preferences.boolean(true, IS_UPDATE_FLAG)
//    private var dbUpdateFlag: Boolean by preferences.boolean(false, DB_UPDATE_FLAG)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkFirstFlag()

        //preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_setting_area,
                R.id.navigation_setting_pref
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
                navController.navigate(R.id.navigation_setting_area)
                return false
            }
            else -> {
                return false
            }
        }
    }

    //更新時はデータベースの初期化
    private fun checkFirstFlag() {
        if (!AppLaunchChecker.hasStartedFromLauncher(applicationContext)) {
            JapanTopDatabase.getInstance().japanTopDao().evilDeleteAllData()
            PrefectureDatabase.getInstance().prefectureDao().evilDeleteAllData()
            actionCreator.initDatabase(this)
        }
        AppLaunchChecker.onActivityCreate(this)
    }
//        if (isFirstFlag) {
//            JapanTopDatabase.getInstance().japanTopDao().deleteAllDatas()
//            PrefectureDatabase.getInstance().prefectureDao().deleteAllDatas()
//            dbUpdateFlag = true
//            isFirstFlag = false
//        }
//        if (isUpdateFlag) {
//            JapanTopDatabase.getInstance().japanTopDao().deleteAllDatas()
//            PrefectureDatabase.getInstance().prefectureDao().deleteAllDatas()
//            dbUpdateFlag = true
//            isUpdateFlag = false
//        }
}
