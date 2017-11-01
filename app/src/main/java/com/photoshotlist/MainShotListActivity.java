package com.photoshotlist;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainShotListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shot_list);

//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(message);
//
//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main_shot_list);
//        layout.addView(textView);

//        GridView gridview = (GridView) findViewById(R.id.gridView);
//        gridview.setAdapter(new ImageAdapter(this));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(MainShotListActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });



        GridView gridView = (GridView) findViewById(R.id.gridView);
        GridViewAdapter gridAdapter = new GridViewAdapter(this, R.layout.activity_grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(MainShotListActivity.this, DetailsActivity.class);
                //Intent intent = new Intent(MainShotListActivity.this, DetailsActivity.class);

                intent.putExtra("title", item.getTitle());

                //byte[] compressedImage = Compress(item.getImage());
                //intent.putExtra("image", compressedImage);

                Uri uri = Uri.parse("android.resource://com.photoshotlist/"+item.getTitle());
                intent.putExtra("image", uri.toString());

                //Start details activity
                startActivity(intent);
            }
        });
    }

    private byte[] Compress(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));

            TypedValue value = new TypedValue();
            getResources().getValue(imgs.getResourceId(i, -1), value, true);
            String resname = value.string.toString().substring(13, value.string.toString().length());
            // check value.string if not null - it is not null for drawables...

            //Log.d(TAG, "Resource filename:" + value.string.toString());

            //imageItems.add(new ImageItem(bitmap, "Image#" + i));
            imageItems.add(new ImageItem(bitmap, Integer.toString(value.resourceId)));


        }
        return imageItems;
    }

    private Integer[] mThumbIds = {
            R.drawable.category_abstract_01, R.drawable.composition_framing,
            R.drawable.category_aerial, R.drawable.composition_depth,
            R.drawable.composition_symmetry, R.drawable.category_abc,
            R.drawable.category_kids, R.drawable.composition_goldenratio
    };
}
