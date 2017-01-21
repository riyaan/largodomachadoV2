package com.photoshotlist.interactors;

import com.photoshotlist.domainmodels.entities.Image;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface IImageInteractor {

    public List<Image> GetImagesByCategory(int categoryId);
}
