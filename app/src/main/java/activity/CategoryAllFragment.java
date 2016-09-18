package activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.photoshotlist.R;
import com.photoshotlist.bll.PSLBusinessHelper;
import com.photoshotlist.bll.ShotListDO;
import com.photoshotlist.common.Logger;
import com.photoshotlist.exception.PSLException;

import java.util.ArrayList;
import java.util.List;


public class CategoryAllFragment extends ListFragment {

    public interface ListFragmentItemClickListener{
        void onListFragmentItemClick(int position);
    }

    ListFragmentItemClickListener iItemClickListener;

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

        //TODO: Use card view. Instead of list view.
        //TODO: Back button issue on Fragment.

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, GetAllCategories());
        setListAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.iItemClickListener = (ListFragmentItemClickListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        iItemClickListener.onListFragmentItemClick(position);
    }

}