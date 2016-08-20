package com.photoshotlist.common;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by PhpDev on 2016/08/20.
 */
public class Helper {
    public static String GetFormattedDate(){
        Date date = Calendar.getInstance().getTime();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String textDate = formatter.format(date);
        return textDate;
    }
}
