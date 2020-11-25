package com.elderdrivers.riru.edxp.entry.yahfa;

import com.elderdrivers.riru.common.KeepMembers;
import com.elderdrivers.riru.edxp._hooker.yahfa.CreateAppContextHooker;
import com.elderdrivers.riru.edxp._hooker.yahfa.SystemMainHooker;

public class SysBootstrapHookInfo implements KeepMembers {
    public static String[] hookItemNames = {
            SystemMainHooker.class.getName(),
            CreateAppContextHooker.class.getName(),
    };
}
