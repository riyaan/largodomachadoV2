package activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.photoshotlist.R;
import com.photoshotlist.bll.ImageDO;
import com.photoshotlist.bll.PSLBusinessHelper;
import com.photoshotlist.exception.PSLException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
                }
            }
        });

        return categoryAllRecycler;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}