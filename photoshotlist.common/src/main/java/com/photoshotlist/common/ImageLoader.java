package com.photoshotlist.common;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by PhpDev on 2016/11/27.
 */

public class ImageLoader implements IImageLoader{

    private Context _context;
    private ImageView _imageView;
    private int _imageId;

    public ImageLoader(Context context, ImageView imageView, int imageId)
    {
        _context = context;
        _imageView = imageView;
        _imageId = imageId;
    }

    @Override
    public void LoadImage() {
        // TODO: Create an interface. Remove the dependency. Clean architecture. DI.
//        Glide
//                .with(_context)
//                .load(_imageId)
//                .crossFade()
//                .into(_imageView);
    }
}
