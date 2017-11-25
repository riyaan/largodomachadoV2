package fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.photoshotlist.R;

import activity.CategoryAllFragment;
import activity.ChallengeMeAllFragment;

/**
 * Created by PhpDev on 2017/11/23.
 */

public class CategoryStrategy extends FragmentStrategy {
    public void Load(FragmentManager fragmentManager)
    {
        Fragment fragment = new CategoryAllFragment();
        if (fragment != null) {
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
}
