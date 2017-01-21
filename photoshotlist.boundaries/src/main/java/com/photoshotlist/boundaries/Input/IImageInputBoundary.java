package com.photoshotlist.boundaries.Input;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface IImageInputBoundary {

    List<ImageResponseModel> GetImagesByCategory(ImageRequestModel requestModel);
}
