package org.meowcat.edxposed.manager;

import android.util.Log;

public class Constants {
    public static int getXposedApiVersion() {
        Log.e(App.TAG, "getXposedApiVersion: Xposed is not active");
        return -1;
    }

    public static String getXposedVersion() {
        Log.e(App.TAG, "getXposedVersion: Xposed is not active");
        return null;
    }

    public static int getXposedVersionCode() {
        Log.e(App.TAG, "getXposedVersionCode: Xposed is not active");
        return -1;
    }

    public static String getXposedVariant() {
        Log.e(App.TAG, "getXposedVariant: Xposed is not active");
        return null;
    }

    public static String getEnabledModulesListFile() {
        return getBaseDir() + "conf/enabled_modules.list";
    }

    public static String getModulesListFile() {
        return getBaseDir() + "conf/modules.list";
    }

    public static String getBaseDir() {
        return App.getInstance().getApplicationInfo().deviceProtectedDataDir + "/";
    }
}
