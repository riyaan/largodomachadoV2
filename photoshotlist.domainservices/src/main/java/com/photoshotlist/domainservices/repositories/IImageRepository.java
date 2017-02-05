package com.photoshotlist.domainservices.repositories;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Image;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface IImageRepository {

//    public Image GetById(int id);
//    public List<Image> GetAll();
    public List<Image> GetByCategory(int categoryId);
    public List<Image> GetByComposition(int compositionId);

}
