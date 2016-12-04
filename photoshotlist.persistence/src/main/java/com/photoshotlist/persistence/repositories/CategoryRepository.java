package com.photoshotlist.persistence.repositories;

import com.photoshotlist.domainmodels.entities.CategoryDO;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CategoryRepository implements ICategoryRepository {
    @Override
    public List<CategoryDO> GetAllCategories() {
        return null;
    }
}
