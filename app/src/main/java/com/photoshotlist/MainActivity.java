package com.photoshotlist;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

// TODO: Why is this model being referenced in the MainActivity?
import com.photoshotlist.domainmodels.entities.ChallengeMe;
import com.photoshotlist.infrastructure.repositories.CategoryRepository;
import com.photoshotlist.infrastructure.repositories.CompositionRepository;
import com.photoshotlist.infrastructure.repositories.ImageRepository;
import com.photoshotlist.interactors.ChallengeMeInteractor;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import activity.CategoryAllFragment;
import activity.CategoryDetailFragment;
import activity.CategoryFragment;
import activity.ChallengeMeAllFragment;
import activity.ChallengeMeDetailFragment;
import activity.ChallengeMeFragment;
import activity.CompositionAllFragment;
import activity.CompositionDetailFragment;
import activity.CompositionFragment;
import activity.Drawer;
import activity.HomeFragment;
import activity.ShotListFragment;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements Drawer.FragmentDrawerListener,
        CategoryDetailFragment.OnFragmentInteractionListener,
        CompositionDetailFragment.OnFragmentInteractionListener,
        ChallengeMeDetailFragment.OnFragmentInteractionListener{

    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private Drawer drawerFragment;
    public final static String EXTRA_MESSAGE = "YANIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Crashlytics());

        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(getApplication());

        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (Drawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
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

    private void displayView(int position) {
        // TODO: Store the Titles in resource file
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new ShotListFragment();
                title = getString(R.string.title_shotlists);
                break;
            case 2:
                fragment = new CategoryFragment();
                title = getString(R.string.title_categories);
                break;
            case 3:
                fragment = new CompositionFragment();
                title = getString(R.string.title_compositions);
                break;
            case 4:
                fragment = new ChallengeMeFragment();
                title = getString(R.string.title_challengeme);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);

            // TODO: Start - This gets the back button to work
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            // TODO: End - This gets the back button to work

            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


    public void onClickAddCategory(View view)
    {
        TextView display = null;
        display = (TextView)findViewById(R.id.textViewDisplayCategory);

        ChallengeMeInteractor cmi = new ChallengeMeInteractor(new CompositionRepository(this),
                new CategoryRepository(this), new ImageRepository(this));
        ChallengeMe cm = cmi.GetRandom();

        if(cm != null)
            //display.setText(category.getName());
            display.setText(String.format("Category: %s %n Composition: %s",
                    cm.getCategory().getName(), cm.getComposition().getName()));

        else {

            Resources res = getResources();
            String text = res.getString(R.string.could_not_retrieve_challengeme_item);
            display.setText(text);
        }
    }

    public void onClickAddComposition(View view)
    {
        TextView display = null;
        display = (TextView)findViewById(R.id.textViewDisplayComposition);

//        ChallengeMeInteractor cmi = new ChallengeMeInteractor(new CompositionRepository(this),
//                new CategoryRepository(this), new ImageRepository(this));
//        Composition composition = cmi.GetRandomComposition();
//
//        if(composition != null)
//            display.setText(composition.getName());
//        else
//            display.setText("Could not retrieve a random Composition.");
    }

    public void onClickViewAllCategory(View view)
    {
        Fragment fragment = new CategoryAllFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);

            // TODO: Start - This gets the back button to work
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            // TODO: End - This gets the back button to work

            fragmentTransaction.commit();

            // set the toolbar title
            // getSupportActionBar().setTitle("All Categories");
        }
    }

    public void onClickViewAllCompositions(View view)
    {
        Fragment fragment = new CompositionAllFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);

            // TODO: Start - This gets the back button to work
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            // TODO: End - This gets the back button to work

            fragmentTransaction.commit();

            // set the toolbar title
            // getSupportActionBar().setTitle("All Categories");
        }
    }

    public void onClickChallengeMe(View view)
    {
        Fragment fragment = new ChallengeMeAllFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);

            // TODO: Start - This gets the back button to work
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            // TODO: End - This gets the back button to work

            fragmentTransaction.commit();

            // set the toolbar title
            // getSupportActionBar().setTitle("All Categories");
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }


    public void onClickNewShotlist(View view)
    {
        // Invoke the 'Create Shot List' UI
        Intent intent = new Intent(this, NewShotListActivity.class);
        // intent.putExtra(EXTRA_MESSAGE, "Oh Hi...");
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    // Miscellaneous method to get the latest backup
    public void onClickBackupDB(View view)
    {
        TextView display = null;
        display = (TextView)findViewById(R.id.textViewDisplayCategory);

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + getPackageName() + "/databases/PhotoShotList";
                String backupDBPath = "backup.db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();

                    Resources res = getResources();
                    String text = res.getString(R.string.done_copying);
                    display.setText(text);
                }
                else {
                    Resources res = getResources();
                    String text = res.getString(R.string.invalid_db_path, currentDB);
                    display.setText(text);
                }
            }
            else {
                Resources res = getResources();
                String text = res.getString(R.string.cannot_write_to_sdcard);
                display.setText(text);
            }
        } catch (Exception e) {
            display.setText(e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}
