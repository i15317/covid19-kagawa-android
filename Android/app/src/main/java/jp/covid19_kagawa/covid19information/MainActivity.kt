package jp.covid19_kagawa.covid19information

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import jp.covid19_kagawa.covid19information.actioncreator.AreaActionCreator
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }
    private lateinit var navView: BottomNavigationView
    private lateinit var navDrawer: NavigationView
    private val actionCreator: AreaActionCreator by inject()
    private lateinit var appNavBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkFirstFlag()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navDrawer = findViewById(R.id.nav_drawer)

        navController.navigatorProvider += ChromeCustomTabsNavigator(this)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_setting_area,
                R.id.navigation_setting_pref,
                R.id.navigation_news,
                R.id.navigation_about
            )
        )
        appNavBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_setting_area,
                R.id.navigation_setting_pref,
                R.id.navigation_news
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        setupActionBarWithNavController(navController, appNavBarConfiguration)
        navView.setupWithNavController(navController)
        navDrawer.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appNavBarConfiguration) || super.onSupportNavigateUp()
    }

    //更新時はデータベースの初期化
    private fun checkFirstFlag() {
        actionCreator.updateDatabase(this)
    }
}
