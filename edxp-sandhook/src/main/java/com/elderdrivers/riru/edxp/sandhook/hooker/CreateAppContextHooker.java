package com.elderdrivers.riru.edxp.sandhook.hooker;
import android.app.ActivityThread;
import android.app.ContextImpl;
import android.app.LoadedApk;

import com.elderdrivers.riru.common.KeepMembers;
import com.elderdrivers.riru.edxp._hooker.impl.CreateAppContext;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.SkipParamCheck;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;

@HookReflectClass("com.android.server.SystemServer")
public class CreateAppContextHooker implements KeepMembers {
    public static String className = "android.app.ContextImpl";
    public static String methodName = "createAppContext";
    public static String methodSig = "(Landroid/app/ActivityThread;Landroid/app/LoadedApk;)Landroid/app/ContextImpl;";

    @HookMethodBackup("createAppContext")
    @SkipParamCheck
    static Method backup;

    @HookMethod("createAppContext")
    public static ContextImpl hook(ActivityThread activityThread, LoadedApk loadedApk) throws Throwable {
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

    public static ContextImpl backup(ActivityThread activityThread, LoadedApk loadedApk) throws Throwable {
        return (ContextImpl)SandHook.callOriginByBackup(backup, activityThread, loadedApk);
    }

}
