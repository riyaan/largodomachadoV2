package com.photoshotlist.boundaries.Input.factories;

import com.photoshotlist.boundaries.Input.ImageResponseModel;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class ImageResponseModelFactory implements IImageResponseModelFactory {

    private static ImageResponseModelFactory instance;

    private ImageResponseModelFactory() { }

    public static ImageResponseModelFactory getInstance()
    {
        if (instance == null) {
            instance = new ImageResponseModelFactory();
        }
        return instance;
    }

    // TODO: How to get the client not to call this
    public ImageResponseModel create(int id, String name, String longDescription, String location,
                                     int imageResourceId, String createdDate, boolean isActive) {

        ImageResponseModel irm = new ImageResponseModel();

        if(id < 0)
            return null;
        else
            irm.setId(id);

        // The name is mandatory. If not filled in return a NULL
        if ((name == null) && (name.length() == 0))
            return null;
        else
            irm.setName(name);

        if ((longDescription != null) && (name.length() > 0))
            irm.setLongDescription(longDescription);

        if ((location != null) && (name.length() > 0))
            irm.setLocation(location);

        if (imageResourceId > 0)
            irm.setImageResourceId(imageResourceId);

        if ((createdDate != null) && (createdDate.length() > 0))
            irm.setCreatedDate(createdDate);

        irm.setActive(isActive);

        return irm;
    }
}