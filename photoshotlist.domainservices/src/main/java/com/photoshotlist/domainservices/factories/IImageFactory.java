package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Image;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface IImageFactory {
    public Image create(int id, String name, String longDescription, String location,
                        int imageResourceId, String createdDate, boolean isActive);
}
