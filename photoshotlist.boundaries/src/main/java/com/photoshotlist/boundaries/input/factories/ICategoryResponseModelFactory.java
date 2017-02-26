package com.photoshotlist.boundaries.input.factories;

import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface ICategoryResponseModelFactory {
    public CategoryResponseModel create(int id, String name, String longDescription,
                                        int imageResourceId, boolean isActive,
                                        List<ImageResponseModel> imageResponseModels);
}
