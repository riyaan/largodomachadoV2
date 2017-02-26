package com.photoshotlist.boundaries.input.factories;

import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CategoryResponseModelFactory implements ICategoryResponseModelFactory {

    private static CategoryResponseModelFactory instance;

    private CategoryResponseModelFactory() { }

    public static CategoryResponseModelFactory getInstance()
    {
        if (instance == null) {
            instance = new CategoryResponseModelFactory();
        }
        return instance;
    }

    // TODO: How to get the client not to call this
    public CategoryResponseModel create(int id, String name, String longDescription,
                                        int imageResourceId, boolean isActive,
                                        List<ImageResponseModel> imageResponseModelList) {

        CategoryResponseModel crm = new CategoryResponseModel();

        if(id < 0)
            return null;
        else
            crm.setId(id);

        // The name is mandatory. If not filled in return a NULL
        if ((name == null) || (name.length() == 0))
            return null;
        else
            crm.setName(name);

        if ((longDescription != null) && (name.length() > 0))
            crm.setLongDescription(longDescription);

        if (imageResourceId > 0)
            crm.setImageResourceId(imageResourceId);

        crm.setActive(isActive);

        crm.setImageResponseModels(imageResponseModelList);

        return crm;
    }
}