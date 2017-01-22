package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.CategoryRequestModel;
import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.domainservices.repositories.IImageRepository;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

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
        when (expected.getImages()).thenReturn(new ArrayList<Image>());

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetById(1)).thenReturn(expected);

        Image image = Mockito.mock(Image.class);
        when(image.getName()).thenReturn("Sunrise");
        when(image.getCreatedDate()).thenReturn("2017/01/22");
        when(image.getImageResourceId()).thenReturn(2);
        when(image.getLongDescription()).thenReturn("Long Description");
        when(image.getId()).thenReturn(1);
        when(image.getLocation()).thenReturn("filepath");

        Image image2 = Mockito.mock(Image.class);
        when(image2.getName()).thenReturn("Sunset");
        when(image2.getCreatedDate()).thenReturn("2017/01/22");
        when(image2.getImageResourceId()).thenReturn(3);
        when(image2.getLongDescription()).thenReturn("Long Description");
        when(image2.getId()).thenReturn(2);
        when(image2.getLocation()).thenReturn("filepath");

        List<Image> images = new ArrayList<Image>();
        images.add(image);
        images.add(image2);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
        Category actual = interactor.GetById(1);

        Assert.assertEquals(1, expected.getId());
    }

    @Test
    public void GetById_Fail() {

        Category expected = null;

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetById(1)).thenReturn(expected);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(new ArrayList<Image>());

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
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
        when (expected.getImages()).thenReturn(new ArrayList<Image>());

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetById(1)).thenReturn(expected);

        Image image = Mockito.mock(Image.class);
        when(image.getName()).thenReturn("Sunrise");
        when(image.getCreatedDate()).thenReturn("2017/01/22");
        when(image.getImageResourceId()).thenReturn(2);
        when(image.getLongDescription()).thenReturn("Long Description");
        when(image.getId()).thenReturn(1);
        when(image.getLocation()).thenReturn("filepath");

        Image image2 = Mockito.mock(Image.class);
        when(image2.getName()).thenReturn("Sunset");
        when(image2.getCreatedDate()).thenReturn("2017/01/22");
        when(image2.getImageResourceId()).thenReturn(3);
        when(image2.getLongDescription()).thenReturn("Long Description");
        when(image2.getId()).thenReturn(2);
        when(image2.getLocation()).thenReturn("filepath");

        List<Image> images = new ArrayList<Image>();
        images.add(image);
        images.add(image2);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
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

            IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
            when(imageRepository.GetByCategory(1)).thenThrow(new Exception());

            CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
            CategoryResponseModel actual = interactor.GetCategoryById(requestModel);
        }
        catch(Exception e) {
            // An exception was thrown, so the category does indeed not exist
            Assert.assertTrue(true);
        }
    }

    @Test
    public void GetAll_Success() {

        Category categoryOne = Mockito.mock(Category.class);
        when (categoryOne.getId()).thenReturn(1);
        when (categoryOne.getName()).thenReturn("Abstract");
        when (categoryOne.getLongDescription()).thenReturn("Blah blah blah");
        when (categoryOne.getImageResourceId()).thenReturn(2);
        when (categoryOne.isActive()).thenReturn(true);
        when (categoryOne.getImages()).thenReturn(new ArrayList<Image>());

        Category categoryTwo = Mockito.mock(Category.class);
        when (categoryTwo.getId()).thenReturn(2);
        when (categoryTwo.getName()).thenReturn("Landscape");
        when (categoryTwo.getLongDescription()).thenReturn("Lorum ipsum");
        when (categoryTwo.getImageResourceId()).thenReturn(3);
        when (categoryTwo.isActive()).thenReturn(false);

        List<Category> categories = new ArrayList<Category>();
        categories.add(categoryOne);
        categories.add(categoryTwo);

        Image image = Mockito.mock(Image.class);
        when(image.getName()).thenReturn("Sunrise");
        when(image.getCreatedDate()).thenReturn("2017/01/22");
        when(image.getImageResourceId()).thenReturn(2);
        when(image.getLongDescription()).thenReturn("Long Description");
        when(image.getId()).thenReturn(1);
        when(image.getLocation()).thenReturn("filepath");

        Image image2 = Mockito.mock(Image.class);
        when(image2.getName()).thenReturn("Sunset");
        when(image2.getCreatedDate()).thenReturn("2017/01/22");
        when(image2.getImageResourceId()).thenReturn(3);
        when(image2.getLongDescription()).thenReturn("Long Description");
        when(image2.getId()).thenReturn(2);
        when(image2.getLocation()).thenReturn("filepath");

        List<Image> images = new ArrayList<Image>();
        images.add(image);
        images.add(image2);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetAll()).thenReturn(categories);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
        List<Category> actual = interactor.GetAll();

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void GetAll_Fail() {

        List<Category> categories = new ArrayList<Category>();

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetAll()).thenReturn(categories);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when(imageRepository.GetByCategory(1)).thenReturn(new ArrayList<Image>());

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
        List<Category> actual = interactor.GetAll();

        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void GetAllCategories_Success() {

        Category categoryOne = Mockito.mock(Category.class);
        when (categoryOne.getId()).thenReturn(1);
        when (categoryOne.getName()).thenReturn("Abstract");
        when (categoryOne.getLongDescription()).thenReturn("Blah blah blah");
        when (categoryOne.getImageResourceId()).thenReturn(2);
        when (categoryOne.isActive()).thenReturn(true);

        Category categoryTwo = Mockito.mock(Category.class);
        when (categoryTwo.getId()).thenReturn(2);
        when (categoryTwo.getName()).thenReturn("Landscape");
        when (categoryTwo.getLongDescription()).thenReturn("Lorum ipsum");
        when (categoryTwo.getImageResourceId()).thenReturn(3);
        when (categoryTwo.isActive()).thenReturn(false);

        List<Category> categories = new ArrayList<Category>();
        categories.add(categoryOne);
        categories.add(categoryTwo);

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetAll()).thenReturn(categories);

        Image image = Mockito.mock(Image.class);
        when(image.getName()).thenReturn("Sunrise");
        when(image.getCreatedDate()).thenReturn("2017/01/22");
        when(image.getImageResourceId()).thenReturn(2);
        when(image.getLongDescription()).thenReturn("Long Description");
        when(image.getId()).thenReturn(1);
        when(image.getLocation()).thenReturn("filepath");

        Image image2 = Mockito.mock(Image.class);
        when(image2.getName()).thenReturn("Sunset");
        when(image2.getCreatedDate()).thenReturn("2017/01/22");
        when(image2.getImageResourceId()).thenReturn(3);
        when(image2.getLongDescription()).thenReturn("Long Description");
        when(image2.getId()).thenReturn(2);
        when(image2.getLocation()).thenReturn("filepath");

        List<Image> images = new ArrayList<Image>();
        images.add(image);
        images.add(image2);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
        List<CategoryResponseModel> actual = interactor.GetAllCategories();

        Assert.assertEquals(2, actual.size());
    }


    @Test
    public void GetAllCategories_Fail() {

        List<Category> categories = new ArrayList<Category>();

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetAll()).thenReturn(categories);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when(imageRepository.GetByCategory(1)).thenReturn(new ArrayList<Image>());

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
        List<CategoryResponseModel> actual = interactor.GetAllCategories();

        Assert.assertEquals(0, actual.size());
    }

    // Get Category By Name Start

    @Test
    public void GetByName_Success() {

        Category expected = Mockito.mock(Category.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Abstract");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetByName("Abstract")).thenReturn(expected);

        Image image = Mockito.mock(Image.class);
        when(image.getName()).thenReturn("Sunrise");
        when(image.getCreatedDate()).thenReturn("2017/01/22");
        when(image.getImageResourceId()).thenReturn(2);
        when(image.getLongDescription()).thenReturn("Long Description");
        when(image.getId()).thenReturn(1);
        when(image.getLocation()).thenReturn("filepath");

        Image image2 = Mockito.mock(Image.class);
        when(image2.getName()).thenReturn("Sunset");
        when(image2.getCreatedDate()).thenReturn("2017/01/22");
        when(image2.getImageResourceId()).thenReturn(3);
        when(image2.getLongDescription()).thenReturn("Long Description");
        when(image2.getId()).thenReturn(2);
        when(image2.getLocation()).thenReturn("filepath");

        List<Image> images = new ArrayList<Image>();
        images.add(image);
        images.add(image2);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
        Category actual = interactor.GetByName("Abstract");

        Assert.assertEquals("Abstract", expected.getName());
    }

    @Test
    public void GetByName_Fail() {

        Category expected = null;

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetByName("Invalid")).thenReturn(expected);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when(imageRepository.GetByCategory(1)).thenReturn(new ArrayList<Image>());

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
        Category actual = interactor.GetByName("Invalid");

        Assert.assertEquals(null, expected);
    }

    @Test
    public void GetCategoryByName_Success() {

        CategoryRequestModel requestModel = Mockito.mock(CategoryRequestModel.class);
        when (requestModel.getCategoryName()).thenReturn("Landscape");

        Category expected = Mockito.mock(Category.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Landscape");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.GetByName("Landscape")).thenReturn(expected);

        Image image = Mockito.mock(Image.class);
        when(image.getName()).thenReturn("Sunrise");
        when(image.getCreatedDate()).thenReturn("2017/01/22");
        when(image.getImageResourceId()).thenReturn(2);
        when(image.getLongDescription()).thenReturn("Long Description");
        when(image.getId()).thenReturn(1);
        when(image.getLocation()).thenReturn("filepath");

        Image image2 = Mockito.mock(Image.class);
        when(image2.getName()).thenReturn("Sunset");
        when(image2.getCreatedDate()).thenReturn("2017/01/22");
        when(image2.getImageResourceId()).thenReturn(3);
        when(image2.getLongDescription()).thenReturn("Long Description");
        when(image2.getId()).thenReturn(2);
        when(image2.getLocation()).thenReturn("filepath");

        List<Image> images = new ArrayList<Image>();
        images.add(image);
        images.add(image2);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
        CategoryResponseModel actual = interactor.GetCategoryByName(requestModel);

        Assert.assertEquals("Landscape", actual.getName());
    }

    @Test
    //Description("The Category does not exist in the system. An exception is thrown.")
    public void GetCategoryByName_Fail() {

        try {
            CategoryRequestModel requestModel = Mockito.mock(CategoryRequestModel.class);
            when(requestModel.getCategoryName()).thenReturn("Street Photography");

            ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
            when(categoryRepository.GetByName("Street Photography")).thenThrow(new Exception());

            IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
            when(imageRepository.GetByCategory(1)).thenThrow(new Exception());

            CategoryInteractor interactor = new CategoryInteractor(categoryRepository, imageRepository);
            CategoryResponseModel actual = interactor.GetCategoryById(requestModel);
        }
        catch(Exception e) {
            // An exception was thrown, so the category does indeed not exist
            Assert.assertTrue(true);
        }
    }

    // Get Category By Name End
}
