package com.photoshotlist.boundaries.input;

import com.photoshotlist.boundaries.input.factories.ImageResponseModelFactory;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class BoundariesInputBoundaryTest {

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

    @Test
    public void testGetImagesByCategory_Success() {

        ImageRequestModel requestModel = new ImageRequestModel();
        requestModel.setCategoryId(1);

        ImageResponseModel irmOne = ImageResponseModelFactory.getInstance().create(1, "Summer",
                "Lorum ipsum", "/filePath", 12, "2017/01/21", true);

        ImageResponseModel irmTwo = ImageResponseModelFactory.getInstance().create(1, "Winter",
                "Lorum ipsum", "/filePath2", 12, "2017/01/21", true);

        List<ImageResponseModel> imageResponseModels = new ArrayList<ImageResponseModel>();
        imageResponseModels.add(irmOne);
        imageResponseModels.add(irmTwo);

        IImageInputBoundary inputBoundary = Mockito.mock(IImageInputBoundary.class);
        when (inputBoundary.GetImagesByCategory(requestModel)).thenReturn(imageResponseModels);

        Assert.assertEquals(2, imageResponseModels.size());
    }

    @Test
    public void testGetCategoryByName_Success() {

        CategoryRequestModel requestModel = new CategoryRequestModel();
        requestModel.setCategoryName("Abstract");

        CategoryResponseModel responseModel = new CategoryResponseModel();
        responseModel.setId(1);
        responseModel.setActive(true);
        responseModel.setName("Abstract");
        responseModel.setImageResourceId(2);
        responseModel.setLongDescription("Blah blah blah");

        IInputBoundary inputBoundary = Mockito.mock(IInputBoundary.class);
        when (inputBoundary.GetCategoryByName(requestModel)).thenReturn(responseModel);

        Assert.assertEquals(1, responseModel.getId());
    }

//    @Test
//    public void testGetCategoryById_Fail() {
//
//        CategoryRequestModel requestModel = new CategoryRequestModel();
//        requestModel.setCategoryId(1);
//
//        CategoryResponseModel responseModel = null;
//
//        IInputBoundary inputBoundary = Mockito.mock(IInputBoundary.class);
//        when (inputBoundary.GetCategoryById(requestModel)).thenReturn(responseModel);
//
//        Assert.assertEquals(null, responseModel);
//    }
}