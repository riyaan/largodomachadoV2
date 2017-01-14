package com.photoshotlist.interactors;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class CategoryInteractorTest {

    @Test
    public void GetById_Success() {

        Category expected = new Category();
        expected.setId(1);
        expected.setName("Abstract");
        expected.setLongDescription("Blah blah blah");
        expected.setImageResourceId(2);
        expected.setActive(true);

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetById(1)).thenReturn(expected);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository);
        Category actual = interactor.GetById(1);

        Assert.assertEquals(1, expected.getId());
    }
}
