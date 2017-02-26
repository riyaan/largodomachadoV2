package com.photoshotlist.boundaries.input.factories;

import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface ICompositionResponseModelFactory {
    public CompositionResponseModel create(int id, String name, String longDescription,
                                           int imageResourceId, boolean isActive,
                                           List<ImageResponseModel> imageResponseModelList);
}
