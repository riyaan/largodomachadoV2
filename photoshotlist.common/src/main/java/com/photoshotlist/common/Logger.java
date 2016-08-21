package com.photoshotlist.common;

import android.util.Log;

/**
 * Created by PhpDev on 2016/08/21.
 */
public class Logger {

    public static void Debug(String className, String message) {
        Log.d(className, message);
    }
}
