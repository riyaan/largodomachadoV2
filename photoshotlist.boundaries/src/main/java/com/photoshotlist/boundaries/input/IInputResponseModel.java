package com.photoshotlist.boundaries.input;

import java.util.List;

/**
 * Created by PhpDev on 2017/01/14.
 */

public interface IInputResponseModel {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    String getLongDescription();

    void setLongDescription(String longDescription);

    boolean isActive();

    void setActive(boolean active);

    int getImageResourceId();

    void setImageResourceId(int imageResourceId);

    List<ImageResponseModel> getImageResponseModels();

    void setImageResponseModels(List<ImageResponseModel> imageResponseModels);
}
