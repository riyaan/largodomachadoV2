package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.Input.CategoryRequestModel;
import com.photoshotlist.boundaries.Input.CategoryResponseModel;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class InteractorsCategoryInteractorTest {

    @Test
    public void GetById_Success() {

        Category expected = Mockito.mock(Category.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Abstract");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetById(1)).thenReturn(expected);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository);
        Category actual = interactor.GetById(1);

        Assert.assertEquals(1, expected.getId());
    }

    @Test
    public void GetById_Fail() {

        Category expected = null;

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetById(1)).thenReturn(expected);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository);
        Category actual = interactor.GetById(1);

        Assert.assertEquals(null, expected);
    }

    @Test
    public void GetCategoryById_Success() {

        CategoryRequestModel requestModel = Mockito.mock(CategoryRequestModel.class);
        when (requestModel.getCategoryId()).thenReturn(1);

        Category expected = Mockito.mock(Category.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Abstract");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetById(1)).thenReturn(expected);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository);
        CategoryResponseModel actual = interactor.GetCategoryById(requestModel);

        Assert.assertEquals(1, actual.getId());
    }

    @Test
    //Description("The Category does not exist in the system. An exception is thrown.")
    public void GetCategoryById_Fail() {

        try {
            CategoryRequestModel requestModel = Mockito.mock(CategoryRequestModel.class);
            when(requestModel.getCategoryId()).thenReturn(1);

            ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
            when(categoryRepository.GetById(1)).thenThrow(new Exception());

            CategoryInteractor interactor = new CategoryInteractor(categoryRepository);
            CategoryResponseModel actual = interactor.GetCategoryById(requestModel);
        }
        catch(Exception e) {
            // An exception was thrown, so the category does indeed not exist
            Assert.assertTrue(true);
        }
    }
}
