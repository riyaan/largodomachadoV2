package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.CategoryRequestModel;
import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.IInputBoundary;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.boundaries.input.factories.ImageResponseModelFactory;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.domainservices.repositories.IImageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class CategoryInteractor implements ICategoryInteractor, IInputBoundary {

    private ICategoryRepository categoryRepository;
    private IImageRepository imageRepository;

    public CategoryInteractor(ICategoryRepository categoryRepository, IImageRepository imageRepository){
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    // ICategoryInteractor Implementation
    @Override
    public Category GetById(int id) {

        Category category = null;
        category = this.categoryRepository.GetById(id);

        if(category == null)
            return category;

        category.setImages(this.imageRepository.GetByCategory(category.getId()));

        return category;
    }

    @Override
    public List<Category> GetAll() {
        List<Category> categoryList = new ArrayList<Category>();
        categoryList = this.categoryRepository.GetAll();

        for(int i=0; i<categoryList.size(); i++){
            List<Image> images = this.imageRepository.GetByCategory(categoryList.get(i).getId());

            Image image = new Image();
            List<Image> tempImage = new ArrayList<Image>();

            Category category = categoryList.get(i);

            // If no images exists for this Category, use the default
            if(images.size() == 0) {
                image.setName("Image Unavailable"); // Using the Category Name instead of the Image Name
                image.setId(0);
                image.setLongDescription("Image Description");
                image.setLocation("R.drawable.category_ina"); // TODO: Store this in a config
                image.setImageResourceId(0);
                image.setActive(true);

                images.add(image);
                tempImage.add(images.get(0));
                categoryList.get(i).setImages(tempImage);
            }
            else
            {
                tempImage.add(images.get(0));
                categoryList.get(i).setImages(tempImage);
            }
        }

        return categoryList;
    }

    @Override
    public Category GetByName(String name) {

        Category category = null;
        category = this.categoryRepository.GetByName(name);

        if(category == null)
            return category;

        category.setImages(this.imageRepository.GetByCategory(category.getId()));

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
        responseModel.setImageResponseModels(LoadImages(category.getImages()));

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
            crm.setImageResponseModels(LoadImages(item.getImages()));

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
        responseModel.setImageResponseModels(LoadImages(category.getImages()));

        return responseModel;
    }

    private List<ImageResponseModel> LoadImages(List<Image> imageList)
    {
        List<ImageResponseModel> imageResponseModels = new ArrayList<ImageResponseModel>();
        for(Image image: imageList){

            ImageResponseModel irm = ImageResponseModelFactory.getInstance().create(image.getId(),
                    image.getName(), image.getLongDescription(), image.getLocation(),
                    image.getImageResourceId(), image.getCreatedDate(), image.isActive()
            );

            imageResponseModels.add(irm);
        }

        return imageResponseModels;
    }
}