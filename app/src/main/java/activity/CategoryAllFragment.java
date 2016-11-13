package activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.RecyclerView;

import com.photoshotlist.DetailsActivity;
import com.photoshotlist.MainActivity;
import com.photoshotlist.R;
import com.photoshotlist.bll.ImageDO;
import com.photoshotlist.bll.PSLBusinessHelper;
import com.photoshotlist.bll.ShotListDO;
import com.photoshotlist.common.Logger;
import com.photoshotlist.dal.ImageDAO;
import com.photoshotlist.exception.PSLException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import adapter.CaptionedAllCategoryImageAdapter;
import adapter.CaptionedImagesAdapter;


public class CategoryAllFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView categoryAllRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_categories_all, container, false);

        List<ImageDO> imageDOList = null;
        PSLBusinessHelper businessHelper = PSLBusinessHelper.getInstance(getActivity());
        try {
            // get all the preview images for all categories
            imageDOList = businessHelper.GetPreviewImagesForCategories();
        } catch (PSLException e) {
            e.printStackTrace();
        }

        int[] images = new int[imageDOList.size()];

        List<Integer> tempImages = new ArrayList<Integer>();
        final List<String> tempNames = new ArrayList<String>();
        final List<Integer> tempCategoryIds = new ArrayList<Integer>();

        // TODO: enable back button on Category all fragment
        // TODO: Display the correct category name instead of the image name
        // TODO: Implement on click of item in card view
        // TODO: Improve performance of image load in all categories card view

        // R.drawable.category_ina //
        for (ImageDO obj:imageDOList) {
            String[] temp = obj.getLocation().split(Pattern.quote("."));

            Resources resources = getActivity().getResources();
            final int resourceId = resources.getIdentifier(temp[2], "drawable",
                    getActivity().getPackageName());

            tempNames.add(obj.getName());
            tempImages.add(resourceId);

            // get the category id of the to which the image belongs
            tempCategoryIds.add(obj.getId());
        }

        // CAnnot use the List<int> in the ImageAdapter
        for(int i=0; i<tempImages.size(); i++){
            images[i] = tempImages.get(i);
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(
                tempNames.toArray(new String[0]),images);

        categoryAllRecycler.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        categoryAllRecycler.setLayoutManager(glm);

        adapter.setListener(new CaptionedImagesAdapter.Listener(){
            public void onClick(int position){
                //Intent intent = new Intent(getActivity(), DetailsActivity.class);

                // pass in the name of the Category. This is unique
                //intent.putExtra(DetailsActivity.EXTRA_MESSAGE, tempNames.get(position));

                // Call the Category Detail Fragment
                Fragment fragment = CategoryDetailFragment.newInstance(tempNames.get(position).toString());
                if (fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);

                    // TODO: Start - This gets the back button to work
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    // TODO: End - This gets the back button to work

                    fragmentTransaction.commit();

                    // set the toolbar title
                    //getSupportActionBar().setTitle(title);
                }

                // intent.putExtra(DetailsActivity.EXTRA_MESSAGE, position);

                //getActivity().startActivity(intent);
            }
        });

        return categoryAllRecycler;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private List<String> GetAllCategories()
    {
        List<ShotListDO> list = new ArrayList<ShotListDO>();

        PSLBusinessHelper businessHelper = PSLBusinessHelper.getInstance(getActivity());
        Logger.Debug(this.getClass().getName(), "Before InsertShotList");
        try {
            list = businessHelper.GetAllCategories();
        } catch (PSLException e) {
            e.printStackTrace();
        }

        List<String> values = new ArrayList<String>();
        for (ShotListDO obj:list) {
            values.add(obj.getName());
        }

        return values;
    }
}