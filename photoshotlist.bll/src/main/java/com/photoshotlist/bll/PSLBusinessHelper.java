package com.photoshotlist.bll;

import android.content.Context;

import com.photoshotlist.common.Logger;
import com.photoshotlist.dal.PSLDatabaseHelper;
import com.photoshotlist.dal.ShotListDAO;
import com.photoshotlist.exception.PSLException;

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
            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this._context);

            // Check if the name is already in use
            ShotListDAO dao = null;
            dao = dbHelper.GetShotListByName(domainObject.getName());
            if(dao == null)
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
}
