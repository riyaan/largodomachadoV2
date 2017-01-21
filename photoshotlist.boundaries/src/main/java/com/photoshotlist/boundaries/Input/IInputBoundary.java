package com.photoshotlist.boundaries.Input;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface IInputBoundary {

    CategoryResponseModel GetCategoryById(CategoryRequestModel requestModel);
    List<CategoryResponseModel> GetAllCategories();
}
