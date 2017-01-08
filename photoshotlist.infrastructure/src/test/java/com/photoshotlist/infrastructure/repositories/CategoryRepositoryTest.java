package com.photoshotlist.infrastructure.repositories;

import com.photoshotlist.domainmodels.entities.Category;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class CategoryRepositoryTest {

    @Test
    public void GetById_Success() {

        CategoryRepository categoryRepository = new CategoryRepository();
        Category category = categoryRepository.GetById(1);

        Assert.assertEquals(1, category.getId());
    }
}
