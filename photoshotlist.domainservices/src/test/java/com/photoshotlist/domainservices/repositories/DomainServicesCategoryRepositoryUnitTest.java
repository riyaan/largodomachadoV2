package com.photoshotlist.domainservices.repositories;

import com.photoshotlist.domainmodels.entities.Category;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2017/01/08.
 */

//@RunWith(MockitoJUnitRunner.class)
public class DomainServicesCategoryRepositoryUnitTest {

    @Test
    public void getCategoryById_Success() throws Exception {


        Category category = Mockito.mock(Category.class);
        when (category.getId()).thenReturn(42);

        Assert.assertEquals(category.getId(), 42);
    }

    //@InjectMocks private ICategoryRepository categoryRepository;
    //@Mock Category category;
    @Test
    public void getCategoryByIdMock_Success() throws Exception {

//        Category category = Mockito.mock(Category.class);
//
        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class, Mockito.CALLS_REAL_METHODS);
//        when (categoryRepository.GetById(42)).thenReturn(category);
        Category category = categoryRepository.GetById(42);
        //category.setName("Test");

        Assert.assertTrue(true);
    }

}
