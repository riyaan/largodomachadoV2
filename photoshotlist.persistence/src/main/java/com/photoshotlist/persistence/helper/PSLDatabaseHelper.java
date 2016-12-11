package com.photoshotlist.persistence.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.photoshotlist.domainmodels.entities.Category;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;


// TODO: Cleanup this class. But a prerequisite is to create Unit Tests first.

    public class PSLDatabaseHelper extends SQLiteAssetHelper {

    private static PSLDatabaseHelper instance;

    private static final String DB_NAME = "PhotoShotList.db";

    //The Android's default system path of your application database.
    //private static final String DB_PATH = "/data/data/com.photoshotlist/databases/";
    //data/data/com.photoshotlist/databases/PhotoShotList

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    private static final int DB_VERSION = 8; // TODO: Read from configuration file

    public PSLDatabaseHelper(Context context) throws Exception {
        super(context, DB_NAME, null, DB_VERSION);
        myContext = context;
        setForcedUpgrade();
    }

    //
    public static PSLDatabaseHelper getInstance(Context context) {
        if (context == null) {
            // Use the test db.
        } else {

            if (instance == null) {
                try {
                    instance = new PSLDatabaseHelper(context);
                } catch (Exception ex) {
                }
            }
        }
        return instance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // updateMyDatabase(db, DB_OLD_VERSION, DB_VERSION);
    }

    public List<Category> GetAllCategories() throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Category category = null;
        List<Category> categoryList = new ArrayList<Category>();

        try {
            db = this.getWritableDatabase();

            cursor = db.query("Category",
                    new String[]{"_id", "Name", "LongDescription", "IsActive"},
                    null, null, null, null, null);

            if (cursor.moveToFirst()) {

                do {
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    category = new Category();
                    category.setId(_id);
                    category.setName(_name);
                    category.setLongDescription(_longDescription);
                    category.setActive(_isActive);

                    categoryList.add(category);

                } while (cursor.moveToNext());
            }

            return categoryList;

        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            if (cursor != null)
                cursor.close();

            if (db != null)
                db.close();
        }
    }

}
