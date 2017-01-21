package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Image;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class ImageFactory implements IImageFactory {

    private static ImageFactory instance;

    private ImageFactory() { }

    public static ImageFactory getInstance()
    {
        if (instance == null) {
            instance = new ImageFactory();
        }
        return instance;
    }

    // TODO: How to get the client not to call this
    public Image create(int id, String name, String longDescription, String location,
                        int imageResourceId, String createdDate, boolean isActive) {

        Image image = new Image();

        if(id < 0)
            return null;
        else
            image.setId(id);

        // The name is mandatory. If not filled in return a NULL
        if ((name == null) || (name.length() == 0))
            return null;
        else
            image.setName(name);

        if ((longDescription != null) && (name.length() > 0))
            image.setLongDescription(longDescription);

        if ((location != null) && (location.length() > 0))
            image.setLocation(location);

        if (imageResourceId > 0)
            image.setImageResourceId(imageResourceId);

        if ((createdDate != null) && (createdDate.length() > 0))
            image.setCreatedDate(createdDate);

        image.setActive(isActive);

        return image;
    }
}
