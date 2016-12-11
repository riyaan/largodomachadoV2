package com.photoshotlist.crosscutting.logging;

import android.util.Log;
import com.photoshotlist.crosscutting.interfaces.ILogger;

/**
 * Created by PhpDev on 2016/12/11.
 */
public class Logger implements ILogger{

    private static Logger instance;

    private Logger(){
    }

    public static Logger getInstance()
    {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    @Override
    public void Debug(String className, String message) {
            Log.d(className, message);
    }
}
