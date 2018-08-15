package com.lz.star.utils.systemsetting;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class LZScreenLightUtils {
    /**
     * 设置屏幕亮度模式
     *
     * @param activity
     */
    public static void setScreenManualMode(Activity activity) {
        ContentResolver contentResolver = activity.getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前屏幕亮度
     *
     * @param activity
     * @return
     */
    public static int getScreenBrightness(Activity activity) {
        ContentResolver contentResolver = activity.getContentResolver();
        int defVal = 125;
        return Settings.System.getInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, defVal);
    }

    /**
     * 设置屏幕亮度，只有在手动调节亮度时候才会生效
     *
     * @param activity
     * @param lightValue 要介于0-255之间
     */
    public static void saveScreenBrightness(Activity activity, int lightValue) {
        ContentResolver contentResolver = activity.getContentResolver();
        //int value = 255; // 设置亮度值为255
        if (lightValue < 0) {
            lightValue = 0;
        }
        if (lightValue > 255) {
            lightValue = 255;
        }
        Settings.System.putInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, lightValue);
    }

    /**
     * 当前亮度调节模式是否是手动调节
     *
     * @param activity
     * @return
     */
    public static boolean istManualBrightness(Activity activity) {
        boolean manualBrightness = false;
        ContentResolver contentResolver = activity.getContentResolver();
        try {
            manualBrightness = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return manualBrightness;
    }

    /**
     * 当前亮度模式是否是自动调节
     *
     * @param aContentResolver
     * @return
     */
    public static boolean isAutoBrightness(ContentResolver aContentResolver) {
        boolean automicBrightness = false;
        try {
            automicBrightness = Settings.System.getInt(aContentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return automicBrightness;
    }

    /**
     * 开启亮度手动调节
     *
     * @param activity
     */
    public static void startManualBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    /**
     * 开启亮度自动调节
     *
     * @param activity
     */
    public static void startAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }
}
