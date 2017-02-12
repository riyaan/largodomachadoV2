package com.photoshotlist.interactors;

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Composition;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface IChallengeMeInteractor {

    public Composition GetRandomComposition();
    public Category GetRandomCategory();
}
