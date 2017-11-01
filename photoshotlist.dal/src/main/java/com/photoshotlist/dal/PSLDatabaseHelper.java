package com.photoshotlist.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.photoshotlist.common.Helper;
import com.photoshotlist.common.Logger;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

// TODO: Cleanup this class. But a prerequisite is to create Unit Tests first.

//public class PSLDatabaseHelper extends SQLiteOpenHelper {
    public class PSLDatabaseHelper extends SQLiteAssetHelper {

    private static PSLDatabaseHelper instance;

    private static final String DB_NAME = "PhotoShotList.db";

    //The Android's default system path of your application database.
    //private static final String DB_PATH = "/data/data/com.photoshotlist/databases/";
    //data/data/com.photoshotlist/databases/PhotoShotList

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    private static final int DB_VERSION = 14; // TODO: Read from configuration file
//    private static final int DB_OLD_VERSION = 2;

//    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE Category (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name TEXT, LongDescription TEXT, IsActive INTEGER);";
//    private static final String CREATE_TABLE_RULE = "CREATE TABLE Rule (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name TEXT, LongDescription TEXT, IsActive INTEGER);";
//    private static final String CREATE_TABLE_SHOTLIST = "CREATE TABLE Shotlist (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name TEXT, LongDescription TEXT, CreatedDate TEXT, IsActive INTEGER);";
//    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE Image (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, LongDescription TEXT, Location TEXT, ImageResourceId INTEGER, IsActive INTEGER);";
//    private static final String CREATE_TABLE_CATEGORY_IMAGE = "CREATE TABLE CategoryImage (CategoryId INTEGER NOT NULL, ImageId INTEGER NOT NULL);";

    public PSLDatabaseHelper(Context context) throws Exception {
        super(context, DB_NAME, null, DB_VERSION);
        myContext = context;
        setForcedUpgrade();

//        try {
//            createDataBase();
//            openDataBase();
//
//        }catch(SQLException sqle){
//
//            throw sqle;
//        }
    }
//
    public static PSLDatabaseHelper getInstance(Context context)
    {
        if(context == null){
            // Use the test db.
        }
        else {

            if (instance == null) {
                try {
                    instance = new PSLDatabaseHelper(context);
                }catch (Exception ex){
                }
            }
        }
        return instance;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
//    public void createDataBase() throws IOException{
//
//        boolean dbExist = checkDataBase();
//
//        if(dbExist){
//            //do nothing - database already exist
//        }else{
//
//            //By calling this method and empty database will be created into the default system path
//            //of your application so we are gonna be able to overwrite that database with our database.
//            this.getReadableDatabase();
//
//            try {
//
//                copyDataBase();
//
//            } catch (IOException e) {
//
//                throw new Error("Error copying database");
//
//            }
//        }
//
//    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
//    private boolean checkDataBase(){
//
//        SQLiteDatabase checkDB = null;
//
//        try{
//            String myPath = DB_PATH + DB_NAME;
//            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//
//        }catch(SQLiteException e){
//
//            //database does't exist yet.
//
//        }
//
//        if(checkDB != null){
//
//            checkDB.close();
//
//        }
//
//        return checkDB != null ? true : false;
//    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
//    private void copyDataBase() throws IOException{
//
//        //Open your local db as the input stream
//        InputStream myInput = myContext.getAssets().open(DB_NAME);
//
//        // Path to the just created empty db
//        String outFileName = DB_PATH + DB_NAME;
//
//        //Open the empty db as the output stream
//        OutputStream myOutput = new FileOutputStream(outFileName);
//
//        //transfer bytes from the inputfile to the outputfile
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myInput.read(buffer))>0){
//            myOutput.write(buffer, 0, length);
//        }
//
//        //Close the streams
//        myOutput.flush();
//        myOutput.close();
//        myInput.close();
//
//    }

//    public void openDataBase() throws SQLException {
//
//        //Open the database
//        String myPath = DB_PATH + DB_NAME;
//        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//        Logger.Debug("Test", myDataBase.getPath());
//    }

//    @Override
//    public synchronized void close() {
//
//        if(myDataBase != null)
//            myDataBase.close();
//
//        super.close();
//
//    }


//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // updateMyDatabase(db, DB_OLD_VERSION, DB_VERSION);
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // updateMyDatabase(db, DB_OLD_VERSION, DB_VERSION);
    }

