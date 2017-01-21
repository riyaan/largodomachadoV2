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
public class DomainServicesCategoryFactoryTest {

    @Test
    public void CreateCategory_Success() {

        Category expected = Mockito.mock(Category.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Abstract");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);

        Category category = CategoryFactory.getInstance().create(1, "Abstract", "Blah blah blah", 2, true);

        Assert.assertEquals(category.getName(), expected.getName());
    }

    @Test
    public void CreateCategory_Fail() {

        Category unexpected = Mockito.mock(Category.class);
        when (unexpected.getId()).thenReturn(1);
        when (unexpected.getName()).thenReturn("Abstract");
        when (unexpected.getLongDescription()).thenReturn("Blah blah blah");
        when (unexpected.getImageResourceId()).thenReturn(2);
        when (unexpected.isActive()).thenReturn(true);

        Category category = CategoryFactory.getInstance().create(2, "Closeup", "Lorem ipsum", 3, true);

        Assert.assertNotEquals(unexpected.getName(), category.getName());
    }
}
