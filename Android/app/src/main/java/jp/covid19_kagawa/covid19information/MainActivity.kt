package jp.covid19_kagawa.covid19information

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.*
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.OnSuccessListener
import com.google.android.play.core.tasks.TaskExecutors
import jp.covid19_kagawa.covid19information.ChromeCustomTabsNavigatorDirections.Companion.actionGlobalToChrome
import jp.covid19_kagawa.covid19information.actioncreator.AreaActionCreator
import jp.covid19_kagawa.covid19information.data.repository.PreferenceRepository
import org.koin.android.ext.android.inject
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
        const val REQUEST_UPDATE_CODE = 1
    }

    private lateinit var installStateUpdatedListener: InstallStateUpdatedListener
    private lateinit var appUpdateManager: AppUpdateManager
    private lateinit var playServiceExecutor: Executor

    private val preferenceRepository: PreferenceRepository by inject()
    private val actionCreator: AreaActionCreator by inject()

    private lateinit var navView: BottomNavigationView
    private lateinit var navDrawer: NavigationView
    private lateinit var appNavBarConfiguration: AppBarConfiguration
    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playServiceExecutor = TaskExecutors.MAIN_THREAD
        //アップデートチェック
        updateChecker()
        //データベースの更新
        checkFirstFlag()
        //ビューのセットアップ
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
        navDrawer.setNavigationItemSelectedListener {
            handleNavigation(it.itemId)
        }

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

    private fun updateChecker() {

        appUpdateManager = AppUpdateManagerFactory.create(this)
        installStateUpdatedListener = InstallStateUpdatedListener { installState ->
            when (installState.installStatus()) {
                InstallStatus.DOWNLOADED -> {
                    Log.d(TAG, "Downloaded")
                    updaterDownloadCompleted()
                }
                InstallStatus.INSTALLED -> {
                    Log.d(TAG, "Installed")
                    appUpdateManager.unregisterListener(installStateUpdatedListener)
                }
                else -> {
                    Log.d(TAG, "installStatus = " + installState.installStatus())
                }
            }
        }
        appUpdateManager.registerListener(installStateUpdatedListener)

        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener(
            playServiceExecutor,
            OnSuccessListener { appUpdateInfo ->
                when (appUpdateInfo.updateAvailability()) {
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        val updateTypes = arrayOf(AppUpdateType.FLEXIBLE, AppUpdateType.IMMEDIATE)
                        run loop@{
                            updateTypes.forEach { type ->
                                if (appUpdateInfo.isUpdateTypeAllowed(type)) {
                                    appUpdateManager.startUpdateFlowForResult(
                                        appUpdateInfo,
                                        type,
                                        this,
                                        REQUEST_UPDATE_CODE
                                    )
                                    return@loop
                                }
                            }
                        }
                    }
                    else -> {
                        Log.d(TAG, "updateAvailability = " + appUpdateInfo.updateAvailability())
                    }
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_UPDATE_CODE) {
            if (resultCode != RESULT_OK) {
                // If the update is cancelled or fails, you can request to start the update again.
                Log.e(TAG, "Update flow failed! Result code: $resultCode")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener(
            playServiceExecutor,
            OnSuccessListener { appUpdateInfo ->
                if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                    // If the update is downloaded but not installed,
                    // notify the user to complete the update.
                    if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED)
                        updaterDownloadCompleted()
                } else {
                    // for AppUpdateType.IMMEDIATE only
                    // already executing updater
                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                        appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            AppUpdateType.IMMEDIATE,
                            this,
                            REQUEST_UPDATE_CODE
                        )
                    }
                }
            })
    }

    private fun updaterDownloadCompleted() {
        Snackbar.make(
            findViewById(R.id.drawer_layout),
            "An update has just been downloaded.",
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("RESTART") { appUpdateManager.completeUpdate() }
            show()
        }
    }

    private fun handleNavigation(@IdRes itemId: Int): Boolean {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawers()

        when (itemId) {
            R.id.navigation_browse -> {
                val url = preferenceRepository.getCurrentUrl()
                if (url.isNotEmpty()) {
                    navController.navigate(
                        actionGlobalToChrome(
                            url
                        )
                    )
                }
            }
        }

        return try {
            // ignore if current destination is selected
            if (navController.currentDestination?.id == itemId) return false
            val builder = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(R.id.navigation_home, false)
                .setEnterAnim(R.anim.fade_in)
                .setExitAnim(R.anim.fade_out)
                .setPopEnterAnim(R.anim.fade_in)
                .setPopExitAnim(R.anim.fade_out)
            val options = builder.build()
            navController.navigate(itemId, null, options)
            true
        } catch (e: IllegalArgumentException) {
            false
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
