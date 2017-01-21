package com.photoshotlist.domainservices.repositories;

import com.photoshotlist.domainmodels.entities.Category;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface ICategoryRepository {

    public Category GetById(int id);
    public List<Category> GetAll();
    public Category GetByName(String name);

}
