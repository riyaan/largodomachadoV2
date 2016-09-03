package activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.photoshotlist.R;
import com.photoshotlist.bll.CategoryDO;


public class CategoryAllFragment extends ListFragment {

    public CategoryAllFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories_all, container, false);

        //TODO: Retrieve these categories from the DB.
        //TODO: Implement onClick event handler for each item in the List.
        //TODO: Create a Category Detail Fragment.
        //TODO: Use card view. Instead of list view.
        //TODO: Back button issue on Fragment.

        CategoryDO one = new CategoryDO();
        one.setName("One");
        one.setLongDescription("One Description");

        CategoryDO two = new CategoryDO();
        two.setName("two");
        two.setLongDescription("two Description");

        CategoryDO three = new CategoryDO();
        three.setName("three");
        three.setLongDescription("three Description");

        CategoryDO[] categoryDOs = {one, two, three};

//        ArrayAdapter<CategoryDO> adapter = new ArrayAdapter<CategoryDO>(
//          getActivity(), android.R.layout.simple_list_item_1,
//                categoryDOs
//        );

        String[] values = new String[] { one.getName(), two.getName(), three.getName() };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}