package com.photoshotlist.persistence.repositories;

import android.content.Context;
import android.content.pm.InstrumentationInfo;

import com.photoshotlist.domainmodels.entities.Category;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class CategoryRepositoryTest {

    @Test
    public void testGetAllCategories() {

        Assert.assertNotEquals("Failed", 1, 1);
        // Context context = new Activity();

//        Context context = getInstrumentation().getContext();
//
//        CategoryRepository categoryRepository = new CategoryRepository(context);
//
//        List<Category> categoryList = null;
//        try {
//            categoryRepository.GetAllCategories();
//        }catch (Exception ex){
//            Assert.assertEquals(0, 1);
//        }
//
//        assertNotEquals(0, categoryList.size());
    }
}
