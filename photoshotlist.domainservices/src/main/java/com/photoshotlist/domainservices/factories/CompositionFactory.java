package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainmodels.entities.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CompositionFactory implements ICompositionFactory {

    private static CompositionFactory instance;

    private CompositionFactory() { }

    public static CompositionFactory getInstance()
    {
        if (instance == null) {
            instance = new CompositionFactory();
        }
        return instance;
    }

    // TODO: How to get the client not to call this
    public Composition create(int id, String name, String longDescription, int imageResourceId, boolean active, List<Image> imageList) {

        Composition composition = new Composition();

        if(id < 0)
            return null;
        else
            composition.setId(id);

        // The name is mandatory. If not filled in return a NULL
        if ((name == null) || (name.length() == 0))
            return null;
        else
            composition.setName(name);

        if ((longDescription != null) && (name.length() > 0))
            composition.setLongDescription(longDescription);

        if (imageResourceId > 0)
            composition.setImageResourceId(imageResourceId);

        composition.setActive(active);

        if(imageList.size() == 0)
            composition.setImages(new ArrayList<Image>());
        else
            composition.setImages(imageList);

        return composition;
    }
}