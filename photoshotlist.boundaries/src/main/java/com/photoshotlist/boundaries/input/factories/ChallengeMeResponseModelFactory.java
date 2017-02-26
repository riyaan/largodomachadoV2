package com.photoshotlist.boundaries.input.factories;

import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.ChallengeMeResponseModel;
import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Composition;

import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class ChallengeMeResponseModelFactory implements IChallengeMeResponseModelFactory {

    private static ChallengeMeResponseModelFactory instance;

    private ChallengeMeResponseModelFactory() { }

    public static ChallengeMeResponseModelFactory getInstance()
    {
        if (instance == null) {
            instance = new ChallengeMeResponseModelFactory();
        }
        return instance;
    }

    @Override
    public ChallengeMeResponseModel create(CategoryResponseModel categoryResponseModel,
                                           CompositionResponseModel compositionResponseModel) {

        if(categoryResponseModel == null)
            return null;

        if(compositionResponseModel == null)
            return null;

        ChallengeMeResponseModel cmrm = new ChallengeMeResponseModel();
        cmrm.setCategoryResponseModel(categoryResponseModel);
        cmrm.setCompositionResponseModel(compositionResponseModel);

        return cmrm;

    }
}