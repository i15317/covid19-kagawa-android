package jp.covid19_kagawa.covid19information

import androidx.appcompat.widget.AppCompatButton
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.testing.FakeAppUpdateManager
import com.google.android.play.core.install.model.AppUpdateType
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainActivityTest {

    private lateinit var fakeAppUpdateManager: FakeAppUpdateManager

    @Before
    fun setup() {
        fakeAppUpdateManager =
            FakeAppUpdateManager(InstrumentationRegistry.getInstrumentation().context)
    }


    @Test
    fun test_FlexibleUpdateSuccess() {

        fakeAppUpdateManager.partiallyAllowedUpdateType = AppUpdateType.FLEXIBLE
        fakeAppUpdateManager.setUpdateAvailable(100)

        ActivityScenario.launch(MainActivity::class.java)

        assertTrue(fakeAppUpdateManager.isConfirmationDialogVisible)

        fakeAppUpdateManager.userAcceptsUpdate()
        fakeAppUpdateManager.downloadStarts()
        fakeAppUpdateManager.downloadCompletes()

        Espresso.onView(
            allOf(
                isDescendantOfA(instanceOf(Snackbar.SnackbarLayout::class.java)),
                instanceOf(AppCompatButton::class.java)
            )
        ).perform(ViewActions.click())

        assertTrue(fakeAppUpdateManager.isInstallSplashScreenVisible)
        fakeAppUpdateManager.installCompletes()
    }

    @Test
    fun test_ImmediateUpdateSuccess() {

        fakeAppUpdateManager.partiallyAllowedUpdateType = AppUpdateType.IMMEDIATE
        fakeAppUpdateManager.setUpdateAvailable(10)

        ActivityScenario.launch(MainActivity::class.java)

        assertTrue(fakeAppUpdateManager.isImmediateFlowVisible)

        fakeAppUpdateManager.userAcceptsUpdate()
        fakeAppUpdateManager.downloadStarts()
        fakeAppUpdateManager.downloadCompletes()

        assertTrue(fakeAppUpdateManager.isInstallSplashScreenVisible)
        fakeAppUpdateManager.installCompletes()
    }
}