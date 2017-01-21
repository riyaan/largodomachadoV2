package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.Input.CategoryRequestModel;
import com.photoshotlist.boundaries.Input.CategoryResponseModel;
import com.photoshotlist.boundaries.Input.IImageInputBoundary;
import com.photoshotlist.boundaries.Input.IInputBoundary;
import com.photoshotlist.boundaries.Input.ImageRequestModel;
import com.photoshotlist.boundaries.Input.ImageResponseModel;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
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

            ImageResponseModel irm = new ImageResponseModel();
            irm.setActive(item.isActive());
            irm.setLocation(item.getLocation());
            irm.setCreatedDate(item.getCreatedDate());
            irm.setLongDescription(item.getLongDescription());
            irm.setLocation(item.getLocation());
            irm.setId(item.getId());
            irm.setImageResourceId(item.getImageResourceId());
            irm.setName(item.getName());

            imageResponseModels.add(irm);
        }

        return imageResponseModels;
    }

    @Override
    public List<Image> GetImagesByCategory(int categoryId) {

        List<Image> images = new ArrayList<Image>();
        images = this.GetImagesByCategory(categoryId);
        return images;
    }
}