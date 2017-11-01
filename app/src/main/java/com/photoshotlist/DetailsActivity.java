package com.photoshotlist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.photoshotlist.bll.ImageDO;
import com.photoshotlist.bll.PSLBusinessHelper;
import com.photoshotlist.bll.ShotListDO;
import com.photoshotlist.exception.PSLException;

import java.util.List;

public class DetailsActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "SelectedCategoryId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String title = getIntent().getExtras().get(EXTRA_MESSAGE).toString();
        String output = "";
        List<ImageDO> imageList = null;

        //Uri bitmap = Uri.parse(getIntent().getStringExtra("image"));
        //Bitmap bitmap = getIntent().getParcelableExtra("image");
        //byte[] compressedImage = getIntent().getByteArrayExtra("image");
        //Bitmap bitmap = Uncompress(compressedImage);

        // Get all images for this Category
        PSLBusinessHelper businessHelper = PSLBusinessHelper.getInstance(this);
        try {
            ShotListDO category = businessHelper.GetCategoryByName(title);

            // Get all the images for this Category
            imageList = businessHelper.GetAllImagesForCategory(category.getId());
        } catch (PSLException e) {
            e.printStackTrace();
        }

        String name = "";
        for(ImageDO obj:imageList)
        {
            if(obj != null)
                output += obj.getName() + "\n";
            else
                output += "No Image \n";
        }

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(output);

        //ImageView imageView = (ImageView) findViewById(R.id.image);
        //imageView.setImageBitmap(bitmap);
        //imageView.setImageURI(bitmap);
    }
}
