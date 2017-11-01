package com.photoshotlist.common;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static com.photoshotlist.common.Helper.GetFormattedDate;
import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2017/11/01.
 */

public class HelperTest {

    @Test
    public void GetDate_Success() {

        //Category expected = Mockito.mock(Category.class);
        //when (expected.getId()).thenReturn(1);

        String date = GetFormattedDate();
        Assert.assertNotEquals("", date);
    }
}
