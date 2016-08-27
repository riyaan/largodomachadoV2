package com.photoshotlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.photoshotlist.dal.PSLDatabaseHelper;
import com.photoshotlist.dal.ShotListDAO;

import activity.Drawer;

public class MainActivity extends AppCompatActivity implements Drawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private Drawer drawerFragment;
    public final static String EXTRA_MESSAGE = "YANIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (Drawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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


    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}
