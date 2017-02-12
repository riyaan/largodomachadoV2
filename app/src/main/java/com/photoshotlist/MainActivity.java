package com.photoshotlist;

import android.content.Intent;
import android.net.Uri;
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

import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.infrastructure.repositories.CategoryRepository;
import com.photoshotlist.infrastructure.repositories.CompositionRepository;
import com.photoshotlist.infrastructure.repositories.ImageRepository;
import com.photoshotlist.interactors.ChallengeMeInteractor;

import activity.CategoryAllFragment;
import activity.CategoryDetailFragment;
import activity.CategoryFragment;
import activity.CompositionAllFragment;
import activity.CompositionFragment;
import activity.Drawer;
import activity.HomeFragment;
import activity.ShotListFragment;

public class MainActivity extends AppCompatActivity implements Drawer.FragmentDrawerListener,
CategoryDetailFragment.OnFragmentInteractionListener{

    private static String TAG = MainActivity.class.getSimpleName();
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
        Category category = cmi.GetRandomCategory();

        if(category != null)
            display.setText(category.getName());
        else
            display.setText("Could not retrieve a random Category.");
    }

    public void onClickAddComposition(View view)
    {
        TextView display = null;
        display = (TextView)findViewById(R.id.textViewDisplayComposition);

        ChallengeMeInteractor cmi = new ChallengeMeInteractor(new CompositionRepository(this),
                new CategoryRepository(this), new ImageRepository(this));
        Composition composition = cmi.GetRandomComposition();

        if(composition != null)
            display.setText(composition.getName());
        else
            display.setText("Could not retrieve a random Composition.");
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
}
