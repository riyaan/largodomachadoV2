package com.photoshotlist.domainservices.repositories;

import com.photoshotlist.domainmodels.entities.Composition;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface ICompositionRepository {

    public Composition GetById(int id);
    public List<Composition> GetAll();
    public Composition GetByName(String name);

}
