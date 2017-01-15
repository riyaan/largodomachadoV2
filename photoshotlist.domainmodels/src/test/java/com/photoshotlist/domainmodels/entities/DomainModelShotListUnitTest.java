package com.photoshotlist.domainmodels.entities;

import org.junit.Assert;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DomainModelShotListUnitTest {
    @Test
    public void getShotListName_Success() throws Exception {

        final String SHOTLIST_NAME = "Abstract";

        ShotList shotList = new ShotList();
        shotList.setName(SHOTLIST_NAME);

        String actual = shotList.getName();

        Assert.assertEquals(SHOTLIST_NAME, actual);
    }

    @Test
    public void geShotListName_Failure() throws Exception {

        final String SHOTLIST_NAME              = "Abstract";
        final String INCORRECT_SHOTLIST_NAME    = "Invalid";

        ShotList shotList = new ShotList();
        shotList.setName(SHOTLIST_NAME);

        String actual = shotList.getName();

        Assert.assertNotEquals(INCORRECT_SHOTLIST_NAME, actual);
    }
}
