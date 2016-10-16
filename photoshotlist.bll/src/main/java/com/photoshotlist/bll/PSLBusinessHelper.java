package com.photoshotlist.bll;

import android.content.Context;
import android.database.SQLException;

import com.photoshotlist.common.Logger;
import com.photoshotlist.dal.ImageDAO;
import com.photoshotlist.dal.PSLDatabaseHelper;
import com.photoshotlist.dal.ShotListDAO;
import com.photoshotlist.exception.PSLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhpDev on 2016/08/21.
 */
public class PSLBusinessHelper {
    private static PSLBusinessHelper _instance;
    private Context _context;

    // TODO: Cannot use isEmpty()?
    private static final String EMPTY_STRING = "";

    private PSLBusinessHelper(Context context) { _context = context; };

    public static PSLBusinessHelper getInstance(Context context)
    {
        if (_instance == null) {
            _instance = new PSLBusinessHelper(context);
        }
        return _instance;
    }

    public ShotListDO InsertShotList(ShotListDO domainObject) throws PSLException
    {
        try {
            // Validation
            if (domainObject == null)
                throw new PSLException("Null ShotList object.");

            if (domainObject.getName() == null || domainObject.getName().toString().equals(EMPTY_STRING))
                throw new PSLException("Name is mandatory when creating a new ShotList.");

            // Setup link to data functionality


            // Use the runtime version of the database
            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);

            // Use the standalone version of the database
            // http://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
//            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);
//            try {
//                dbHelper.createDataBase();
//                dbHelper.openDataBase();
//
//            }catch(SQLException sqle){
//
//                throw sqle;
//            }

            // Check if the name is already in use
            ShotListDAO dao = null;
            dao = dbHelper.GetShotListByName(domainObject.getName());
            if(dao != null)
                throw new PSLException(String.format("The name '%s' is already in use.", domainObject.getName()));

            // The name is available for use. Insert the record
            Logger.Debug(this.getClass().getName(), "Before InsertShotList");
            int shotListId = dbHelper.InsertShotList(domainObject.getName(), domainObject.getLongDescription());
            Logger.Debug(this.getClass().getName(), "After InsertShotList");

            if(shotListId == -1) // There was an error in trying to add the DB entry
                throw new PSLException(String.format("A database error occurred when trying to create the new ShotList entry. Code: '%s'.", Integer.toString(shotListId)));

            // The record has been inserted successfully
            dao = dbHelper.GetShotListById(shotListId);
            if(dao == null)
                throw new PSLException(String.format("The ShotList was not found. ShotListId: '%s'.", Integer.toString(shotListId)));

            // TODO: Use Data to Business Mapper
            ShotListDO sdo = new ShotListDO();
            sdo.setName(dao.getName());
            sdo.setLongDescription(dao.getLongDescription());
            sdo.setActive(dao.isActive());
            sdo.setId(dao.getId());
            sdo.setCreatedDate(dao.getCreatedDate());

            return sdo;
        }
        catch(Exception ex)
        {
            throw new PSLException(ex.getMessage());
        }
    }

    public List<ShotListDO> GetAllCategories() throws PSLException
    {
        try {
            // Use the runtime version of the database
            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);

            // Use the standalone version of the database
            // http://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
//            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);
//            try {
//                dbHelper.createDataBase();
//                dbHelper.openDataBase();
//
//            }catch(SQLException sqle){
//
//                throw sqle;
//            }

            // The name is available for use. Insert the record
            Logger.Debug(this.getClass().getName(), "Before GetAllCategories");
            List<ShotListDAO> categoryList = dbHelper.GetAllCategories();
            Logger.Debug(this.getClass().getName(), "After InsertShotList");

            // TODO: Use Data to Business Mapper
            List<ShotListDO> categoryListDO = new ArrayList<ShotListDO>();
            for (ShotListDAO obj:categoryList) {
                ShotListDO sdo = new ShotListDO();
                sdo.setName(obj.getName());
                sdo.setLongDescription(obj.getLongDescription());
                sdo.setActive(obj.isActive());
                sdo.setId(obj.getId());

                categoryListDO.add(sdo);
            }

            return categoryListDO;
        }
        catch(Exception ex)
        {
            throw new PSLException(ex.getMessage());
        }
    }

    public ShotListDO GetCategoryById(int categoryId) throws PSLException
    {
        try {
            // Use the runtime version of the database
            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);

            // Use the standalone version of the database
            // http://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
//            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);
//            try {
//                dbHelper.createDataBase();
//                dbHelper.openDataBase();
//
//            }catch(SQLException sqle){
//
//                throw sqle;
//            }

            // The name is available for use. Insert the record
            Logger.Debug(this.getClass().getName(), "Before GetCategoryById");
            ShotListDAO category = dbHelper.GetCategoryById(categoryId);
            Logger.Debug(this.getClass().getName(), "After InsertShotList");

            // TODO: Use Data to Business Mapper
            ShotListDO sdo = new ShotListDO();
            sdo.setName(category.getName());
            sdo.setLongDescription(category.getLongDescription());
            sdo.setActive(category.isActive());
            sdo.setId(category.getId());

            return sdo;
        }
        catch(Exception ex)
        {
            throw new PSLException(ex.getMessage());
        }
    }

    public ImageDO GetImageByCategoryId(int categoryId) throws PSLException
    {
        try {
            // Use the runtime version of the database
            //PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);

            PSLDatabaseHelper dbHelper = new PSLDatabaseHelper(this._context);
            //dbHelper.createDataBase();
            //dbHelper.openDataBase();

            // Use the standalone version of the database
            // http://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
//            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);
//            try {
//                dbHelper.createDataBase();
//                dbHelper.openDataBase();
//
//            }catch(SQLException sqle){
//
//                throw sqle;
//            }

            // The name is available for use. Insert the record
            Logger.Debug(this.getClass().getName(), "Before GetCategoryById");
            ImageDAO image = dbHelper.GetImageByCategoryId(categoryId);
            Logger.Debug(this.getClass().getName(), "After InsertShotList");

            // TODO: Use Data to Business Mapper
            ImageDO sdo = new ImageDO();
            sdo.setName(image.getName());
            sdo.setLongDescription(image.getLongDescription());

            if(image.getLocation() != null && image.getLocation().isEmpty())
                sdo.setLocation("R.drawable.category_ina");
            else
                sdo.setLocation(image.getLocation());

            sdo.setImageResourceId(image.getImageResourceId());

            sdo.setActive(image.isActive());
            sdo.setId(image.getId());

            return sdo;
        }
        catch(Exception ex)
        {
            throw new PSLException(ex.getMessage());
        }
    }

    public List<ImageDO> GetPreviewImagesForCategories() throws PSLException
    {
        try {
            PSLDatabaseHelper dbHelper = new PSLDatabaseHelper(this._context);

            Logger.Debug(this.getClass().getName(), "GetPreviewImagesForCategories");
            List<ImageDAO> previewImageDAOList = dbHelper.GetPreviewImagesForCategories();

            List<ImageDO> previewImageDOList = new ArrayList<ImageDO>();
            for (ImageDAO obj:previewImageDAOList) {

                // TODO: Use Data to Business Mapper
                ImageDO sdo = new ImageDO();
                sdo.setName(obj.getName());
                sdo.setLongDescription(obj.getLongDescription());
                sdo.setLocation(obj.getLocation());
                sdo.setImageResourceId(obj.getImageResourceId());
                sdo.setActive(obj.isActive());
                sdo.setId(obj.getId());

                previewImageDOList.add(sdo);
            }

            return previewImageDOList;
        }
        catch(Exception ex)
        {
            throw new PSLException(ex.getMessage());
        }
    }
}