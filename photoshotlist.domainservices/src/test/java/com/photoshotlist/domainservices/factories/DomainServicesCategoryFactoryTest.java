package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Image;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by PhpDev on 2016/12/18.
 */
public class DomainServicesCategoryFactoryTest {

    @Test
    public void CreateCategory_Success() {

        Category category = CategoryFactory.getInstance().create(1, "Abstract", "Blah blah blah",
                2, true, new ArrayList<Image>());

        Assert.assertEquals(category.getName(), "Abstract");
    }

    @Test
    public void CreateCategory_InvalidId_Fail() {

        Category category = CategoryFactory.getInstance().create(-1, "Closeup", "Lorem ipsum",
                3, true, new ArrayList<Image>());

        Assert.assertEquals(null, category);
    }

    @Test
    public void CreateCategory_NoName_Fail() {

        Category category = CategoryFactory.getInstance().create(1, "", "Lorem ipsum",
                3, true, new ArrayList<Image>());

        Assert.assertEquals(null, category);
    }

    @Test
    public void CreateCategory_ImageList_Success() {

        Image image = Mockito.mock(Image.class);
        when(image.getName()).thenReturn("Sunrise");
        when(image.getCreatedDate()).thenReturn("2017/01/22");
        when(image.getImageResourceId()).thenReturn(2);
        when(image.getLongDescription()).thenReturn("Long Description");
        when(image.getId()).thenReturn(1);
        when(image.getLocation()).thenReturn("filepath");

        Image image2 = Mockito.mock(Image.class);
        when(image2.getName()).thenReturn("Sunset");
        when(image2.getCreatedDate()).thenReturn("2017/01/22");
        when(image2.getImageResourceId()).thenReturn(3);
        when(image2.getLongDescription()).thenReturn("Long Description");
        when(image2.getId()).thenReturn(2);
        when(image2.getLocation()).thenReturn("filepath");

        List<Image> images = new ArrayList<Image>();
        images.add(image);
        images.add(image2);

        Category category = CategoryFactory.getInstance().create(1, "Landscape",
                "Lorem ipsum", 3, true, images);

        Assert.assertEquals(2, category.getImages().size());
    }

    @Test
    public void CreateCategory_NoImageList_Success() {

        List<Image> images = new ArrayList<Image>();

        Category category = CategoryFactory.getInstance().create(1, "Landscape",
                "Lorem ipsum", 3, true, images);

        Assert.assertEquals(0, category.getImages().size());
    }
}
