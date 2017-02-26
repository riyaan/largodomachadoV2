package com.photoshotlist.boundaries.input;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class ChallengeMeResponseModel {

    private CategoryResponseModel categoryResponseModel;
    private CompositionResponseModel compositionResponseModel;

    public CategoryResponseModel getCategoryResponseModel() {
        return this.categoryResponseModel;
    }

    public void setCategoryResponseModel(CategoryResponseModel categoryResponseModel) {
        this.categoryResponseModel = categoryResponseModel;
    }

    public CompositionResponseModel getCompositionResponseModel() {
        return this.compositionResponseModel;
    }

    public void setCompositionResponseModel(CompositionResponseModel compositionResponseModel) {
        this.compositionResponseModel = compositionResponseModel;
    }
}
