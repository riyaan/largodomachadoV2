package com.photoshotlist.infrastructure.repositories;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CategoryRepository implements ICategoryRepository {

    public CategoryRepository(){
    }

    @Override
    public Category GetById(int id) {

        // TODO: Replace this Stub Method
        Category category = new Category();
        category.setId(1);
        category.setName("Stub Category");
        category.setActive(true);
        category.setImageResourceId(1);
        category.setLongDescription("Stub long description");

        if(category.getId() == id)
            return category;
        else
            return null;
    }

    @Override
    public List<Category> GetAll() {
        return null;
    }
}