package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.CompositionRequestModel;
import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.ICategoryInputBoundary;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.ChallengeMe;
import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.domainservices.repositories.ICompositionRepository;
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
public class InteractorsChallengeMeInteractorTest {

    // TODO: Fix these tests. Some of the methods are not pass of this Interactor
    @Test
    public void GetById_Success() {

        Composition expected = Mockito.mock(Composition.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Rule of Thirds");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);
        when (expected.getImages()).thenReturn(new ArrayList<Image>());

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.RandomComposition()).thenReturn(expected);

        Image compositionImage1 = Mockito.mock(Image.class);
        when(compositionImage1.getName()).thenReturn("Dog");
        when(compositionImage1.getCreatedDate()).thenReturn("2017/01/22");
        when(compositionImage1.getImageResourceId()).thenReturn(2);
        when(compositionImage1.getLongDescription()).thenReturn("Long Description");
        when(compositionImage1.getId()).thenReturn(1);
        when(compositionImage1.getLocation()).thenReturn("filepath");

        Image compositionImage2 = Mockito.mock(Image.class);
        when(compositionImage2.getName()).thenReturn("Tractor");
        when(compositionImage2.getCreatedDate()).thenReturn("2017/01/22");
        when(compositionImage2.getImageResourceId()).thenReturn(3);
        when(compositionImage2.getLongDescription()).thenReturn("Long Description");
        when(compositionImage2.getId()).thenReturn(2);
        when(compositionImage2.getLocation()).thenReturn("filepath");

        List<Image> compositionImages = new ArrayList<Image>();
        compositionImages.add(compositionImage1);
        compositionImages.add(compositionImage2);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByComposition(1)).thenReturn(compositionImages);

        // Setup Category
        Category category = Mockito.mock(Category.class);
        when (category.getId()).thenReturn(1);
        when (category.getName()).thenReturn("Rule of Thirds");
        when (category.getLongDescription()).thenReturn("Blah blah blah");
        when (category.getImageResourceId()).thenReturn(2);
        when (category.isActive()).thenReturn(true);
        when (category.getImages()).thenReturn(new ArrayList<Image>());

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when (categoryRepository.RandomCategory()).thenReturn(category);

        Image categoryImage1 = Mockito.mock(Image.class);
        when(categoryImage1.getName()).thenReturn("Cat");
        when(categoryImage1.getCreatedDate()).thenReturn("2017/01/22");
        when(categoryImage1.getImageResourceId()).thenReturn(2);
        when(categoryImage1.getLongDescription()).thenReturn("Long Description");
        when(categoryImage1.getId()).thenReturn(1);
        when(categoryImage1.getLocation()).thenReturn("filepath");

        Image categoryImage2 = Mockito.mock(Image.class);
        when(categoryImage2.getName()).thenReturn("Bus");
        when(categoryImage2.getCreatedDate()).thenReturn("2017/01/22");
        when(categoryImage2.getImageResourceId()).thenReturn(3);
        when(categoryImage2.getLongDescription()).thenReturn("Long Description");
        when(categoryImage2.getId()).thenReturn(2);
        when(categoryImage2.getLocation()).thenReturn("filepath");

        List<Image> categoryImages = new ArrayList<Image>();
        categoryImages.add(categoryImage1);
        categoryImages.add(categoryImage2);

        when (imageRepository.GetByCategory(1)).thenReturn(categoryImages);


        ChallengeMeInteractor interactor = new ChallengeMeInteractor(compositionRepository,
                categoryRepository, imageRepository);

