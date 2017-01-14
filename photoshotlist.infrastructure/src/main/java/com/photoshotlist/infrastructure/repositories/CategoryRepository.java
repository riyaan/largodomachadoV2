package com.photoshotlist.infrastructure.repositories;

import android.content.Context;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.infrastructure.helper.CategoryDAO;
import com.photoshotlist.infrastructure.helper.PSLDatabaseHelper;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CategoryRepository implements ICategoryRepository {

    private Context _context;

    public CategoryRepository(Context context){
        this._context = context;
    }

    @Override
    public Category GetById(int id) {

        PSLDatabaseHelper databaseHelper = PSLDatabaseHelper.getInstance(_context);

        // TODO: Create a factory method
        CategoryDAO categoryDAO = new CategoryDAO();

        try {
            categoryDAO = databaseHelper.GetCategoryById(id);
        }catch (Exception ex) {
        }

        // TODO: Category Factory
        Category category = new Category();
        category.setId(categoryDAO.getId()) ;
        category.setName(categoryDAO.getName());
        category.setActive(categoryDAO.isActive());
        category.setImageResourceId(categoryDAO.getImageResourceId());
        category.setLongDescription(categoryDAO.getLongDescription());

        return category;
    }

    @Override
    public List<Category> GetAll() {
        return null;
    }
}