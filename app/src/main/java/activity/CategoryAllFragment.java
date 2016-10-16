package activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.RecyclerView;

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


public class CategoryAllFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView categoryAllRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_categories_all, container, false);

        List<ImageDO> imageDOList = null;
        PSLBusinessHelper businessHelper = PSLBusinessHelper.getInstance(getActivity());
        try {
            imageDOList = businessHelper.GetPreviewImagesForCategories();
        } catch (PSLException e) {
            e.printStackTrace();
        }

        int[] images = new int[imageDOList.size()];

        List<Integer> tempImages = new ArrayList<Integer>();
        List<String> tempNames = new ArrayList<String>();

        // TODO: if there is no matching image in the resoure folder, use category_ina.jpg
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
        }

        // CAnnot use the List<int> in the ImageAdapter
        for(int i=0; i<tempImages.size(); i++){
            images[i] = tempImages.get(i);
        }

        CaptionedAllCategoryImageAdapter adapter = new CaptionedAllCategoryImageAdapter(
                tempNames.toArray(new String[0]),images);

        categoryAllRecycler.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        categoryAllRecycler.setLayoutManager(glm);
        return categoryAllRecycler;
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