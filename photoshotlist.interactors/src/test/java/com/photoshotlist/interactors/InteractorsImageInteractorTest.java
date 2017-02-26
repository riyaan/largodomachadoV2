package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.ImageRequestModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.factories.ImageFactory;
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
public class InteractorsImageInteractorTest {

    @Test
    public void GetAllImagesForCategory_Success() {

        Image imgOne = ImageFactory.getInstance().create(1, "Crate", "Blah blah blah", "filepath", 2,
                "2017/01/21", false);

        Image imgTwo = ImageFactory.getInstance().create(2, "Water", "Blah blah blah", "filepath2", 3,
                "2017/01/21", false);

        List<Image> images = new ArrayList<Image>();
        images.add(imgOne);
        images.add(imgTwo);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        ImageInteractor interactor = new ImageInteractor(imageRepository);
        List<Image> actual = interactor.GetImagesByCategory(1);

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void GetAllImageResponseModelsForCategory_Success() {

        Image imgOne = ImageFactory.getInstance().create(1, "Crate", "Blah blah blah", "filepath", 2,
                "2017/01/21", false);

        Image imgTwo = ImageFactory.getInstance().create(2, "Water", "Blah blah blah", "filepath2", 3,
                "2017/01/21", false);

        List<Image> images = new ArrayList<Image>();
        images.add(imgOne);
        images.add(imgTwo);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        ImageRequestModel irm = new ImageRequestModel();
        irm.setCategoryId(1);

        ImageInteractor interactor = new ImageInteractor(imageRepository);
        List<ImageResponseModel> actual = interactor.GetImagesByCategory(irm);

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void GetAllImagesForComposition_Success() {

        Image imgOne = ImageFactory.getInstance().create(1, "Crate", "Blah blah blah", "filepath", 2,
                "2017/01/21", false);

        Image imgTwo = ImageFactory.getInstance().create(2, "Water", "Blah blah blah", "filepath2", 3,
                "2017/01/21", false);

        List<Image> images = new ArrayList<Image>();
        images.add(imgOne);
        images.add(imgTwo);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        ImageInteractor interactor = new ImageInteractor(imageRepository);
        List<Image> actual = interactor.GetImagesByComposition(1);

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void GetAllResponseModelImagesForComposition_Success() {

        Image imgOne = ImageFactory.getInstance().create(1, "Crate", "Blah blah blah", "filepath", 2,
                "2017/01/21", false);

        Image imgTwo = ImageFactory.getInstance().create(2, "Water", "Blah blah blah", "filepath2", 3,
                "2017/01/21", false);

        List<Image> images = new ArrayList<Image>();
        images.add(imgOne);
        images.add(imgTwo);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        ImageRequestModel irm = new ImageRequestModel();
        irm.setCompositionId(1);

        ImageInteractor interactor = new ImageInteractor(imageRepository);
        List<ImageResponseModel> actual = interactor.GetImagesByComposition(irm);

        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void GetAllImagesForCategory_Fail() {

        List<Image> images = new ArrayList<Image>();

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        ImageInteractor interactor = new ImageInteractor(imageRepository);
        List<Image> actual = interactor.GetImagesByCategory(1);

        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void GetAllImageResponseModelsForCategory_Fail() {

        List<Image> images = new ArrayList<Image>();;

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByCategory(1)).thenReturn(images);

        ImageRequestModel irm = new ImageRequestModel();
        irm.setCategoryId(1);

        ImageInteractor interactor = new ImageInteractor(imageRepository);
        List<ImageResponseModel> actual = interactor.GetImagesByCategory(irm);

        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void GetAllImagesForComposition_Fail() {

        List<Image> images = new ArrayList<Image>();

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        ImageInteractor interactor = new ImageInteractor(imageRepository);
        List<Image> actual = interactor.GetImagesByComposition(1);

        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void GetAllImageResponseModelsForComposition_Fail() {

        List<Image> images = new ArrayList<Image>();;

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        when (imageRepository.GetByComposition(1)).thenReturn(images);

        ImageRequestModel irm = new ImageRequestModel();
        irm.setCompositionId(1);

        ImageInteractor interactor = new ImageInteractor(imageRepository);
        List<ImageResponseModel> actual = interactor.GetImagesByComposition(irm);

        Assert.assertEquals(0, actual.size());
    }
}
