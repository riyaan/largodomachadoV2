package com.photoshotlist.domainservices.factories;

import com.photoshotlist.domainmodels.entities.Composition;
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
public class DomainServicesCompositionFactoryTest {

    @Test
    public void CreateComposition_Success() {

        Composition composition = CompositionFactory.getInstance().create(1, "Rule of Thirds", "Blah blah blah",
                2, true, new ArrayList<Image>());

        Assert.assertEquals(composition.getName(), "Rule of Thirds");
    }

    @Test
    public void CreateCategory_InvalidId_Fail() {

        Composition composition = CompositionFactory.getInstance().create(-1, "Leaving Space", "Lorem ipsum",
                3, true, new ArrayList<Image>());

        Assert.assertEquals(null, composition);
    }

    @Test
    public void CreateComposition_NoName_Fail() {

        Composition composition = CompositionFactory.getInstance().create(1, "", "Lorem ipsum",
                3, true, new ArrayList<Image>());

        Assert.assertEquals(null, composition);
    }

    @Test
    public void CreateComposition_ImageList_Success() {

        Image image = Mockito.mock(Image.class);
        when(image.getName()).thenReturn("Sea");
        when(image.getCreatedDate()).thenReturn("2017/01/22");
        when(image.getImageResourceId()).thenReturn(2);
        when(image.getLongDescription()).thenReturn("Long Description");
        when(image.getId()).thenReturn(1);
        when(image.getLocation()).thenReturn("filepath");

        Image image2 = Mockito.mock(Image.class);
        when(image2.getName()).thenReturn("Jump");
        when(image2.getCreatedDate()).thenReturn("2017/01/22");
        when(image2.getImageResourceId()).thenReturn(3);
        when(image2.getLongDescription()).thenReturn("Long Description");
        when(image2.getId()).thenReturn(2);
        when(image2.getLocation()).thenReturn("filepath");

        List<Image> images = new ArrayList<Image>();
        images.add(image);
        images.add(image2);

        Composition composition = CompositionFactory.getInstance().create(1, "Leaving Space",
                "Lorem ipsum", 3, true, images);

        Assert.assertEquals(2, composition.getImages().size());
    }

    @Test
    public void CreateComposition_NoImageList_Success() {

        List<Image> images = new ArrayList<Image>();

        Composition composition = CompositionFactory.getInstance().create(1, "Leaving Space",
                "Lorem ipsum", 3, true, images);

        Assert.assertEquals(0, composition.getImages().size());
    }
}
