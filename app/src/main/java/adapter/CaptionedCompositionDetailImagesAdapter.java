package adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.photoshotlist.R;

import java.util.List;

/**
 * Created by PhpDev on 2016/09/25.
 */
public class CaptionedCompositionDetailImagesAdapter extends RecyclerView.Adapter<CaptionedCompositionDetailImagesAdapter.ViewHolder>{

    // TODO: Use List instead of Array
    private String[] captions;
    private int[] imageIds;
    private List<String> imageShortWriteups;
    private Listener listener;
    private Context context;


    public static interface Listener{
        public void onClick(int position);
    }

    // TODO: Why not just pass in the object?
    // TODO: Refactor
    public CaptionedCompositionDetailImagesAdapter(String[] captions, int[] imageIds, List<String> imageShortWriteups)
    {
        this.captions = captions;
        this.imageIds = imageIds;
        this.imageShortWriteups = imageShortWriteups;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public CaptionedCompositionDetailImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        CardView cv = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_composition_detail, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(CaptionedCompositionDetailImagesAdapter.ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageView);

        // TODO: Create an interface. Remove the dependency. Clean architecture. DI.
        Glide
                .with(this.context)
                .load(imageIds[position])
                .crossFade()
                .into(imageView);

//        // TODO: Testing DI
//        IImageLoader imageLoader = new ImageLoader(this.context, imageView, imageIds[position]);
//        imageLoader.LoadImage();

        TextView textView = (TextView)cardView.findViewById(R.id.textViewImageName);
        textView.setText(captions[position]);

        TextView textViewWriteUp = (TextView)cardView.findViewById(R.id.textViewWriteUp);
        textView.setText(imageShortWriteups.get(position));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }
}
