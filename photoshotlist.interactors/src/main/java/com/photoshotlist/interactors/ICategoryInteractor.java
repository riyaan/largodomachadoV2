package com.photoshotlist.interactors;

import com.photoshotlist.domainmodels.entities.Category;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface ICategoryInteractor {

    public Category GetById(int id);
}
