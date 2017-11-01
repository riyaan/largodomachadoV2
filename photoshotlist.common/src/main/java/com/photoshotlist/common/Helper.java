package com.photoshotlist.common;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.text.DateFormat.getDateTimeInstance;

/**
 * Created by PhpDev on 2016/08/20.
 */
public class Helper {
    public static String GetFormattedDate(){
        Date date = Calendar.getInstance().getTime();
        // Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Format f = getDateTimeInstance();
        String textDate = f.format(date);
        // String textDate = formatter.format(date);
        return textDate;
    }
}
