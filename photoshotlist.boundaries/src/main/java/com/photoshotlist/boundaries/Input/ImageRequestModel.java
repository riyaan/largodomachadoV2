package com.photoshotlist.boundaries.input;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class ImageRequestModel {
    private int categoryId;
    private int compositionId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(int compositionId) {
        this.compositionId = compositionId;
    }
}
