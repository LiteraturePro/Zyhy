package com.njupt.zyhy.bean;

import com.mob.MobSDK;
import com.njupt.zyhy.bmob.restapi.Bmob;

public class InitSDK {
    public static void Initbmob() {
        Bmob.initBmob("",
                "");
        Bmob.initMaster("");

    }
    public static void Initmob() {
        MobSDK.init(MobSDK.getContext(),"","");

    }
}
