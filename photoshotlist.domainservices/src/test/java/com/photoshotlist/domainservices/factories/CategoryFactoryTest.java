package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class CategoryFactoryTest {

    @Test
    public void CreateCategory_Success() {

        Category expected = new Category();
        expected.setId(1);
        expected.setName("Abstract");
        expected.setLongDescription("Blah blah blah");
        expected.setImageResourceId(2);
        expected.setActive(true);

        Category category = CategoryFactory.getInstance().create("Abstract", "Blah blah blah", 2, true);

        Assert.assertEquals(category.getName(), expected.getName());
    }

    @Test
    public void CreateCategory_Fail() {

        Category unexpected = new Category();
        unexpected.setId(1);
        unexpected.setName("Abstract");
        unexpected.setLongDescription("Blah blah blah");
        unexpected.setImageResourceId(2);
        unexpected.setActive(true);

        Category category = CategoryFactory.getInstance().create("Closeup", "Lorem ipsum", 3, true);

        Assert.assertNotEquals(unexpected.getName(), category.getName());
    }
}
