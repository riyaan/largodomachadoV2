package com.photoshotlist.boundaries.input;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface IChallengeMeInputBoundary {

    CompositionResponseModel RandomComposition();
    CategoryResponseModel RandomCategory();
}