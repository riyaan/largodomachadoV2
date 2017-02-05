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
import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.infrastructure.repositories.CompositionRepository;
import com.photoshotlist.infrastructure.repositories.ImageRepository;
import com.photoshotlist.interactors.CompositionInteractor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import adapter.CaptionedCompositionImagesAdapter;


public class CompositionAllFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView compositionAllRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_compositions_all, container, false);

        List<ImageResponseModel> imageResponseModels = new ArrayList<ImageResponseModel>();
        Context context = getActivity();

        CompositionInteractor compositionInteractor = new CompositionInteractor(
                new CompositionRepository(context), new ImageRepository(context));

        List<CompositionResponseModel> compositionResponseModels = compositionInteractor.GetAllCompositions();

        int[] images = new int[compositionResponseModels.size()];

        List<Integer> tempImages = new ArrayList<Integer>();
        final List<String> tempNames = new ArrayList<String>();
        final List<Integer> tempCompositionIds = new ArrayList<Integer>();

        for(CompositionResponseModel crm : compositionResponseModels){
            tempNames.add(crm.getName());

            String[] temp = crm.getImageResponseModels().get(0).getLocation().split(Pattern.quote("."));

            Resources resources = getActivity().getResources();
            final int resourceId = resources.getIdentifier(temp[2], "drawable",
                    getActivity().getPackageName());

            tempImages.add(resourceId);
            tempCompositionIds.add(crm.getId());
        }

        for(int i=0; i<tempImages.size(); i++){
            images[i] = tempImages.get(i);
        }

        CaptionedCompositionImagesAdapter adapter = new CaptionedCompositionImagesAdapter(
                tempNames.toArray(new String[0]),images);

        compositionAllRecycler.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        compositionAllRecycler.setLayoutManager(glm);

        adapter.setListener(new CaptionedCompositionImagesAdapter.Listener(){
            public void onClick(int position){

                // Call the Composition Detail Fragment
                Fragment fragment = CompositionDetailFragment.newInstance(tempNames.get(position).toString());
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

        return compositionAllRecycler;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}