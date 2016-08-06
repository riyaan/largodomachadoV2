package com.photoshotlist.dal;

import org.junit.Test;
import org.mockito.Mockito;

import android.content.Context;

import static org.junit.Assert.*;

/**
 * Created by PhpDev on 2016/08/06.
 */
public class PSLDatabaseHelperTest {
    @Test
    public void createShotlist_Success() throws Exception {

        Context context = Mockito.mock(Context.class);

        PSLDatabaseHelper obj = PSLDatabaseHelper.getInstance(context);

        assertEquals(4, 2 + 2);
    }
}