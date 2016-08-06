package com.photoshotlist.dal;

import org.junit.Test;
import org.mockito.Mockito;

import android.content.Context;
import android.test.AndroidTestCase;

import static org.junit.Assert.*;

/**
 * Created by PhpDev on 2016/08/06.
 */
public class PSLDatabaseHelperTest extends AndroidTestCase {
    @Test
    public void createShotlist_Success() throws Exception {

        //Context context = Mockito.mock(Context.class);
        Context context = getContext();

        PSLDatabaseHelper obj = PSLDatabaseHelper.getInstance(context);

        assertEquals(true, true);
    }

    @Test
    public void testGetShotListByName() throws Exception {

        //Context context = Mockito.mock(Context.class);
        Context context = getContext();

        PSLDatabaseHelper obj = PSLDatabaseHelper.getInstance(context);
        ShotListDAO dao = obj.GetShotListByName("test");

        assertEquals(null, dao);
    }
}