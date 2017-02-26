package com.photoshotlist.boundaries.input.factories;

import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CompositionResponseModelFactory implements ICompositionResponseModelFactory {

    private static CompositionResponseModelFactory instance;

    private CompositionResponseModelFactory() { }

    public static CompositionResponseModelFactory getInstance()
    {
        if (instance == null) {
            instance = new CompositionResponseModelFactory();
        }
        return instance;
    }

    // TODO: How to get the client not to call this
    public CompositionResponseModel create(int id, String name, String longDescription,
                                           int imageResourceId, boolean isActive,
                                           List<ImageResponseModel> imageResponseModels) {

        CompositionResponseModel crm = new CompositionResponseModel();

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

        crm.setImageResponseModels(imageResponseModels);

        return crm;
    }
}