package com.photoshotlist.boundaries.input;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public class CategoryResponseModel implements IInputResponseModel {
    private int id;
    private String name;
    private String longDescription;
    private boolean isActive;
    private int imageResourceId;
    private List<ImageResponseModel> imageResponseModels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public List<ImageResponseModel> getImageResponseModels() {
        return imageResponseModels;
    }

    public void setImageResponseModels(List<ImageResponseModel> imageResponseModels) {
        this.imageResponseModels = imageResponseModels;
    }
}
