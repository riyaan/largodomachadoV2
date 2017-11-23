package com.photoshotlist.infrastructure.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;


// TODO: Cleanup this class. But a prerequisite is to create Unit Tests first.

    public class PSLDatabaseHelper extends SQLiteAssetHelper {

    private PSLDatabaseHelper instance;

    private static final String DB_NAME = "PhotoShotList.db";

    //The Android's default system path of your application database.
    //private static final String DB_PATH = "/data/data/com.photoshotlist/databases/";
    //data/data/com.photoshotlist/databases/PhotoShotList

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    private static final int DB_VERSION = 14; // TODO: Read from configuration file

    public PSLDatabaseHelper(Context context) throws Exception {
        super(context, DB_NAME, null, DB_VERSION);
        myContext = context;
        setForcedUpgrade();
    }

    //
    public PSLDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            try {
                instance = new PSLDatabaseHelper(context);
            }catch (Exception ex) {

            }
        }

        return instance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // updateMyDatabase(db, DB_OLD_VERSION, DB_VERSION);
    }

        /**
         * Retrieve all Categories from the Persistence mechanism
         @return A list of Categories or an empty list when no Categories exist
         @exception  Exception
         */
    public List<CategoryDAO> GetAllCategories() throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        CategoryDAO category = null;
        List<CategoryDAO> categoryList = new ArrayList<CategoryDAO>();

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
                    category = new CategoryDAO();
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

        /**
         * Retrieve all Compositions from the Persistence mechanism
         @return A list of Compositions or an empty list when no Compositions exist
         @exception  Exception
         */
        public List<CompositionDAO> GetAllCompositions() throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            CompositionDAO composition = null;
            List<CompositionDAO> compositionList = new ArrayList<CompositionDAO>();

            try {
                db = this.getWritableDatabase();

                cursor = db.query("Composition",
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
                        composition = new CompositionDAO();
                        composition.setId(_id);
                        composition.setName(_name);
                        composition.setLongDescription(_longDescription);
                        composition.setActive(_isActive);

                        compositionList.add(composition);

                    } while (cursor.moveToNext());
                }

                return compositionList;

            } catch (Exception ex) {
                throw new Exception(ex);
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }

    /**
     * Retrieve a Category from the Persistence mechanism
    @return A Category or NULL object when the category does not exist
    @exception  Exception
    */
    public CategoryDAO GetCategoryById(int id) throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            CategoryDAO category = null;
            List<CategoryDAO> categoryList = new ArrayList<CategoryDAO>();

            try {
                db = this.getWritableDatabase();

                cursor = db.query("Category",
                        new String[]{"_id", "Name", "LongDescription", "IsActive"},
                        "_id = ?",
                        new String[]{Integer.toString(id)},
                        null, null, null);

                if (cursor.moveToFirst()) {
                        // output the first row
                        int _id = Integer.parseInt(cursor.getString(0));
                        String _name = cursor.getString(1);
                        String _longDescription = cursor.getString(2);
                        boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                        // TODO: Use a Mapper?
                        category = new CategoryDAO();
                        category.setId(_id);
                        category.setName(_name);
                        category.setLongDescription(_longDescription);
                        category.setActive(_isActive);
                }

                return category;

            } catch (Exception ex) {
                throw ex;
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }

        /**
         * Retrieve a Composition from the Persistence mechanism
         @return A Composition or NULL object when the composition does not exist
         @exception  Exception
         */
        public CompositionDAO GetCompositionById(int id) throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            CompositionDAO composition = null;
            List<CompositionDAO> compositionList = new ArrayList<CompositionDAO>();

            try {
                db = this.getWritableDatabase();

                cursor = db.query("Composition",
                        new String[]{"_id", "Name", "LongDescription", "IsActive"},
                        "_id = ?",
                        new String[]{Integer.toString(id)},
                        null, null, null);

                if (cursor.moveToFirst()) {
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    composition = new CompositionDAO();
                    composition.setId(_id);
                    composition.setName(_name);
                    composition.setLongDescription(_longDescription);
                    composition.setActive(_isActive);
                }

                return composition;

            } catch (Exception ex) {
                throw ex;
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }


        public List<ImageDAO> GetImagesForCategory(int categoryId) throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            List<ImageDAO> daoList = new ArrayList<ImageDAO>();

            try {
                db = getReadableDatabase();

                String query = "select i._id, i.Name, i.LongDescription, i.Location, i.ImageResourceId, i.IsActive " +
                        "from Category c\n " +
                        "inner join CategoryImage ci on ci.CategoryId = c._id\n" +
                        "inner join Image i on i._id = ci.ImageId\n" +
                        "where c._id = "+ Integer.toString(categoryId);

                cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {

                    do {

                        // output the first row
                        int _id = Integer.parseInt(cursor.getString(0));
                        String _name = cursor.getString(1);
                        String _longDescription = cursor.getString(2);
                        String _location = cursor.getString(3);
                        int _imageResourceId = Integer.parseInt(cursor.getString(4));
                        boolean _isActive = Boolean.parseBoolean(cursor.getString(5));

                        // TODO: Use a Mapper?
                        ImageDAO dao = new ImageDAO();
                        dao.setId(_id);
                        dao.setName(_name);
                        dao.setLongDescription(_longDescription);
                        dao.setLocation(_location);
                        dao.setImageResourceId(_imageResourceId);
                        dao.setActive(_isActive);

                        daoList.add(dao);

                    }while(cursor.moveToNext());
                }

                return daoList;

            } catch (Exception ex) {
                //display.setText(String.format("Error: %s", e.getMessage()));
                throw new Exception(ex);
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }

        public List<ImageDAO> GetImagesForComposition(int compositionId) throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            List<ImageDAO> daoList = new ArrayList<ImageDAO>();

            try {
                db = getReadableDatabase();

                String query = "select i._id, i.Name, i.LongDescription, i.Location, i.ImageResourceId, i.IsActive " +
                        "from Composition c\n " +
                        "inner join CompositionImage ci on ci.CompositionId = c._id\n" +
                        "inner join Image i on i._id = ci.ImageId\n" +
                        "where c._id = "+ Integer.toString(compositionId);

                cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {

                    do {

                        // output the first row
                        int _id = Integer.parseInt(cursor.getString(0));
                        String _name = cursor.getString(1);
                        String _longDescription = cursor.getString(2);
                        String _location = cursor.getString(3);
                        int _imageResourceId = Integer.parseInt(cursor.getString(4));
                        boolean _isActive = Boolean.parseBoolean(cursor.getString(5));

                        // TODO: Use a Mapper?
                        ImageDAO dao = new ImageDAO();
                        dao.setId(_id);
                        dao.setName(_name);
                        dao.setLongDescription(_longDescription);
                        dao.setLocation(_location);
                        dao.setImageResourceId(_imageResourceId);
                        dao.setActive(_isActive);

                        daoList.add(dao);

                    }while(cursor.moveToNext());
                }

                return daoList;

            } catch (Exception ex) {
                //display.setText(String.format("Error: %s", e.getMessage()));
                throw new Exception(ex);
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }

        public CategoryDAO GetCategoryByName(String categoryName) throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            CategoryDAO dao = null;

            try {
                db = this.getWritableDatabase();

                cursor = db.query("Category",
                        new String[]{"_id", "Name", "LongDescription", "IsActive"},
                        "Name = ?",
                        new String[]{categoryName},
                        null, null, null);

                if (cursor.moveToFirst()) {
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    dao = new CategoryDAO();
                    dao.setId(_id);
                    dao.setName(_name);
                    dao.setLongDescription(_longDescription);
                    dao.setActive(_isActive);
                }

                return dao;

            } catch (Exception ex) {
                //display.setText(String.format("Error: %s", e.getMessage()));
                throw new Exception(ex);
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }

        public CompositionDAO GetCompositionByName(String compositionName) throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            CompositionDAO dao = null;

            try {
                db = this.getWritableDatabase();

                cursor = db.query("Composition",
                        new String[]{"_id", "Name", "LongDescription", "IsActive"},
                        "Name = ?",
                        new String[]{compositionName},
                        null, null, null);

                if (cursor.moveToFirst()) {
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    dao = new CompositionDAO();
                    dao.setId(_id);
                    dao.setName(_name);
                    dao.setLongDescription(_longDescription);
                    dao.setActive(_isActive);
                }

                return dao;

            } catch (Exception ex) {
                //display.setText(String.format("Error: %s", e.getMessage()));
                throw new Exception(ex);
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }

        /**
         * Retrieve a random Category from the Persistence mechanism
         @return A Category or NULL object if an error occurs
         @exception  Exception
         */
        public CategoryDAO RandomCategory() throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            CategoryDAO category = null;

            try {

                db = this.getReadableDatabase();
                cursor = db.query("Category", new String[]{"_id", "Name", "LongDescription", "IsActive"},
                        null, null, null, null, "RANDOM() LIMIT 1");

                if (cursor.moveToFirst()) {
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    category = new CategoryDAO();
                    category.setId(_id);
                    category.setName(_name);
                    category.setLongDescription(_longDescription);
                    category.setActive(_isActive);
                }

                return category;

            } catch (Exception ex) {
                throw ex;
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }

        /**
         * Retrieve a random Composition from the Persistence mechanism
         @return A Composition or NULL object if an error occurs
         @exception  Exception
         */
        public CompositionDAO RandomComposition() throws Exception {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            CompositionDAO composition = null;

            try {

                db = this.getReadableDatabase();
                cursor = db.query("Composition", new String[]{"_id", "Name", "LongDescription", "IsActive"},
                        null, null, null, null, "RANDOM() LIMIT 1");

                if (cursor.moveToFirst()) {
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    composition = new CompositionDAO();
                    composition.setId(_id);
                    composition.setName(_name);
                    composition.setLongDescription(_longDescription);
                    composition.setActive(_isActive);
                }

                return composition;

            } catch (Exception ex) {
                throw ex;
            } finally {
                if (cursor != null)
                    cursor.close();

                if (db != null)
                    db.close();
            }
        }


    }
