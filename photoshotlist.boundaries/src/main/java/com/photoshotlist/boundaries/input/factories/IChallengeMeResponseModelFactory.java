package com.photoshotlist.boundaries.input.factories;

import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.ChallengeMeResponseModel;
import com.photoshotlist.boundaries.input.CompositionResponseModel;

/**
 * Created by PhpDev on 2016/12/04.
 */
public interface IChallengeMeResponseModelFactory {
    public ChallengeMeResponseModel create(CategoryResponseModel categoryResponseModel,
                                           CompositionResponseModel compositionResponseModel);
}
