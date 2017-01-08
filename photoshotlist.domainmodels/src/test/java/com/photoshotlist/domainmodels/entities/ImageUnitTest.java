package com.photoshotlist.domainmodels.entities;

import org.junit.Assert;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ImageUnitTest {
    @Test
    public void getImageName_Success() throws Exception {

        final String IMAGE_NAME = "Close-up";

        Image image = new Image();
        image.setName(IMAGE_NAME);

        String actual = image.getName();

        Assert.assertEquals(IMAGE_NAME, actual);
    }

    @Test
    public void getImageName_Failure() throws Exception {

        final String IMAGE_NAME              = "Close-up";
        final String INCORRECT_IMAGE_NAME    = "Invalid";

        Image image = new Image();
        image.setName(IMAGE_NAME);

        String actual = image.getName();

        Assert.assertNotEquals(INCORRECT_IMAGE_NAME, actual);
    }
}
