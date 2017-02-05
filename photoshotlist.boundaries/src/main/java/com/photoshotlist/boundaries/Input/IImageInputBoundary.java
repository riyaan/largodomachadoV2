package com.photoshotlist.boundaries.input;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface IImageInputBoundary {

    List<ImageResponseModel> GetImagesByCategory(ImageRequestModel requestModel);
    List<ImageResponseModel> GetImagesByComposition(ImageRequestModel requestModel);
}
