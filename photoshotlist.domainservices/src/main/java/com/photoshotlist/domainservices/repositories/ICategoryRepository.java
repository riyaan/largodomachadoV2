package com.photoshotlist.domainservices.repositories;

import com.photoshotlist.domainmodels.entities.CategoryDO;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface ICategoryRepository {

    public List<CategoryDO> GetAllCategories();
}
