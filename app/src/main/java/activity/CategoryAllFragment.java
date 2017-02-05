package activity;

import android.content.Context;
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
import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.ImageRequestModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.domainservices.repositories.IImageRepository;
import com.photoshotlist.exception.PSLException;
import com.photoshotlist.infrastructure.repositories.CategoryRepository;
import com.photoshotlist.infrastructure.repositories.ImageRepository;
import com.photoshotlist.interactors.CategoryInteractor;
import com.photoshotlist.interactors.ImageInteractor;

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

        List<ImageResponseModel> imageResponseModels = new ArrayList<ImageResponseModel>();
        Context context = getActivity();

        CategoryInteractor categoryInteractor = new CategoryInteractor(
                new CategoryRepository(context), new ImageRepository(context));

        List<CategoryResponseModel> categoryResponseModels = categoryInteractor.GetAllCategories();

        int[] images = new int[categoryResponseModels.size()];

        List<Integer> tempImages = new ArrayList<Integer>();
        final List<String> tempNames = new ArrayList<String>();
        final List<Integer> tempCategoryIds = new ArrayList<Integer>();

        for(CategoryResponseModel crm : categoryResponseModels){
            tempNames.add(crm.getName());

            String[] temp = crm.getImageResponseModels().get(0).getLocation().split(Pattern.quote("."));

            Resources resources = getActivity().getResources();
            final int resourceId = resources.getIdentifier(temp[2], "drawable",
                    getActivity().getPackageName());

            tempImages.add(resourceId);
            tempCategoryIds.add(crm.getId());
        }

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