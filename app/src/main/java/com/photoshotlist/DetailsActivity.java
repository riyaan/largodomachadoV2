package com.photoshotlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "SelectedCategoryId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String title = getIntent().getExtras().get(EXTRA_MESSAGE).toString();

        //Uri bitmap = Uri.parse(getIntent().getStringExtra("image"));
        //Bitmap bitmap = getIntent().getParcelableExtra("image");
        //byte[] compressedImage = getIntent().getByteArrayExtra("image");
        //Bitmap bitmap = Uncompress(compressedImage);

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        //ImageView imageView = (ImageView) findViewById(R.id.image);
        //imageView.setImageBitmap(bitmap);
        //imageView.setImageURI(bitmap);
    }

    private Bitmap Uncompress(byte[] compressedImage)
    {
        Bitmap bmp = BitmapFactory.decodeByteArray(compressedImage, 0, compressedImage.length);
        return bmp;
    }
}
