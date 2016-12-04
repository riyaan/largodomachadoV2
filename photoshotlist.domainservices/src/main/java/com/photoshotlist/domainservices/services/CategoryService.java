package com.photoshotlist.domainservices.services;

import com.photoshotlist.domainservices.repositories.ICategoryRepository;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CategoryService {
    protected ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }
}
