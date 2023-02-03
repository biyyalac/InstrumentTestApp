package com.instrumentapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class JavaInstrumentedTest {


    private static final String APP_PACKAGE
            = "com.instrumentapp";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "Instrumentation Test";
    private static final String STRING_MESSAGE_PARAMETER = "Message from Previous activity";
    private UiDevice mDevice;

    @Before
    public void startMainActivity(){
        mDevice = UiDevice.getInstance(getInstrumentation());
        mDevice.pressHome();

        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),LAUNCH_TIMEOUT);

        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(APP_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT);

    }

    @Test
    public void checkPreCondition() {
        assertThat(mDevice, notNullValue());
    }

    @Test
    public void testChangeText_sameActivity() {
        // Type text and then press the button.
        mDevice.findObject(By.res(APP_PACKAGE, "editTextUserInput"))
                .setText(STRING_TO_BE_TYPED);
        mDevice.findObject(By.res(APP_PACKAGE, "changeTextBt"))
                .click();

        // Verify the test is displayed in the Ui
        UiObject2 changedText = mDevice
                .wait(Until.findObject(By.res(APP_PACKAGE, "textview")),500 );
        assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }

    @Test
    public void testChangeText_newActivity() {
        // Type text and then press the button.
        mDevice.findObject(By.res(APP_PACKAGE, "editTextUserInput"))
                .setText(STRING_MESSAGE_PARAMETER);
        mDevice.findObject(By.res(APP_PACKAGE, "activityChangeTextBtn"))
                .click();

        // Verify the test is displayed in the Ui
        UiObject2 changedText = mDevice
                .wait(Until.findObject(By.res(APP_PACKAGE, "show_text_view")),
                        500 );
        assertThat(changedText.getText(), is(equalTo(STRING_MESSAGE_PARAMETER)));
    }


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();

        assertEquals("com.instrumentapp", appContext.getPackageName());
    }


    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
