package com.photoshotlist.interactors;

import com.photoshotlist.domainmodels.entities.Composition;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface ICompositionInteractor {

    public Composition GetById(int id);
    public List<Composition> GetAll();
    public Composition GetByName(String name);
}
