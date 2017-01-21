package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.Input.IImageInputBoundary;
import com.photoshotlist.boundaries.Input.ImageRequestModel;
import com.photoshotlist.boundaries.Input.ImageResponseModel;
import com.photoshotlist.boundaries.Input.factories.ImageResponseModelFactory;
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

    // IInputBoundary Implementation

    // This is called by the Delivery Mechanism -> Input Boundary -> Interactor

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
}