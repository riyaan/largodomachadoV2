package com.photoshotlist.boundaries.input;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class CompositionRequestModel {
    private int compositionId;
    private String compositionName;

    public int getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(int compostionId) {
        this.compositionId = compostionId;
    }

    public String getCompositionName() {
        return compositionName;
    }

    public void setCompositionName(String compostionName) {
        this.compositionName = compostionName;
    }
}