//    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
//        if(oldVersion < 1){
//
//            db.execSQL(CREATE_TABLE_CATEGORY);
//
//            /*
//            TODO: Read these values from configuration file.
//            TODO: Allow these categories to be added by the application
//            */
//            InsertCategory(db, "ABC (Always Be Curious)", "", 1);
//            InsertCategory(db, "Abstract", "", 1);
//            InsertCategory(db, "Aerial", "", 1);
//            InsertCategory(db, "Agriculture", "", 1);
//            InsertCategory(db, "Architecture", "", 1);
//            InsertCategory(db, "Art", "", 1);
//            InsertCategory(db, "Closeup Shot", "", 1);
//            InsertCategory(db, "Colour", "", 1);
//            InsertCategory(db, "Contrast", "", 1);
//            InsertCategory(db, "Culture & Customs", "", 1);
//            InsertCategory(db, "Establishing Shot", "", 1);
//            InsertCategory(db, "Everyday Life", "", 1);
//            InsertCategory(db, "Fashion and Style", "", 1);
//            InsertCategory(db, "Flags", "", 1);
//            InsertCategory(db, "Flora", "", 1);
//            InsertCategory(db, "Food / Gastronomy", "", 1);
//            InsertCategory(db, "Golden Hour", "", 1);
//            InsertCategory(db, "History", "", 1);
//            InsertCategory(db, "Holidays", "", 1);
//            InsertCategory(db, "Humour", "", 1);
//            InsertCategory(db, "Icons", "", 1);
//            InsertCategory(db, "Industry", "", 1);
//            InsertCategory(db, "Interiors", "", 1);
//            InsertCategory(db, "Silhouette", "", 1);
//            InsertCategory(db, "Landmarks", "", 1);
//            InsertCategory(db, "Landscape", "", 1);
//            InsertCategory(db, "Language", "", 1);
//            InsertCategory(db, "Man Made Wonders", "", 1);
//            InsertCategory(db, "Markets / Vendors", "", 1);
//            InsertCategory(db, "Medium Shot", "", 1);
//            InsertCategory(db, "Motion", "", 1);
//            InsertCategory(db, "Multiple Exposure", "", 1);
//            InsertCategory(db, "Music", "", 1);
//            InsertCategory(db, "Natural Wonders", "", 1);
//            InsertCategory(db, "Neighbourhoods", "", 1);
//            InsertCategory(db, "Night Scenes", "", 1);
//            InsertCategory(db, "Panoramic View", "", 1);
//            InsertCategory(db, "People", "", 1);
//            InsertCategory(db, "Recreation", "", 1);
//            InsertCategory(db, "Sacred Sites", "", 1);
//            InsertCategory(db, "Scene Details", "", 1);
//            InsertCategory(db, "Seasons", "", 1);
//            InsertCategory(db, "Self Portrait", "", 1);
//            InsertCategory(db, "Shopping", "", 1);
//            InsertCategory(db, "Signs", "", 1);
//            InsertCategory(db, "Skyline", "", 1);
//            InsertCategory(db, "Souvenirs & Crafts", "", 1);
//            InsertCategory(db, "Sports", "", 1);
//            InsertCategory(db, "Story Telling", "", 1);
//            InsertCategory(db, "Street Scenes", "", 1);
//            InsertCategory(db, "Theme", "", 1);
//            InsertCategory(db, "Time-lapse", "", 1);
//            InsertCategory(db, "Traditions", "", 1);
//            InsertCategory(db, "Traffic Trails", "", 1);
//            InsertCategory(db, "Transportation", "", 1);
//            InsertCategory(db, "Underbelly", "", 1);
//            InsertCategory(db, "Wide Shot", "", 1);
//
//            db.execSQL(CREATE_TABLE_RULE);
//
//            /*
//            TODO: Read these values from configuration file.
//            TODO: Allow these Rules to be added by the application
//            */
//
//            InsertRule(db, "Rule of Thirds", "", 1);
//            InsertRule(db, "The Golden Ratio", "", 1);
//            InsertRule(db, "Golden Triangle & Spirals", "", 1);
//            InsertRule(db, "Rule of Odds", "", 1);
//            InsertRule(db, "Leaving Space", "", 1);
//            InsertRule(db, "Fill the Frame", "", 1);
//            InsertRule(db, "Simplification", "", 1);
//            InsertRule(db, "Balance", "", 1);
//            InsertRule(db, "Leading Lines", "", 1);
//            InsertRule(db, "Patterns", "", 1);
//            InsertRule(db, "Colour", "", 1);
//            InsertRule(db, "Texture", "", 1);
//            InsertRule(db, "Symmetry", "", 1);
//            InsertRule(db, "Viewpoint", "", 1);
//            InsertRule(db, "Background", "", 1);
//            InsertRule(db, "Depth", "", 1);
//            InsertRule(db, "Framing", "", 1);
//            InsertRule(db, "Orientation", "", 1);
//        }
//
//        if(oldVersion < 2){
//            // code to add the new DB structure
//            db.execSQL(CREATE_TABLE_SHOTLIST);
//        }
//
//        if(oldVersion < 3) {
//            // code to add the new DB Structure
//            db.execSQL(CREATE_TABLE_IMAGE);
//            db.execSQL(CREATE_TABLE_CATEGORY_IMAGE);
//
//            InsertImage(db, "Chains", "XYZ", "R.drawable.category_abstract", 0, 1);
//            InsertCategoryImage(db, 2, 1);
//        }
//    }

    private static void InsertCategory(SQLiteDatabase db, String name, String longDescription, int isActive){
        ContentValues categoryValues = new ContentValues();
        categoryValues.put("Name", name);
        categoryValues.put("LongDescription", longDescription);
        categoryValues.put("IsActive", isActive);

        db.insert("Category", null, categoryValues);
    }

    private static void InsertRule(SQLiteDatabase db, String name, String longDescription, int isActive){
        ContentValues ruleValues = new ContentValues();
        ruleValues.put("Name", name);
        ruleValues.put("LongDescription", longDescription);
        ruleValues.put("IsActive", isActive);

        db.insert("Rule", null, ruleValues);
    }

    private static void InsertShotList(SQLiteDatabase db, String name, String longDescription, int isActive){
        ContentValues ruleValues = new ContentValues();
        ruleValues.put("Name", name);
        ruleValues.put("LongDescription", longDescription);

        String textDate = Helper.GetFormattedDate();
        ruleValues.put("CreatedDate", textDate);

        ruleValues.put("IsActive", isActive);

        db.insert("Rule", null, ruleValues);
    }

    private static void InsertImage(SQLiteDatabase db, String name, String longDescription, String location, int imageResourceId, int isActive){
        ContentValues ruleValues = new ContentValues();
        ruleValues.put("Name", name);
        ruleValues.put("LongDescription", longDescription);
        ruleValues.put("Location", location);
        ruleValues.put("ImageResourceId", imageResourceId);
        ruleValues.put("IsActive", isActive);

        db.insert("Image", null, ruleValues);
    }

    private static void InsertCategoryImage(SQLiteDatabase db, int categoryId, int imageId){
        ContentValues ruleValues = new ContentValues();
        ruleValues.put("CategoryId", categoryId);
        ruleValues.put("ImageId", imageId);

        db.insert("CategoryImage", null, ruleValues);
    }

    /*
    Returns -1 if an error occurred
     */
    public int InsertShotList(String name, String longDescription) throws Exception {
        Logger.Debug(this.getClass().getName(), "Entering InsertShotList");

        SQLiteDatabase db = null;
        Cursor cursor = null;
        ShotListDAO dao = null;

        try {

            if (name == null || name.isEmpty())
                throw new Exception("Null or Empty values for ShotList Name field.");

            db = this.getWritableDatabase();

            ContentValues ruleValues = new ContentValues();
            ruleValues.put("Name", name);
            ruleValues.put("LongDescription", longDescription);

            String textDate = Helper.GetFormattedDate();

            ruleValues.put("CreatedDate", textDate);
            ruleValues.put("IsActive", 1);

            int shotListId = (int)db.insert("Shotlist", null, ruleValues);
            return shotListId;

        }catch (Exception ex){
            throw new Exception(ex);
        }finally {
            if (cursor != null)
                cursor.close();

            if (db != null)
                db.close();
        }
    }

    /*
    Return a NULL dao object if no matching records are found.
     */
    public ShotListDAO GetShotListByName(String name) throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ShotListDAO dao = null;

        try {
            db = this.getWritableDatabase();

            cursor = db.query("Shotlist",
                    new String[]{"_id", "Name", "LongDescription", "CreatedDate", "IsActive"},
                    "Name = ?",
                    new String[]{name},
                    null, null, null);

            if (cursor.moveToFirst()) {
                // output the first row
                int _id = Integer.parseInt(cursor.getString(0));
                String _name = cursor.getString(1);
                String _longDescription = cursor.getString(2);
                String _createdDate = cursor.getString(3);
                boolean _isActive = Boolean.parseBoolean(cursor.getString(4));

                // TODO: Use a Mapper?
                dao = new ShotListDAO();
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

    public ShotListDAO GetShotListById(int shotListId) throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ShotListDAO dao = null;

        try {
            db = this.getWritableDatabase();

            cursor = db.query("Shotlist",
                    new String[]{"_id", "Name", "LongDescription", "CreatedDate", "IsActive"},
                    "_id = ?",
                    new String[]{Integer.toString(shotListId)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                // output the first row
                int _id = Integer.parseInt(cursor.getString(0));
                String _name = cursor.getString(1);
                String _longDescription = cursor.getString(2);
                String _createdDate = cursor.getString(3);
                boolean _isActive = Boolean.parseBoolean(cursor.getString(4));

                // TODO: Use a Mapper?
                dao = new ShotListDAO();
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

    public List<ShotListDAO> GetAllCategories() throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ShotListDAO dao = null;
        List<ShotListDAO> daoList = new ArrayList<ShotListDAO>();

        try {
            db = this.getWritableDatabase();

            cursor = db.query("Category",
                    new String[]{"_id", "Name", "LongDescription", "IsActive"},
                    null, null, null, null, null);

            if (cursor.moveToFirst()) {

                do{
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    dao = new ShotListDAO();
                    dao.setId(_id);
                    dao.setName(_name);
                    dao.setLongDescription(_longDescription);
                    dao.setActive(_isActive);

                    daoList.add(dao);

                    //cursor.moveToNext();
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

    public ShotListDAO GetCategoryById(int categoryId) throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ShotListDAO dao = null;

        try {
            db = this.getWritableDatabase();

            cursor = db.query("Category",
                    new String[]{"_id", "Name", "LongDescription", "IsActive"},
                    "_id = ?",
                    new String[]{Integer.toString(categoryId)},
                    null, null, null);

            if (cursor.moveToFirst()) {

                do{
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    dao = new ShotListDAO();
                    dao.setId(_id);
                    dao.setName(_name);
                    dao.setLongDescription(_longDescription);
                    dao.setActive(_isActive);

                    //cursor.moveToNext();
                }while(cursor.moveToNext());
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

    public ShotListDAO GetCategoryByName(String categoryName) throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ShotListDAO dao = null;

        try {
            db = this.getWritableDatabase();

            cursor = db.query("Category",
                    new String[]{"_id", "Name", "LongDescription", "IsActive"},
                    "Name = ?",
                    new String[]{categoryName},
                    null, null, null);

            if (cursor.moveToFirst()) {

                do{
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(3));

                    // TODO: Use a Mapper?
                    dao = new ShotListDAO();
                    dao.setId(_id);
                    dao.setName(_name);
                    dao.setLongDescription(_longDescription);
                    dao.setActive(_isActive);

                    //cursor.moveToNext();
                }while(cursor.moveToNext());
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

    public List<ImageDAO> GetAllImagesForCategory(int categoryId) throws Exception {
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

    public ImageDAO GetPreviewImageForCategory(int categoryId) throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ImageDAO dao = null;

        try {
            db = getReadableDatabase();

            String query = "select i._id, i.Name, i.LongDescription, i.Location, i.ImageResourceId, i.IsActive " +
                    "from Category c\n " +
                    "inner join CategoryImage ci on ci.CategoryId = c._id\n" +
                    "inner join Image i on i._id = ci.ImageId\n" +
                    "where c._id = "+ Integer.toString(categoryId);

            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                    // output the first row
                    int _id = Integer.parseInt(cursor.getString(0));
                    String _name = cursor.getString(1);
                    String _longDescription = cursor.getString(2);
                    String _location = cursor.getString(3);
                    int _imageResourceId = Integer.parseInt(cursor.getString(4));
                    boolean _isActive = Boolean.parseBoolean(cursor.getString(5));

                    // TODO: Use a Mapper?
                    dao = new ImageDAO();
                    dao.setId(_id);
                    dao.setName(_name);
                    dao.setLongDescription(_longDescription);
                    dao.setLocation(_location);
                    dao.setImageResourceId(_imageResourceId);
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

    public List<ImageDAO> GetPreviewImagesForCategories() throws Exception {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        ShotListDAO dao = null;
        List<ShotListDAO> daoCategoryList = new ArrayList<ShotListDAO>();
        List<ImageDAO> daoList = new ArrayList<ImageDAO>();
        ImageDAO img = null;

        try {
            db = this.getWritableDatabase();

            daoCategoryList = GetAllCategories();

            for (ShotListDAO obj:daoCategoryList) {
                img = GetPreviewImageForCategory(obj.getId());

                // When no preview Image for this Category is found. Use the default image.
                if(img == null) {
                    img = new ImageDAO();
                    img.setName(obj.getName()); // Using the Category Name instead of the Image Name
                    img.setId(obj.getId());
                    img.setLongDescription(obj.getLongDescription());
                    img.setLocation("R.drawable.category_ina"); // TODO: Store this in a config
                    img.setImageResourceId(0);
                    img.setActive(true);
                }
                else{
                    // We only need to show the Preview aka 'First' image.
                    img.setName(obj.getName()); // Using the Category Name instead of the Image Name
                }

                daoList.add(img);
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
}
