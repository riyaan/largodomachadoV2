package com.photoshotlist.infrastructure.repositories;

import android.content.Context;

import com.photoshotlist.domainmodels.entities.Image;
import com.photoshotlist.domainservices.factories.ImageFactory;
import com.photoshotlist.domainservices.repositories.IImageRepository;
import com.photoshotlist.infrastructure.helper.ImageDAO;
import com.photoshotlist.infrastructure.helper.PSLDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2016/12/04.
 */
public class ImageRepository implements IImageRepository {

    private Context _context;

    public ImageRepository(Context context){
        this._context = context;
    }

    @Override
    /**
     * Retrieve a list of Images for a specific Category from the Persistence mechanism
     @return A list of Images or an empty list when nothing exists for this Category
     @exception  Exception
     */
    public List<Image> GetByCategory(int id) {

        PSLDatabaseHelper databaseHelper = PSLDatabaseHelper.getInstance(_context);

        // TODO: Create a factory method
        List<ImageDAO> imageDAOs = new ArrayList<ImageDAO>();
        List<Image> images = new ArrayList<Image>();

        try {
            imageDAOs = databaseHelper.GetImagesForCategory(id);
        }catch (Exception ex) {
        }

        if(imageDAOs.size() == 0)
            return images;

        for(ImageDAO item : imageDAOs){

            Image image = ImageFactory.getInstance().create(item.getId(), item.getName(),
                    item.getLongDescription(), item.getLocation(), item.getImageResourceId(),
                    item.getCreatedDate(), item.isActive());

            if(item != null) {
                // We only need to show the Preview aka 'First' image.
                image.setName(item.getName()); // Using the Category Name instead of the Image Name
            }


            images.add(image);
        }

        return images;
    }
}