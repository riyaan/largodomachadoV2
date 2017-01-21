package com.photoshotlist.boundaries.Input.factories;

import com.photoshotlist.boundaries.Input.ImageResponseModel;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface IImageResponseModelFactory {
    public ImageResponseModel create(int id, String name, String longDescription, String location,
            int imageResourceId, String createdDate, boolean isActive);
}
