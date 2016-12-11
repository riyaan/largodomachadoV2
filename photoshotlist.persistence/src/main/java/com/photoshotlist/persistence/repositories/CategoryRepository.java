package com.photoshotlist.persistence.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.persistence.helper.PSLDatabaseHelper;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class CategoryRepository implements ICategoryRepository {

    private Context context;

    public CategoryRepository(Context context)
    {
        this.context = context;
    }

    public List<Category> GetAllCategories() throws Exception  {

        PSLDatabaseHelper helper = PSLDatabaseHelper.getInstance(context);

        List<Category> categoryList = null;
        try {
            categoryList = helper.GetAllCategories();
        }catch (Exception ex) {
            throw new Exception(ex);
        }

        return categoryList;
    }
}
