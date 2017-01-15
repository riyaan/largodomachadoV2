package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.Input.CategoryRequestModel;
import com.photoshotlist.boundaries.Input.CategoryResponseModel;
import com.photoshotlist.boundaries.Input.IInputBoundary;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class CategoryInteractor implements ICategoryInteractor, IInputBoundary {

    private ICategoryRepository categoryRepository;

    public CategoryInteractor(ICategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category GetById(int id) {
        Category category = this.categoryRepository.GetById(id);
        return category;
    }


    // This is called by the Delivery Mechanism -> Input Boundary -> Interactor
    @Override
    public CategoryResponseModel GetCategoryById(CategoryRequestModel requestModel) {

        Category category = GetById(requestModel.getCategoryId());

        // TODO: Use a Factory method
        CategoryResponseModel responseModel = new CategoryResponseModel();
        responseModel.setId(category.getId());
        responseModel.setName(category.getName());
        responseModel.setLongDescription(category.getLongDescription());
        responseModel.setImageResourceId(category.getImageResourceId());
        responseModel.setActive(category.isActive());

        return responseModel;
    }
}