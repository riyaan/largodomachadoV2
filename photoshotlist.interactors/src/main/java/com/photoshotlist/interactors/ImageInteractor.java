package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.IImageInputBoundary;
import com.photoshotlist.boundaries.input.ImageRequestModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.boundaries.input.factories.ImageResponseModelFactory;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.repositories.IImageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class ImageInteractor implements IImageInteractor, IImageInputBoundary {

    private IImageRepository imageRepository;

    public ImageInteractor(IImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    // ICategoryInputBoundary Implementation

    // This is called by the Delivery Mechanism -> input Boundary -> Interactor

    @Override
    public List<ImageResponseModel> GetImagesByCategory(ImageRequestModel requestModel) {

        List<Image> images = GetImagesByCategory(requestModel.getCategoryId());
        List<ImageResponseModel> imageResponseModels = new ArrayList<ImageResponseModel>();

        for(Image item : images){

            ImageResponseModel irm = ImageResponseModelFactory.getInstance().create(
                    item.getId(), item.getName(), item.getLongDescription(), item.getLocation(),
                    item.getImageResourceId(), item.getCreatedDate(), item.isActive());

            imageResponseModels.add(irm);
        }

        return imageResponseModels;
    }

    @Override
    public List<Image> GetImagesByCategory(int categoryId) {

        List<Image> images = new ArrayList<Image>();
        images = this.imageRepository.GetByCategory(categoryId);
        return images;
    }

    @Override
    public List<ImageResponseModel> GetImagesByComposition(ImageRequestModel requestModel) {

        List<Image> images = GetImagesByComposition(requestModel.getCompositionId());
        List<ImageResponseModel> imageResponseModels = new ArrayList<ImageResponseModel>();

        for(Image item : images){

            ImageResponseModel irm = ImageResponseModelFactory.getInstance().create(
                    item.getId(), item.getName(), item.getLongDescription(), item.getLocation(),
                    item.getImageResourceId(), item.getCreatedDate(), item.isActive());

            imageResponseModels.add(irm);
        }

        return imageResponseModels;
    }

    @Override
    public List<Image> GetImagesByComposition(int compositionId) {

        List<Image> images = new ArrayList<Image>();
        images = this.imageRepository.GetByComposition(compositionId);
        return images;
    }
}