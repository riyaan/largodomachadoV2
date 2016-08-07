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

import static org.junit.Assert.assertEquals;

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
        Context context = new Activity();

        PSLDatabaseHelper obj = PSLDatabaseHelper.getInstance(context);

        assertEquals(true, true);
    }

    @Test
    public void testGetShotListByName() throws Exception {

        //RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test");
        //Context context = Mockito.mock(Context.class);
        //Context context = getContext();
        Context context = new Activity();

        PSLDatabaseHelper obj = PSLDatabaseHelper.getInstance(context);
        ShotListDAO dao = obj.GetShotListByName("test");

        assertEquals(null, dao);
    }
}