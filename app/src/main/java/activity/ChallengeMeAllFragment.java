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
import android.widget.Toast;

import com.photoshotlist.R;
import com.photoshotlist.boundaries.input.ChallengeMeResponseModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.infrastructure.repositories.CategoryRepository;
import com.photoshotlist.infrastructure.repositories.CompositionRepository;
import com.photoshotlist.infrastructure.repositories.ImageRepository;
import com.photoshotlist.interactors.ChallengeMeInteractor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import adapter.CaptionedChallengeMeImagesAdapter;


public class ChallengeMeAllFragment extends Fragment {

    private ChallengeMeResponseModel challengeMeResponseModel;
    private Bundle outState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView challengeMeAllRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_challengeme_all, container, false);

        List<ImageResponseModel> imageResponseModels = new ArrayList<ImageResponseModel>();
        Context context = getActivity();

        ChallengeMeInteractor challengeMeInteractor = new ChallengeMeInteractor(
                new CompositionRepository(context), new CategoryRepository(context),
                new ImageRepository(context));

        // TODO: Consider adding a Polymorphic Random method that accepts a number. This paramater
        // TODO: will determine how many Random items to return.
        challengeMeResponseModel = challengeMeInteractor.Random();

        int numberOfImages = challengeMeResponseModel.getCategoryResponseModel().
                getImageResponseModels().size() +
                challengeMeResponseModel.getCompositionResponseModel().
                        getImageResponseModels().size();

        int[] images = new int[numberOfImages];

        List<Integer> tempImages = new ArrayList<Integer>();
        final List<String> tempNames = new ArrayList<String>();

        // TODO: Fix this duplicate code
        for(ImageResponseModel irm : challengeMeResponseModel.getCategoryResponseModel().
                getImageResponseModels()){

            tempNames.add(challengeMeResponseModel.getCategoryResponseModel().getName());

            String[] temp = irm.getLocation().split(Pattern.quote("."));

            Resources resources = getActivity().getResources();
            final int resourceId = resources.getIdentifier(temp[2], "drawable",
                    getActivity().getPackageName());

            tempImages.add(resourceId);
        }

        // TODO: Fix this duplicate code

        for(ImageResponseModel irm : challengeMeResponseModel.getCompositionResponseModel().
                getImageResponseModels()){

            tempNames.add(challengeMeResponseModel.getCompositionResponseModel().getName());

            String[] temp = irm.getLocation().split(Pattern.quote("."));

            Resources resources = getActivity().getResources();
            final int resourceId = resources.getIdentifier(temp[2], "drawable",
                    getActivity().getPackageName());

            tempImages.add(resourceId);
        }

        for(int i=0; i<tempImages.size(); i++){
            images[i] = tempImages.get(i);
        }

        CaptionedChallengeMeImagesAdapter adapter = new CaptionedChallengeMeImagesAdapter(
                tempNames.toArray(new String[0]),images);

        challengeMeAllRecycler.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        challengeMeAllRecycler.setLayoutManager(glm);

        adapter.setListener(new CaptionedChallengeMeImagesAdapter.Listener(){
            public void onClick(int position){

                // Call the ChallengeMe Detail Fragment
                Fragment fragment = ChallengeMeDetailFragment.newInstance(tempNames.get(position).toString());
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

        return challengeMeAllRecycler;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // super.onActivityCreated(savedInstanceState);

        super.onActivityCreated(outState);

        //if(savedInstanceState != null)
        if(outState != null)
        {
            Toast.makeText(getActivity(), "Instance was saved. Do the reload here.",
                    Toast.LENGTH_SHORT);

            challengeMeResponseModel = (ChallengeMeResponseModel)
                    savedInstanceState.getSerializable("responseModel");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("responseModel", (Serializable)challengeMeResponseModel);

        getFragmentManager().putFragment(outState, "ChallengeMeAllFragment", this);
    }

    @Override
    public void onStop()
    {
        super.onStop();

        // outState.putSerializable("responseModel", (Serializable)challengeMeResponseModel);

        // getFragmentManager().putFragment(outState, "ChallengeMeAllFragment", this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

}