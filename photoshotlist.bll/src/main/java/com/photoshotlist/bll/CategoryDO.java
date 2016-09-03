package com.photoshotlist.bll;

/**
 * Created by PhpDev on 2016/08/21.
 */
public class CategoryDO {
    private int id;
    private String name;
    private String longDescription;
    private boolean isActive;
    private int imageResourceId; // TODO: This will be retrieved from the database

    public CategoryDO() {}

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
}
