package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Category;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface ICategoryFactory {
    Category create(String name, String longDescription, int imageResourceId, boolean active);
}
