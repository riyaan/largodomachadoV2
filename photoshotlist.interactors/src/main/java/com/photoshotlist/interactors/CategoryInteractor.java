package com.photoshotlist.interactors;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class CategoryInteractor implements ICategoryInteractor {

    private ICategoryRepository categoryRepository;

    public CategoryInteractor(ICategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category GetById(int id) {
        Category category = this.categoryRepository.GetById(id);
        return category;
    }
}
