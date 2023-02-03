package com.instrumentapp

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4::class)
class KotlinInstrumentedTest {

    private val APP_PACKAGE = "com.instrumentapp"
    private val LAUNCH_TIMEOUT = 5000
    private val STRING_TO_BE_TYPED = "Instrumentation Test"
    private val STRING_MESSAGE_PARAMETER = "Message from Previous activity"
    private var mDevice: UiDevice? = null

    @Before
    fun startMainActivity() {
        mDevice = UiDevice.getInstance(getInstrumentation())
        mDevice!!.pressHome()

        val launcherPackage = getLauncherPackageName()
        assertThat<String>(launcherPackage, notNullValue())
        mDevice!!.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT.toLong())

        val context = getApplicationContext<Context>()
        val intent = context.getPackageManager()
                .getLaunchIntentForPackage(APP_PACKAGE)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        // Wait for the app to appear
        mDevice!!.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT.toLong())

    }

    @Test
    fun checkPreCondition() {
        assertThat<UiDevice>(mDevice, notNullValue())
    }

    @Test
    fun testChangeText_sameActivity() {
        // Type text and then press the button.
        mDevice!!.findObject(By.res(APP_PACKAGE, "editTextUserInput")).text = STRING_TO_BE_TYPED
        mDevice!!.findObject(By.res(APP_PACKAGE, "changeTextBt"))
                .click()

        // Verify the test is displayed in the Ui
        val changedText = mDevice!!
                .wait(Until.findObject(By.res(APP_PACKAGE, "textview")), 500)
        assertThat<String>(changedText.text, `is`<String>(equalTo<String>(STRING_TO_BE_TYPED)))
    }

    @Test
    fun testChangeText_newActivity() {
        // Type text and then press the button.
        mDevice!!.findObject(By.res(APP_PACKAGE, "editTextUserInput")).text = STRING_MESSAGE_PARAMETER
        mDevice!!.findObject(By.res(APP_PACKAGE, "activityChangeTextBtn"))
                .click()

        // Verify the test is displayed in the Ui
        val changedText = mDevice!!
                .wait(Until.findObject(By.res(APP_PACKAGE, "show_text_view")),
                        500)
        assertThat<String>(changedText.text, `is`<String>(equalTo<String>(STRING_MESSAGE_PARAMETER)))
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = getInstrumentation().getTargetContext()

        assertEquals("com.instrumentapp", appContext.getPackageName())
    }


    private fun getLauncherPackageName(): String {
        // Create launcher Intent
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)

        // Use PackageManager to get the launcher package name
        val pm = getApplicationContext<Context>().getPackageManager()
        val resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return resolveInfo!!.activityInfo.packageName
    }
}