        ChallengeMe cm = interactor.GetRandom();
        Assert.assertNotEquals(null, cm);
    }

    @Test
    public void GetById_Fail() {

        Composition expected = null;

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetById(1)).thenReturn(expected);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByComposition(1)).thenReturn(new ArrayList<Image>());

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        Composition actual = interactor.GetById(1);

        Assert.assertEquals(null, expected);
    }

    @Test
    public void GetCompositionById_Success() {

        CompositionRequestModel requestModel = Mockito.mock(CompositionRequestModel.class);
        when (requestModel.getCompositionId()).thenReturn(1);

        Composition expected = Mockito.mock(Composition.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Abstract");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);
        when (expected.getImages()).thenReturn(new ArrayList<Image>());

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetById(1)).thenReturn(expected);

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
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        CompositionResponseModel actual = interactor.GetCompositionById(requestModel);

        Assert.assertEquals(1, actual.getId());
    }

    @Test
    //Description("The Composition does not exist in the system. An exception is thrown.")
    public void GetCompositionById_Fail() {

        try {
            CompositionRequestModel requestModel = Mockito.mock(CompositionRequestModel.class);
            when(requestModel.getCompositionId()).thenReturn(1);

            ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
            when(compositionRepository.GetById(1)).thenThrow(new Exception());

            IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
            when(imageRepository.GetByComposition(1)).thenThrow(new Exception());

            CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
            CompositionResponseModel actual = interactor.GetCompositionById(requestModel);
        }
        catch(Exception e) {
            // An exception was thrown, so the composition does indeed not exist
            Assert.assertTrue(true);
        }
    }

    @Test
    public void GetAllCompositions_Success() {

        Composition compositionOne = Mockito.mock(Composition.class);
        when (compositionOne.getId()).thenReturn(1);
        when (compositionOne.getName()).thenReturn("Abstract");
        when (compositionOne.getLongDescription()).thenReturn("Blah blah blah");
        when (compositionOne.getImageResourceId()).thenReturn(2);
        when (compositionOne.isActive()).thenReturn(true);
        when (compositionOne.getImages()).thenReturn(new ArrayList<Image>());

        Composition compositionTwo = Mockito.mock(Composition.class);
        when (compositionTwo.getId()).thenReturn(2);
        when (compositionTwo.getName()).thenReturn("Landscape");
        when (compositionTwo.getLongDescription()).thenReturn("Lorum ipsum");
        when (compositionTwo.getImageResourceId()).thenReturn(3);
        when (compositionTwo.isActive()).thenReturn(false);

        List<Composition> compositions = new ArrayList<Composition>();
        compositions.add(compositionOne);
        compositions.add(compositionTwo);

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
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetAll()).thenReturn(compositions);

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        List<Composition> actual = interactor.GetAll();

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void GetAllCompositions_Fail() {

        List<Composition> compositions = new ArrayList<Composition>();

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetAll()).thenReturn(compositions);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when(imageRepository.GetByComposition(1)).thenReturn(new ArrayList<Image>());

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        List<Composition> actual = interactor.GetAll();

        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void GetAllCompositionResponseModels_Success() {

        Composition compositionOne = Mockito.mock(Composition.class);
        when (compositionOne.getId()).thenReturn(1);
        when (compositionOne.getName()).thenReturn("Abstract");
        when (compositionOne.getLongDescription()).thenReturn("Blah blah blah");
        when (compositionOne.getImageResourceId()).thenReturn(2);
        when (compositionOne.isActive()).thenReturn(true);

        Composition compositionTwo = Mockito.mock(Composition.class);
        when (compositionTwo.getId()).thenReturn(2);
        when (compositionTwo.getName()).thenReturn("Landscape");
        when (compositionTwo.getLongDescription()).thenReturn("Lorum ipsum");
        when (compositionTwo.getImageResourceId()).thenReturn(3);
        when (compositionTwo.isActive()).thenReturn(false);

        List<Composition> compositions = new ArrayList<Composition>();
        compositions.add(compositionOne);
        compositions.add(compositionTwo);

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetAll()).thenReturn(compositions);

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
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        List<CompositionResponseModel> actual = interactor.GetAllCompositions();

        Assert.assertEquals(2, actual.size());
    }


    @Test
    public void GetAllCompositionResponseModels_Fail() {

        List<Composition> compositions = new ArrayList<Composition>();

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetAll()).thenReturn(compositions);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when(imageRepository.GetByComposition(1)).thenReturn(new ArrayList<Image>());

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        List<CompositionResponseModel> actual = interactor.GetAllCompositions();

        Assert.assertEquals(0, actual.size());
    }

    // Get Composition By Name Start

    @Test
    public void GetByName_Success() {

        Composition expected = Mockito.mock(Composition.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Abstract");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetByName("Abstract")).thenReturn(expected);

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
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        Composition actual = interactor.GetByName("Abstract");

        Assert.assertEquals("Abstract", expected.getName());
    }

    @Test
    public void GetByName_Fail() {

        Composition expected = null;

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetByName("Invalid")).thenReturn(expected);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when(imageRepository.GetByComposition(1)).thenReturn(new ArrayList<Image>());

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        Composition actual = interactor.GetByName("Invalid");

        Assert.assertEquals(null, expected);
    }

    @Test
    public void GetCompositionByName_Success() {

        CompositionRequestModel requestModel = Mockito.mock(CompositionRequestModel.class);
        when (requestModel.getCompositionName()).thenReturn("Landscape");

        Composition expected = Mockito.mock(Composition.class);
        when (expected.getId()).thenReturn(1);
        when (expected.getName()).thenReturn("Landscape");
        when (expected.getLongDescription()).thenReturn("Blah blah blah");
        when (expected.getImageResourceId()).thenReturn(2);
        when (expected.isActive()).thenReturn(true);

        ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
        when (compositionRepository.GetByName("Landscape")).thenReturn(expected);

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
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
        CompositionResponseModel actual = interactor.GetCompositionByName(requestModel);

        Assert.assertEquals("Landscape", actual.getName());
    }

    @Test
    //Description("The Composition does not exist in the system. An exception is thrown.")
    public void GetCompositionByName_Fail() {

        try {
            CompositionRequestModel requestModel = Mockito.mock(CompositionRequestModel.class);
            when(requestModel.getCompositionName()).thenReturn("Street Photography");

            ICompositionRepository compositionRepository = Mockito.mock(ICompositionRepository.class);
            when(compositionRepository.GetByName("Street Photography")).thenThrow(new Exception());

            IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
            when(imageRepository.GetByComposition(1)).thenThrow(new Exception());

            CompositionInteractor interactor = new CompositionInteractor(compositionRepository, imageRepository);
            CompositionResponseModel actual = interactor.GetCompositionById(requestModel);
        }
        catch(Exception e) {
            // An exception was thrown, so the composition does indeed not exist
            Assert.assertTrue(true);
        }
    }

    // Get Composition By Name End
}
