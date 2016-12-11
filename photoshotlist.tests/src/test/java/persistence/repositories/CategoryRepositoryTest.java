package persistence.repositories;

import android.app.Activity;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.persistence.repositories.CategoryRepository;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by PhpDev on 2016/12/11.
 */
public class CategoryRepositoryTest {

    @Test
    public void getAllCategoriesTest() implements AndroidTestCase{

        // Context context = new Activity();

        //Context context = getInstrumentation().getContext();

        CategoryRepository categoryRepository = new CategoryRepository(context);

        List<Category> categoryList = null;
        try {
            categoryRepository.GetAllCategories();
        }catch (Exception ex){
            assertEquals(0, 1);
        }

        assertNotEquals(0, categoryList.size());
    }
}
