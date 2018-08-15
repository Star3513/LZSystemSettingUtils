package com.lz.star.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Copyright (c) 2017-2019 DEEPFINCH Corporation. All rights reserved.
 **/
public class LZCommonUtils {

    private static final String TAG = "DFCarInspectSDKUtils";

    public static void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }

    /**
     * 设置语言
     *
     * @param lauType
     */
    public static void setLocal(Context context, String lauType) {
        logI(TAG, "setLocal", "lauType", lauType);
        // 本地语言设置
        Locale myLocale = new Locale(lauType);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        conf.orientation = Configuration.ORIENTATION_LANDSCAPE;
        res.updateConfiguration(conf, dm);
    }

    public static <T> List<T> refreshList(List<T> srcList, List<T> destList) {
        if (destList == null) {
            destList = new ArrayList<>();
        } else {
            destList.clear();
        }
        if (srcList != null) {
            destList.addAll(srcList);
        }
        return destList;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    /**
     * 防止多次点击
     *
     * @return
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2dx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void logI(Object... logValue) {
        StringBuffer sb = new StringBuffer();
        if (logValue != null) {
            for (Object value : logValue) {
                if (value != null) {
                    sb.append("*")
                            .append(value.toString())
                            .append("*");
                }
            }
        }
        Log.i(TAG, "logI*" + sb.toString());
    }
}
