package com.photoshotlist.infrastructure.repositories;

import android.content.Context;
import android.test.mock.MockContext;

import com.photoshotlist.domainmodels.entities.Category;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class InfrastructureCategoryRepositoryTest {

    @Test
    public void GetById_Success() {

        // TODO: test the actual connection to the database

        // TODO: Cannot get the Mocking of the Context working
//        Context context = Mockito.mock(Context.class);
//        when(context.getApplicationContext()).thenReturn(context);
//
//        CategoryRepository categoryRepository = new CategoryRepository(context);
//        Category category = categoryRepository.GetById(1);
//
//        Assert.assertEquals(1, category.getId());

    }
}
