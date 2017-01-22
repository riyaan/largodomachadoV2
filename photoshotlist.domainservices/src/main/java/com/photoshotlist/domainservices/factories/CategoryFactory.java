package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CategoryFactory implements ICategoryFactory {

    private static CategoryFactory instance;

    private CategoryFactory() { }

    public static CategoryFactory getInstance()
    {
        if (instance == null) {
            instance = new CategoryFactory();
        }
        return instance;
    }

    // TODO: How to get the client not to call this
    public Category create(int id, String name, String longDescription, int imageResourceId, boolean active, List<Image> imageList) {

        Category category = new Category();

        if(id < 0)
            return null;
        else
            category.setId(id);

        // The name is mandatory. If not filled in return a NULL
        if ((name == null) || (name.length() == 0))
            return null;
        else
            category.setName(name);

        if ((longDescription != null) && (name.length() > 0))
            category.setLongDescription(longDescription);

        if (imageResourceId > 0)
            category.setImageResourceId(imageResourceId);

        category.setActive(active);

        if(imageList.size() == 0)
            category.setImages(new ArrayList<Image>());
        else
            category.setImages(imageList);

        return category;
    }
}
