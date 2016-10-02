package com.photoshotlist.dal;

/**
 * Created by PhpDev on 2016/08/06.
 */
public class ImageDAO {
    private int id;
    private String name;
    private String longDescription;
    private String location;
    private int imageResourceId;
    private boolean isActive;

    public ImageDAO() {}

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

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

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
