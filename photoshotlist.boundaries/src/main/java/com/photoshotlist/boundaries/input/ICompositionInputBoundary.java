package com.photoshotlist.boundaries.input;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface ICompositionInputBoundary {

    CompositionResponseModel GetCompositionById(CompositionRequestModel requestModel);
    List<CompositionResponseModel> GetAllCompositions();
    CompositionResponseModel GetCompositionByName(CompositionRequestModel requestModel);
}
