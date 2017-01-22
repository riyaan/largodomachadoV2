package activity;

import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.repositories.ICategoryRepository;
import com.photoshotlist.domainservices.repositories.IImageRepository;
import com.photoshotlist.interactors.CategoryInteractor;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class ActivityFragmentCategoryAllTest {

    @Test
    public void DisplayAllCategories_Success() {

        Category categoryOne = Mockito.mock(Category.class);
        when (categoryOne.getId()).thenReturn(1);
        when (categoryOne.getName()).thenReturn("Abstract");
        when (categoryOne.getLongDescription()).thenReturn("Blah blah blah");
        when (categoryOne.getImageResourceId()).thenReturn(2);
        when (categoryOne.isActive()).thenReturn(true);

        Category categoryTwo = Mockito.mock(Category.class);
        when (categoryTwo.getId()).thenReturn(2);
        when (categoryTwo.getName()).thenReturn("Landscape");
        when (categoryTwo.getLongDescription()).thenReturn("Lorum ipsum");
        when (categoryTwo.getImageResourceId()).thenReturn(3);
        when (categoryTwo.isActive()).thenReturn(false);

        List<Category> categories = new ArrayList<Category>();
        categories.add(categoryOne);
        categories.add(categoryTwo);

        ICategoryRepository categoryRepository = Mockito.mock(ICategoryRepository.class);
        when(categoryRepository.GetAll()).thenReturn(categories);

        IImageRepository imageRepository = Mockito.mock(IImageRepository.class);
        for(int i=0; i<categories.size(); i++){

            Image image1 = Mockito.mock(Image.class);
            when(image1.getName()).thenReturn("Sunrise" + i);
            when(image1.getCreatedDate()).thenReturn("2017/01/22");
            when(image1.getImageResourceId()).thenReturn(i+2);
            when(image1.getLongDescription()).thenReturn("Long Description" + i);
            when(image1.getId()).thenReturn(i+1);
            when(image1.getLocation()).thenReturn("filepath"+i);

            Image image2 = Mockito.mock(Image.class);
            when(image2.getName()).thenReturn("Sunset" + i);
            when(image2.getCreatedDate()).thenReturn("2017/01/22");
            when(image2.getImageResourceId()).thenReturn(i+3);
            when(image2.getLongDescription()).thenReturn("Long Description" + i);
            when(image2.getId()).thenReturn(i+2);
            when(image2.getLocation()).thenReturn("filepath"+i);

            List<Image> images = new ArrayList<Image>();
            images.add(image1);
            images.add(image2);

            when(imageRepository.GetByCategory(categories.get(i).getId())).thenReturn(images);
        }

        CategoryInteractor ci = new CategoryInteractor(categoryRepository, imageRepository);
        List<CategoryResponseModel> allCategories = ci.GetAllCategories();

        Assert.assertEquals(2, allCategories.size());
    }
}
