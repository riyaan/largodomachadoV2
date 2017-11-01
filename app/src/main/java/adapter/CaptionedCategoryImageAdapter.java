package adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.photoshotlist.R;

/**
 * Created by PhpDev on 2016/09/25.
 */
public class CaptionedCategoryImageAdapter extends RecyclerView.Adapter<CaptionedCategoryImageAdapter.ViewHolder>{

    private String[] captions;
    private int[] imageIds;

    public CaptionedCategoryImageAdapter(String[] captions, int[] imageIds)
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

    @Override
    public CaptionedCategoryImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_category_detail, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(CaptionedCategoryImageAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageView);
        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.textViewImageName);
        textView.setText(captions[position]);

    }

    @Override
    public int getItemCount() {
        return captions.length;
    }
}
