package com.photoshotlist.domainmodels.entities;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DomainModelCategoryUnitTest {
    @Test
    public void getCategoryName_Success() throws Exception {

        final String CATEGORY_NAME = "Abstract";

        Category category = new Category();
        category.setName(CATEGORY_NAME);

        String actual = category.getName();

        Assert.assertEquals(CATEGORY_NAME, actual);
    }

    @Test
    public void getCategoryName_Failure() throws Exception {

        final String CATEGORY_NAME              = "Abstract";
        final String INCORRECT_CATEGORY_NAME    = "Invalid";

        Category category = new Category();
        category.setName(CATEGORY_NAME);

        String actual = category.getName();

        Assert.assertNotEquals(INCORRECT_CATEGORY_NAME, actual);
    }
}
