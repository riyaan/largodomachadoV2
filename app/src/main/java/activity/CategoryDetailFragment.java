package activity;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.photoshotlist.R;
import com.photoshotlist.bll.PSLBusinessHelper;
import com.photoshotlist.bll.ShotListDO;
import com.photoshotlist.common.Logger;
import com.photoshotlist.exception.PSLException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryDetailFragment extends Fragment {
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

    public CategoryDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryDetailFragment newInstance(String param1, String param2) {
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_detail, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        PSLBusinessHelper businessHelper = PSLBusinessHelper.getInstance(getActivity());
        Logger.Debug(this.getClass().getName(), "onStart");

        ShotListDO category = null;
        try {
            category = businessHelper.GetCategoryById(this.categoryId);
        } catch (PSLException e) {
            e.printStackTrace();
        }

        View view = getView();
        if(view != null){
            TextView title = (TextView)view.findViewById(R.id.textView);
            title.setText(category.getName());

            ImageView iv = (ImageView)view.findViewById(R.id.imageViewSample);



            String imageName = "category_"+Integer.toString(this.categoryId);//+".jpg";

            // TODO: Do not hard code
            // Uri uri = Uri.parse("android.resource://"+context.getPackageName()+"/drawable/myimage");
            Uri uri = Uri.parse("android.resource://com.photoshotlist/drawable/"+imageName);
            try {
                iv.setImageURI(uri);
            }
            catch(Exception ex)
            {
                //TODO: Validate the URI, if not found, use the "Image Not Available" image.
                uri = Uri.parse("android.resource://com.photoshotlist/drawable/category_ina");
                iv.setImageURI(uri);
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
