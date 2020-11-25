package com.elderdrivers.riru.edxp.sandhook.entry;

import com.elderdrivers.riru.common.KeepMembers;
import com.elderdrivers.riru.edxp.sandhook.hooker.CreateAppContextHooker;
import com.elderdrivers.riru.edxp.sandhook.hooker.SystemMainHooker;

public class SysBootstrapHookInfo implements KeepMembers {
    public static String[] hookItemNames = {
            CreateAppContextHooker.class.getName(),
            SystemMainHooker.class.getName(),
    };

    public static Class[] hookItems = {
            CreateAppContextHooker.class,
            SystemMainHooker.class,
    };
}
