package com.photoshotlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.photoshotlist.dal.PSLDatabaseHelper;
import com.photoshotlist.dal.ShotListDAO;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "YANIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this);
//        try {
//            ShotListDAO dao = dbHelper.GetShotListByName("test");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void onClickAddCategory(View view)
    {
//        EditText categoryName = null;
        TextView display = null;
        Cursor cursor = null;
        SQLiteDatabase db = null;

        try
        {
            /*categoryName = (EditText) findViewById(R.id.editTextCategoryName);
            String text = categoryName.getText().toString();*/

            display = (TextView)findViewById(R.id.textViewDisplayCategory);
            //display.setText(String.format("Thanks for adding category: %s", text));

            PSLDatabaseHelper dbHelper = PSLDatabaseHelper.getInstance(this);
            db = dbHelper.getReadableDatabase();
            cursor = db.query("Category", new String[]{"Name"}, null, null, null, null, "RANDOM() LIMIT 1");

            if(cursor.moveToFirst()){
                // output the first row
                String dbCategoryName = cursor.getString(0);
                display.setText(String.format("Random category: %s", dbCategoryName));
            }
        }
        catch(Exception e){
            display.setText(String.format("Error: %s", e.getMessage()));
        }
        finally {
            if(cursor != null)
                cursor.close();

            if(db != null)
                db.close();
        }
    }

    public void onClickNewShotlist(View view)
    {
        // Test
//        Intent intent = new Intent(this, MainShotListActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, "Oh Hi...");
//        startActivity(intent);

        // Invoke the 'Create Shot List' UI
        Intent intent = new Intent(this, NewShotListActivity.class);
        // intent.putExtra(EXTRA_MESSAGE, "Oh Hi...");
        startActivity(intent);
    }


}
