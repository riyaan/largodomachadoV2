package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Category;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class DomainServicesCategoryFactoryTest {

    @Test
    public void CreateCategory_Success() {

        Category category = CategoryFactory.getInstance().create(1, "Abstract", "Blah blah blah", 2, true);

        Assert.assertEquals(category.getName(), "Abstract");
    }

    @Test
    public void CreateCategory_InvalidId_Fail() {

        Category category = CategoryFactory.getInstance().create(-1, "Closeup", "Lorem ipsum", 3, true);

        Assert.assertEquals(null, category);
    }

    @Test
    public void CreateCategory_NoName_Fail() {

        Category category = CategoryFactory.getInstance().create(1, "", "Lorem ipsum", 3, true);

        Assert.assertEquals(null, category);
    }
}
