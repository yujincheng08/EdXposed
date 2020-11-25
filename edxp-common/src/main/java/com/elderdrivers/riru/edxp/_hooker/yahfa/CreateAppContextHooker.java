package com.elderdrivers.riru.edxp._hooker.yahfa;

import android.app.ActivityThread;
import android.app.ContextImpl;
import android.app.LoadedApk;

import com.elderdrivers.riru.common.KeepMembers;
import com.elderdrivers.riru.edxp._hooker.impl.CreateAppContext;

import de.robv.android.xposed.XC_MethodHook;

public class CreateAppContextHooker implements KeepMembers {
    public static String className = "android.app.ContextImpl";
    public static String methodName = "createAppContext";
    public static String methodSig = "(Landroid/app/ActivityThread;Landroid/app/LoadedApk;)Landroid/app/ContextImpl;";

    public static ContextImpl hook(final ActivityThread activityThread, final LoadedApk loadedApk) throws Throwable {

        final XC_MethodHook methodHook = new CreateAppContext();
        final XC_MethodHook.MethodHookParam param = new XC_MethodHook.MethodHookParam();
        param.thisObject = null;
        param.args = new Object[]{activityThread, loadedApk};
        methodHook.callBeforeHookedMethod(param);
        if (!param.returnEarly) {
            param.setResult(backup(activityThread, loadedApk));
        }
        methodHook.callAfterHookedMethod(param);
        return (ContextImpl)param.getResult();
    }

    public static ContextImpl backup(ActivityThread activityThread, LoadedApk loadedApk) {
        return null;
    }
}
