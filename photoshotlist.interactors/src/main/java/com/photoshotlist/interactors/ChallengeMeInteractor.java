package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.CompositionRequestModel;
import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.IChallengeMeInputBoundary;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.boundaries.input.factories.ImageResponseModelFactory;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.domainservices.repositories.ICompositionRepository;
import com.photoshotlist.domainservices.repositories.IImageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class ChallengeMeInteractor implements IChallengeMeInteractor, IChallengeMeInputBoundary {

    private ICompositionRepository compositionRepository;
    private ICategoryRepository categoryRepository;
    private IImageRepository imageRepository;

    public ChallengeMeInteractor(ICompositionRepository compositionRepository, ICategoryRepository categoryRepository, IImageRepository imageRepository){
        this.compositionRepository = compositionRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
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

    @Override
    public Composition GetRandomComposition() {
        Composition composition = null;
        composition = this.compositionRepository.RandomComposition();

        if(composition == null)
            return composition;

        composition.setImages(this.imageRepository.GetByComposition(composition.getId()));

        return composition;
    }

    @Override
    public Category GetRandomCategory() {
        Category category = null;
        category = this.categoryRepository.RandomCategory();

        if(category == null)
            return category;

        category.setImages(this.imageRepository.GetByCategory(category.getId()));

        return category;
    }

    @Override
    public CompositionResponseModel RandomComposition() {
        Composition composition = GetRandomComposition();

        // TODO: Use a Factory method
        CompositionResponseModel responseModel = new CompositionResponseModel();
        responseModel.setId(composition.getId());
        responseModel.setName(composition.getName());
        responseModel.setLongDescription(composition.getLongDescription());
        responseModel.setImageResourceId(composition.getImageResourceId());
        responseModel.setActive(composition.isActive());
        responseModel.setImageResponseModels(LoadImages(composition.getImages()));

        return responseModel;
    }

    @Override
    public CategoryResponseModel RandomCategory() {
        Category category = GetRandomCategory();

        CategoryResponseModel responseModel = new CategoryResponseModel();
        responseModel.setId(category.getId());
        responseModel.setName(category.getName());
        responseModel.setLongDescription(category.getLongDescription());
        responseModel.setImageResourceId(category.getImageResourceId());
        responseModel.setActive(category.isActive());
        responseModel.setImageResponseModels(LoadImages(category.getImages()));

        return responseModel;
    }
}