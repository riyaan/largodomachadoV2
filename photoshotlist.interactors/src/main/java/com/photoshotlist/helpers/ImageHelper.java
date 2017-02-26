package com.photoshotlist.helpers;

import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.boundaries.input.factories.ImageResponseModelFactory;
import com.photoshotlist.domainmodels.entities.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2017/02/26.
 */

public class ImageHelper {

    public static List<ImageResponseModel> LoadImages(List<Image> imageList)
    {
        List<ImageResponseModel> imageResponseModels = new ArrayList<ImageResponseModel>();
        for(Image image: imageList){

            ImageResponseModel irm = ImageResponseModelFactory.getInstance().create(image.getId(),
                    image.getName(), image.getLongDescription(), image.getLocation(),
                    image.getImageResourceId(), image.getCreatedDate(), image.isActive()
            );

            imageResponseModels.add(irm);
        }

        return imageResponseModels;
    }
}
