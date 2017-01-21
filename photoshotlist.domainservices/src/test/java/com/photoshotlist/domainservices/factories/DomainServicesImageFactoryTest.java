package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Image;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class DomainServicesImageFactoryTest {

    @Test
    public void CreateImage_Success() {

        Image imgOne = ImageFactory.getInstance().create(1, "Crate", "Blah blah blah", "filepath",
                2, "2017/01/21", false);

        Assert.assertEquals(imgOne.getName(), "Crate");
    }

    @Test
    public void CreateCategory_InvalidId_Fail() {

        Image imgOne = ImageFactory.getInstance().create(-1, "Crate", "Blah blah blah", "filepath",
                2, "2017/01/21", false);

        Assert.assertEquals(null, imgOne);
    }

    @Test
    public void CreateCategory_NoName_Fail() {

        Image imgOne = ImageFactory.getInstance().create(1, "", "Blah blah blah", "filepath", 2,
                "2017/01/21", false);

        Assert.assertEquals(null, imgOne);
    }
}
