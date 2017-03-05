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

/**
 * Created by PhpDev on 2016/09/25.
 */
public class CaptionedChallengeMeImagesAdapter extends
        RecyclerView.Adapter<CaptionedChallengeMeImagesAdapter.ViewHolder>{

    // TODO: Use List instead of Array
    private String[] captions;
    private int[] imageIds;
    private Listener listener;
    private Context context;


    public static interface Listener{
        public void onClick(int position);
    }

    public CaptionedChallengeMeImagesAdapter(String[] captions, int[] imageIds)
    {
        this.captions = captions;
        this.imageIds = imageIds;
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
    public CaptionedChallengeMeImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        CardView cv = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_challengeme_all, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(CaptionedChallengeMeImagesAdapter.ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageView);

        Glide
                .with(this.context)
                .load(imageIds[position])
                .crossFade()
                .into(imageView);

        // The below is slower and laggy
        //Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        //imageView.setImageDrawable(drawable);

        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.textView);
        textView.setText(captions[position]);
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
