package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.CategoryRequestModel;
import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.IInputBoundary;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class CategoryInteractor implements ICategoryInteractor, IInputBoundary {

    private ICategoryRepository categoryRepository;

    public CategoryInteractor(ICategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    // ICategoryInteractor Implementation
    @Override
    public Category GetById(int id) {
        Category category = this.categoryRepository.GetById(id);
        return category;
    }

    @Override
    public List<Category> GetAll() {
        List<Category> categoryList = new ArrayList<Category>();
        categoryList = this.categoryRepository.GetAll();
        return categoryList;
    }

    @Override
    public Category GetByName(String name) {
        Category category = this.categoryRepository.GetByName(name);
        return category;
    }


    // IInputBoundary Implementation

    // This is called by the Delivery Mechanism -> input Boundary -> Interactor
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

    @Override
    public List<CategoryResponseModel> GetAllCategories() {
        List<Category> categories = GetAll();

        List<CategoryResponseModel> categoryResponseModels = new ArrayList<CategoryResponseModel>();

        for(Category item : categories){

            CategoryResponseModel crm = new CategoryResponseModel();
            crm.setId(item.getId());
            crm.setName(item.getName());
            crm.setLongDescription(item.getLongDescription());
            crm.setImageResourceId(item.getImageResourceId());
            crm.setActive(item.isActive());

            categoryResponseModels.add(crm);
        }

        return categoryResponseModels;
    }

    @Override
    public CategoryResponseModel GetCategoryByName(CategoryRequestModel requestModel) {

        Category category = GetByName(requestModel.getCategoryName());

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