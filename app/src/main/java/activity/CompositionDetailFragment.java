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
import com.photoshotlist.boundaries.input.CompositionRequestModel;
import com.photoshotlist.boundaries.input.CompositionResponseModel;
import com.photoshotlist.boundaries.input.ImageResponseModel;
import com.photoshotlist.infrastructure.repositories.CompositionRepository;
import com.photoshotlist.infrastructure.repositories.ImageRepository;
import com.photoshotlist.interactors.CompositionInteractor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import adapter.CaptionedCompositionDetailImagesAdapter;

public class CompositionDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int compositionId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public void setCompositionId(int id){
        this.compositionId = id;
    }

    public CompositionDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CompositionDetailFragment newInstance(String param1) {
        CompositionDetailFragment fragment = new CompositionDetailFragment();
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

        RecyclerView compositionDetailRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_composition_detail, container, false);

        // Get all images for this Category
        List<ImageDO> imageList = new ArrayList<ImageDO>();

        Context context = getActivity();

        CompositionInteractor compositionInteractor = new CompositionInteractor(
                new CompositionRepository(context), new ImageRepository(context));

        CompositionRequestModel requestModel = new CompositionRequestModel();
        requestModel.setCompositionName(mParam1);

        CompositionResponseModel compositionResponseModel =
                compositionInteractor.GetCompositionByName(requestModel);

            for(ImageResponseModel item : compositionResponseModel.getImageResponseModels()){

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
        final List<Integer> tempCompositionIds = new ArrayList<Integer>();
        List<String> tempWriteUp = new ArrayList<String>();

        for (ImageDO obj:imageList) {
            String[] temp = obj.getLocation().split(Pattern.quote("."));

            Resources resources = getActivity().getResources();
            final int resourceId = resources.getIdentifier(temp[2], "drawable",
                    getActivity().getPackageName());

            tempNames.add(obj.getName());
            tempImages.add(resourceId);

            // get the category id of the to which the image belongs
            tempCompositionIds.add(obj.getId());

            // get the short writeup for the image
            tempWriteUp.add(obj.getLongDescription());
        }

        // CAnnot use the List<int> in the ImageAdapter
        for(int i=0; i<tempImages.size(); i++){
            images[i] = tempImages.get(i);
        }

        CaptionedCompositionDetailImagesAdapter adapter = new CaptionedCompositionDetailImagesAdapter(
                tempNames.toArray(new String[0]),images, tempWriteUp);

        compositionDetailRecycler.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        compositionDetailRecycler.setLayoutManager(glm);

        return compositionDetailRecycler;
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
