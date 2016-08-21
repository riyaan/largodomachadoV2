package com.photoshotlist.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.photoshotlist.common.Helper;
import com.photoshotlist.common.Logger;


public class PSLDatabaseHelper extends SQLiteOpenHelper {

    private static PSLDatabaseHelper instance;

    private static final String DB_NAME = "PhotoShotList";
    private static final int DB_VERSION = 2; // TODO: Read from configuration file
    private static final int DB_OLD_VERSION = 1;

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE Category (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name TEXT, LongDescription TEXT, IsActive INTEGER);";
    private static final String CREATE_TABLE_RULE = "CREATE TABLE Rule (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name TEXT, LongDescription TEXT, IsActive INTEGER);";
    private static final String CREATE_TABLE_SHOTLIST = "CREATE TABLE Shotlist (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Name TEXT, LongDescription TEXT, CreatedDate TEXT, IsActive INTEGER);";

    private PSLDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static PSLDatabaseHelper getInstance(Context context)
    {
        if(context == null){
            // Use the test db.
        }
        else {

            if (instance == null) {
                instance = new PSLDatabaseHelper(context);
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, DB_OLD_VERSION, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, DB_OLD_VERSION, DB_VERSION);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){

            db.execSQL(CREATE_TABLE_CATEGORY);

            /*
            TODO: Read these values from configuration file.
            TODO: Allow these categories to be added by the application
            */
            InsertCategory(db, "ABC (Always Be Curious)", "", 1);
            InsertCategory(db, "Abstract", "", 1);
            InsertCategory(db, "Aerial", "", 1);
            InsertCategory(db, "Agriculture", "", 1);
            InsertCategory(db, "Architecture", "", 1);
            InsertCategory(db, "Art", "", 1);
            InsertCategory(db, "Closeup Shot", "", 1);
            InsertCategory(db, "Colour", "", 1);
            InsertCategory(db, "Contrast", "", 1);
            InsertCategory(db, "Culture & Customs", "", 1);
            InsertCategory(db, "Establishing Shot", "", 1);
            InsertCategory(db, "Everyday Life", "", 1);
            InsertCategory(db, "Fashion and Style", "", 1);
            InsertCategory(db, "Flags", "", 1);
            InsertCategory(db, "Flora", "", 1);
            InsertCategory(db, "Food / Gastronomy", "", 1);
            InsertCategory(db, "Golden Hour", "", 1);
            InsertCategory(db, "History", "", 1);
            InsertCategory(db, "Holidays", "", 1);
            InsertCategory(db, "Humour", "", 1);
            InsertCategory(db, "Icons", "", 1);
            InsertCategory(db, "Industry", "", 1);
            InsertCategory(db, "Interiors", "", 1);
            InsertCategory(db, "Silhouette", "", 1);
            InsertCategory(db, "Landmarks", "", 1);
            InsertCategory(db, "Landscape", "", 1);
            InsertCategory(db, "Language", "", 1);
            InsertCategory(db, "Man Made Wonders", "", 1);
            InsertCategory(db, "Markets / Vendors", "", 1);
            InsertCategory(db, "Medium Shot", "", 1);
            InsertCategory(db, "Motion", "", 1);
            InsertCategory(db, "Multiple Exposure", "", 1);
            InsertCategory(db, "Music", "", 1);
            InsertCategory(db, "Natural Wonders", "", 1);
            InsertCategory(db, "Neighbourhoods", "", 1);
            InsertCategory(db, "Night Scenes", "", 1);
            InsertCategory(db, "Panoramic View", "", 1);
            InsertCategory(db, "People", "", 1);
            InsertCategory(db, "Recreation", "", 1);
            InsertCategory(db, "Sacred Sites", "", 1);
            InsertCategory(db, "Scene Details", "", 1);
            InsertCategory(db, "Seasons", "", 1);
            InsertCategory(db, "Self Portrait", "", 1);
            InsertCategory(db, "Shopping", "", 1);
            InsertCategory(db, "Signs", "", 1);
            InsertCategory(db, "Skyline", "", 1);
            InsertCategory(db, "Souvenirs & Crafts", "", 1);
            InsertCategory(db, "Sports", "", 1);
            InsertCategory(db, "Story Telling", "", 1);
            InsertCategory(db, "Street Scenes", "", 1);
            InsertCategory(db, "Theme", "", 1);
            InsertCategory(db, "Time-lapse", "", 1);
            InsertCategory(db, "Traditions", "", 1);
            InsertCategory(db, "Traffic Trails", "", 1);
            InsertCategory(db, "Transportation", "", 1);
            InsertCategory(db, "Underbelly", "", 1);
            InsertCategory(db, "Wide Shot", "", 1);

            db.execSQL(CREATE_TABLE_RULE);

            /*
            TODO: Read these values from configuration file.
            TODO: Allow these Rules to be added by the application
            */

            InsertRule(db, "Rule of Thirds", "", 1);
            InsertRule(db, "The Golden Ratio", "", 1);
            InsertRule(db, "Golden Triangle & Spirals", "", 1);
            InsertRule(db, "Rule of Odds", "", 1);
            InsertRule(db, "Leaving Space", "", 1);
            InsertRule(db, "Fill the Frame", "", 1);
            InsertRule(db, "Simplification", "", 1);
            InsertRule(db, "Balance", "", 1);
            InsertRule(db, "Leading Lines", "", 1);
            InsertRule(db, "Patterns", "", 1);
            InsertRule(db, "Colour", "", 1);
            InsertRule(db, "Texture", "", 1);
            InsertRule(db, "Symmetry", "", 1);
            InsertRule(db, "Viewpoint", "", 1);
            InsertRule(db, "Background", "", 1);
            InsertRule(db, "Depth", "", 1);
            InsertRule(db, "Framing", "", 1);
            InsertRule(db, "Orientation", "", 1);
        }

        if(oldVersion < 2){
            // code to add the new DB structure
            db.execSQL(CREATE_TABLE_SHOTLIST);
        }
    }

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

    /*
    Returns -1 if an error occurred
     */
    public int InsertShotList(String name, String longDescription) throws Exception {
        Logger.Debug(this.getClass().getName(), "Entering InsertShotList");

        SQLiteDatabase db = null;
        Cursor cursor = null;
        ShotListDAO dao = null;

        try {

            if (name == null || name.toString().isEmpty())
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
}
