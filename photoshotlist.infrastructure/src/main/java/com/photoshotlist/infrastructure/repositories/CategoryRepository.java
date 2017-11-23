package com.photoshotlist.infrastructure.repositories;

import android.content.Context;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.factories.CategoryFactory;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.infrastructure.helper.CategoryDAO;
import com.photoshotlist.infrastructure.helper.PSLDatabaseHelper;
import com.photoshotlist.infrastructure.helper.PSLDatabaseHelperFactory;

import java.util.ArrayList;
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
    /**
     * Retrieve a Category from the Persistence mechanism
     @return A Category or NULL object when the category does not exist
     @exception  Exception
     */
    public Category GetById(int id) {

        PSLDatabaseHelper databaseHelper = PSLDatabaseHelperFactory.getInstance().create(_context);

        CategoryDAO categoryDAO = null;
        Category category = null;

        try {
            categoryDAO = databaseHelper.GetCategoryById(id);
        }catch (Exception ex) {
        }

        if(categoryDAO == null)
            return  category;

        category = CategoryFactory.getInstance().create(categoryDAO.getId(), categoryDAO.getName(),
                categoryDAO.getLongDescription(), categoryDAO.getImageResourceId(),
                categoryDAO.isActive(), new ArrayList<Image>());

        return category;
    }

    @Override
    /**
     * Retrieve all Categories from the Persistence mechanism
     @return A list of Categories or an empty list when no Categories exist
     @exception  Exception
     */
    public List<Category> GetAll() {

        PSLDatabaseHelper databaseHelper = PSLDatabaseHelperFactory.getInstance().create(_context);

        List<CategoryDAO> categoryDAOList = new ArrayList<CategoryDAO>();
        List<Category> categoryList = new ArrayList<Category>();

        try {
            categoryDAOList = databaseHelper.GetAllCategories();
        }catch (Exception ex) {
            // TODO: Handle database exceptions
        }

        if(categoryDAOList.size() == 0)
            return  categoryList;

        for(CategoryDAO item : categoryDAOList) {

            Category category = CategoryFactory.getInstance().create(item.getId(), item.getName(),
                    item.getLongDescription(), item.getImageResourceId(),
                    item.isActive(), new ArrayList<Image>());

            categoryList.add(category);
        }

        return categoryList;
    }

    @Override
    /**
     * Retrieve a Category from the Persistence mechanism
     @return A Category or NULL object when the category does not exist
     @exception  Exception
     */
    public Category GetByName(String name) {

        PSLDatabaseHelper databaseHelper = PSLDatabaseHelperFactory.getInstance().create(_context);

        CategoryDAO categoryDAO = null;
        Category category = null;

        try {
            categoryDAO = databaseHelper.GetCategoryByName(name);
        }catch (Exception ex) {
        }

        if(categoryDAO == null)
            return  category;

        category = CategoryFactory.getInstance().create(categoryDAO.getId(), categoryDAO.getName(),
                categoryDAO.getLongDescription(), categoryDAO.getImageResourceId(),
                categoryDAO.isActive(), new ArrayList<Image>());

        return category;
    }

    @Override
    public Category RandomCategory()
    {
        PSLDatabaseHelper databaseHelper = PSLDatabaseHelperFactory.getInstance().create(_context);

        CategoryDAO categoryDAO = null;
        Category category = null;

        try {
            categoryDAO = databaseHelper.RandomCategory();
        }catch (Exception ex) {
        }

        if(categoryDAO == null)
            return  category;

        category = CategoryFactory.getInstance().create(categoryDAO.getId(), categoryDAO.getName(),
                categoryDAO.getLongDescription(), categoryDAO.getImageResourceId(),
                categoryDAO.isActive(), new ArrayList<Image>());

        return category;
    }
}