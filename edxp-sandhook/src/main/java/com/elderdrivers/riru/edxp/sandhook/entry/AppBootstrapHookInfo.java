package com.elderdrivers.riru.edxp.sandhook.entry;

import com.elderdrivers.riru.common.KeepMembers;
import com.elderdrivers.riru.edxp.sandhook.hooker.CreateAppContextHooker;

public class AppBootstrapHookInfo implements KeepMembers {
    public static String[] hookItemNames = {
            CreateAppContextHooker.class.getName()
    };

    public static Class[] hookItems = {
            CreateAppContextHooker.class
    };
}
