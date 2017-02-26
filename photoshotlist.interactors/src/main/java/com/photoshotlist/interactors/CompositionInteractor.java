package com.photoshotlist.interactors;

import com.photoshotlist.boundaries.input.CompositionRequestModel;
import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.ICompositionInputBoundary;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.boundaries.input.factories.CompositionResponseModelFactory;
import com.photoshotlist.boundaries.input.factories.ImageResponseModelFactory;
import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.repositories.ICompositionRepository;
import com.photoshotlist.domainservices.repositories.IImageRepository;
import com.photoshotlist.helpers.ImageHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class CompositionInteractor implements ICompositionInteractor, ICompositionInputBoundary {

    private ICompositionRepository compositionRepository;
    private IImageRepository imageRepository;

    public CompositionInteractor(ICompositionRepository compositionRepository, IImageRepository imageRepository){
        this.compositionRepository = compositionRepository;
        this.imageRepository = imageRepository;
    }

    // ICompositionInteractor Implementation
    @Override
    public Composition GetById(int id) {

        Composition composition = null;
        composition = this.compositionRepository.GetById(id);

        if(composition == null)
            return composition;

        composition.setImages(this.imageRepository.GetByComposition(composition.getId()));

        return composition;
    }

    @Override
    public List<Composition> GetAll() {
        List<Composition> compositionList = new ArrayList<Composition>();
        compositionList = this.compositionRepository.GetAll();

        for(int i=0; i<compositionList.size(); i++){
            List<Image> images = this.imageRepository.GetByComposition(compositionList.get(i).getId());

            Image image = new Image();
            List<Image> tempImage = new ArrayList<Image>();

            Composition composition = compositionList.get(i);

            // If no images exists for this Composition, use the default
            if(images.size() == 0) {
                image.setName("Image Unavailable"); // Using the Composition Name instead of the Image Name
                image.setId(0);
                image.setLongDescription("Image Description");
                image.setLocation("R.drawable.composition_ina"); // TODO: Store this in a config
                image.setImageResourceId(0);
                image.setActive(true);

                images.add(image);
                tempImage.add(images.get(0));
                compositionList.get(i).setImages(tempImage);
            }
            else
            {
                tempImage.add(images.get(0));
                compositionList.get(i).setImages(tempImage);
            }
        }

        return compositionList;
    }

    @Override
    public Composition GetByName(String name) {

        Composition composition = null;
        composition = this.compositionRepository.GetByName(name);

        if(composition == null)
            return composition;

        composition.setImages(this.imageRepository.GetByComposition(composition.getId()));

        return composition;
    }


    // ICompositionInputBoundary Implementation

    // This is called by the Delivery Mechanism -> input Boundary -> Interactor
    @Override
    public CompositionResponseModel GetCompositionById(CompositionRequestModel requestModel) {

        Composition composition = GetById(requestModel.getCompositionId());

        CompositionResponseModel responseModel = CompositionResponseModelFactory.getInstance().
                create(composition.getId(), composition.getName(), composition.getLongDescription(),
                        composition.getImageResourceId(), composition.isActive(),
                        ImageHelper.LoadImages(composition.getImages()));

        return responseModel;
    }

    @Override
    public List<CompositionResponseModel> GetAllCompositions() {
        List<Composition> categories = GetAll();

        List<CompositionResponseModel> compositionResponseModels = new ArrayList<CompositionResponseModel>();

        for(Composition item : categories){

            CompositionResponseModel crm = new CompositionResponseModel();
            crm.setId(item.getId());
            crm.setName(item.getName());
            crm.setLongDescription(item.getLongDescription());
            crm.setImageResourceId(item.getImageResourceId());
            crm.setActive(item.isActive());
            crm.setImageResponseModels(ImageHelper.LoadImages(item.getImages()));

            compositionResponseModels.add(crm);
        }

        return compositionResponseModels;
    }

    @Override
    public CompositionResponseModel GetCompositionByName(CompositionRequestModel requestModel) {

        Composition composition = GetByName(requestModel.getCompositionName());

        CompositionResponseModel responseModel = CompositionResponseModelFactory.getInstance().
                create(composition.getId(), composition.getName(), composition.getLongDescription(),
                        composition.getImageResourceId(), composition.isActive(),
                        ImageHelper.LoadImages(composition.getImages()));

        return responseModel;
    }
}