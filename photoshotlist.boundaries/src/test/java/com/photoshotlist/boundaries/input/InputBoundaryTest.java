package com.photoshotlist.boundaries.input;

import com.photoshotlist.boundaries.Input.CategoryRequestModel;
import com.photoshotlist.boundaries.Input.CategoryResponseModel;
import com.photoshotlist.boundaries.Input.IInputBoundary;

import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class InputBoundaryTest {

    @Test
    public void testGetCategoryById_Success() {

        CategoryRequestModel requestModel = new CategoryRequestModel();
        requestModel.setCategoryId(1);

        CategoryResponseModel responseModel = new CategoryResponseModel();
        responseModel.setId(1);
        responseModel.setActive(true);
        responseModel.setName("Abstract");
        responseModel.setImageResourceId(2);
        responseModel.setLongDescription("Blah blah blah");

        IInputBoundary inputBoundary = Mockito.mock(IInputBoundary.class);
        when (inputBoundary.GetCategoryById(requestModel)).thenReturn(responseModel);

        Assert.assertEquals(1, responseModel.getId());
    }

    @Test
    public void testGetCategoryById_Fail() {

        CategoryRequestModel requestModel = new CategoryRequestModel();
        requestModel.setCategoryId(1);

        CategoryResponseModel responseModel = null;

        IInputBoundary inputBoundary = Mockito.mock(IInputBoundary.class);
        when (inputBoundary.GetCategoryById(requestModel)).thenReturn(responseModel);

        Assert.assertEquals(null, responseModel);
    }
}