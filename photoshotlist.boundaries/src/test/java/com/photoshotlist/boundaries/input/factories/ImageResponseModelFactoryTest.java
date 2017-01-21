package com.photoshotlist.boundaries.input.factories;

import com.photoshotlist.boundaries.input.ImageResponseModel;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class ImageResponseModelFactoryTest {

    @Test
    public void CreateImage_Success() {

        ImageResponseModel imgOne = ImageResponseModelFactory.getInstance().create(1, "Crate",
                "Blah blah blah", "filepath", 2, "2017/01/21", false);

        Assert.assertEquals(imgOne.getName(), "Crate");
    }

    @Test
    public void CreateImage_InvalidId_Fail() {

        ImageResponseModel imgOne = ImageResponseModelFactory.getInstance().create(-1, "Crate",
                "Blah blah blah", "filepath", 2, "2017/01/21", false);

        Assert.assertEquals(null, imgOne);
    }

    @Test
    public void CreateImage_NoName_Fail() {

        ImageResponseModel imgOne = ImageResponseModelFactory.getInstance().create(1, "",
                "Blah blah blah", "filepath", 2, "2017/01/21", false);

        Assert.assertEquals(null, imgOne);
    }
}
