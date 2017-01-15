package com.photoshotlist.dal;

import android.app.Activity;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.robolectric.Robolectric;
//import org.robolectric.RobolectricGradleTestRunner;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//import org.mockito.Mockito;
//import android.test.AndroidTestCase;
//import android.test.RenamingDelegatingContext;
//import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by PhpDev on 2016/08/06.
 */
//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)
//@Config(manifest=Config.NONE)
//public class PSLDatabaseHelperTest extends AndroidTestCase {
public class PSLDatabaseHelperTest{

    //@Inject
    //private Context context;

    @Test
    public void createShotlist_Success() throws Exception {

        //Context context = Mockito.mock(Context.class);
        //Context context = getContext();
//        Context context = new Activity();
//
//        PSLDatabaseHelper obj = PSLDatabaseHelper.getInstance(context);
//
//        assertEquals(true, true);

    }

    @Test
    public void testGetShotListByName() throws Exception {

        //RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test");
        //Context context = Mockito.mock(Context.class);
        //Context context = getContext();
//        Context context = new Activity();
//
//        PSLDatabaseHelper obj = PSLDatabaseHelper.getInstance(context);
//        ShotListDAO dao = obj.GetShotListByName("test");
//
//        assertEquals(null, dao);
    }

    @Test
    public void convertDateToText()
    {
//        String textDate;
////        LocalDateTime date = LocalDateTime.now();
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS.SSS");
////        String text = date.format(formatter);
////        LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
////
////        String textDate = parsedDate.toString();
//
//        Date date = Calendar.getInstance().getTime();
//        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        textDate = formatter.format(date);
//
//
//        assertNotEquals("", textDate);
    }

    @Test
    public void getImageByCategoryId()
    {

    }
}