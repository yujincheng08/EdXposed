package com.elderdrivers.riru.edxp._hooker.impl;

import android.app.ActivityThread;
import android.app.LoadedApk;
import android.content.res.XResources;

import com.elderdrivers.riru.edxp.config.ConfigManager;
import com.elderdrivers.riru.edxp.hooker.SliceProviderFix;
import com.elderdrivers.riru.edxp.hooker.XposedInstallerHooker;
import com.elderdrivers.riru.edxp.util.Hookers;
import com.elderdrivers.riru.edxp.util.Utils;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.elderdrivers.riru.edxp.hooker.SliceProviderFix.SYSTEMUI_PACKAGE_NAME;

public class CreateAppContext extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) {
        Hookers.logD("ContextImpl#createAppContext");
        try {
            LoadedApk loadedApk = (LoadedApk) param.args[1];
            String packageName = loadedApk.getPackageName().equals("android") ? "system" : loadedApk.getPackageName();
            ConfigManager.appProcessName = ActivityThread.currentProcessName();

            Utils.logD("ContextImpl#createAppContext: processName=" + ConfigManager.appProcessName +
                    ", packageName=" + packageName + ", appDataDir=" + ConfigManager.appDataDir);


            XResources.setPackageNameForResDir(packageName, loadedApk.getResDir());
            String processName = ConfigManager.appProcessName;
            XC_LoadPackage.LoadPackageParam lpparam = new XC_LoadPackage.LoadPackageParam(XposedBridge.sLoadedPackageCallbacks);
            lpparam.packageName = packageName;
            lpparam.processName = processName;
            lpparam.classLoader = loadedApk.getClassLoader();
            lpparam.appInfo = loadedApk.getApplicationInfo();
            // Always true
            lpparam.isFirstApplication = XposedInit.loadedPackagesInProcess.add(packageName);
            XC_LoadPackage.callAll(lpparam);

            if (packageName.equals(ConfigManager.getInstallerPackageName())) {
                XposedInstallerHooker.hookXposedInstaller(lpparam.classLoader);
            }
            if (packageName.equals(SYSTEMUI_PACKAGE_NAME)) {
                SliceProviderFix.hook();
            }

        } catch (Throwable e) {
            Hookers.logE("ContextImpl#createAppContext", e);
        }
    }
}
