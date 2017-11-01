package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainmodels.entities.Image;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface ICompositionFactory {
    public Composition create(int id, String name, String longDescription, int imageResourceId, boolean active, List<Image> imageList);
}
