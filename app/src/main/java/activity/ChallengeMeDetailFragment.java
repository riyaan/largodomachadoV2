package activity;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.photoshotlist.R;
import com.photoshotlist.bll.ImageDO;
import com.photoshotlist.boundaries.input.CategoryRequestModel;
import com.photoshotlist.boundaries.input.CategoryResponseModel;
import com.photoshotlist.boundaries.input.ChallengeMeResponseModel;
import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.IInputResponseModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.domainmodels.entities.Category;
import com.photoshotlist.domainmodels.entities.Composition;
import com.photoshotlist.domainservices.factories.CategoryFactory;
import com.photoshotlist.infrastructure.repositories.CategoryRepository;
import com.photoshotlist.infrastructure.repositories.CompositionRepository;
import com.photoshotlist.infrastructure.repositories.ImageRepository;
import com.photoshotlist.interactors.CategoryInteractor;
import com.photoshotlist.interactors.ChallengeMeInteractor;
import com.photoshotlist.interactors.CompositionInteractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import adapter.CaptionedChallengeMeDetailImagesAdapter;

public class ChallengeMeDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int categoryId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public void setCategoryId(int id){
        this.categoryId = id;
    }

    public ChallengeMeDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChallengeMeDetailFragment newInstance(String param1) {
        ChallengeMeDetailFragment fragment = new ChallengeMeDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView categoryDetailRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_challengeme_detail, container, false);

        // Get all images for this Category
        List<ImageDO> imageList = new ArrayList<ImageDO>();

        Context context = getActivity();

//        CategoryInteractor categoryInteractor = new CategoryInteractor(
//                new CategoryRepository(context), new ImageRepository(context));
//
//        CategoryRequestModel requestModel = new CategoryRequestModel();
//        requestModel.setCategoryName(mParam1);

//        CategoryResponseModel categoryResponseModel =
//                categoryInteractor.GetCategoryByName(requestModel);

        // mParam1 can be either a Category or a Composition.
        // search for it in Category, if not found search Composition
        ChallengeMeInteractor challengeMeInteractor = new ChallengeMeInteractor(
                new CompositionRepository(context), new CategoryRepository(context),
                new ImageRepository(context));

        ChallengeMeResponseModel challengeMeResponseModel = challengeMeInteractor.Random();

        IInputResponseModel inputResponseModel = null;

        CategoryInteractor ci = new CategoryInteractor(new CategoryRepository(context),
                new ImageRepository(context));
        Category category = ci.GetByName(mParam1);

        CompositionInteractor ci2 = new CompositionInteractor(new CompositionRepository(context),
                new ImageRepository(context));
        Composition composition = ci2.GetByName(mParam1);

        if(category != null)
            inputResponseModel = challengeMeResponseModel.getCategoryResponseModel();
        else if(composition != null)
            inputResponseModel = challengeMeResponseModel.getCompositionResponseModel();

            //for(ImageResponseModel item : categoryResponseModel.getImageResponseModels()){
        for(ImageResponseModel item : inputResponseModel.getImageResponseModels()){

                // TODO: Create a Factory
                ImageDO temp = new ImageDO();
                temp.setCreatedDate(item.getCreatedDate());
                temp.setLocation(item.getLocation());
                temp.setActive(item.isActive());
                temp.setId(item.getId());
                temp.setImageResourceId(item.getImageResourceId());
                temp.setLongDescription(item.getLongDescription());
                temp.setName(item.getName());

                imageList.add(temp);
            }

        int[] images = new int[imageList.size()];

        List<Integer> tempImages = new ArrayList<Integer>();
        final List<String> tempNames = new ArrayList<String>();
        final List<Integer> tempCategoryIds = new ArrayList<Integer>();
        List<String> tempWriteUp = new ArrayList<String>();

        for (ImageDO obj:imageList) {
            String[] temp = obj.getLocation().split(Pattern.quote("."));

            Resources resources = getActivity().getResources();
            final int resourceId = resources.getIdentifier(temp[2], "drawable",
                    getActivity().getPackageName());

            tempNames.add(obj.getName());
            tempImages.add(resourceId);

            // get the category id of the to which the image belongs
            tempCategoryIds.add(obj.getId());

            // get the short writeup for the image
            tempWriteUp.add(obj.getLongDescription());
        }

        // CAnnot use the List<int> in the ImageAdapter
        for(int i=0; i<tempImages.size(); i++){
            images[i] = tempImages.get(i);
        }

        CaptionedChallengeMeDetailImagesAdapter adapter = new CaptionedChallengeMeDetailImagesAdapter(
                tempNames.toArray(new String[0]),images, tempWriteUp);

        categoryDetailRecycler.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        categoryDetailRecycler.setLayoutManager(glm);

        return categoryDetailRecycler;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // ((MainActivity)getActivity()).getSupportActionBar().setSubtitle(mParam1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
