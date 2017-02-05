package adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.photoshotlist.R;

/**
 * Created by PhpDev on 2016/09/25.
 */
public class CaptionedAllCategoryImageAdapter extends RecyclerView.Adapter<CaptionedAllCategoryImageAdapter.ViewHolder>{

    // TODO: Use List instead of Array
    private String[] captions;
    private int[] imageIds;

    public CaptionedAllCategoryImageAdapter(String[] captions, int[] imageIds)
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
    public CaptionedAllCategoryImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_category_all, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(CaptionedAllCategoryImageAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageViewSampleCategoryAll);
        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.textViewCategoryAll);
        textView.setText(captions[position]);

        TextView textViewWriteup = (TextView)cardView.findViewById(R.id.textViewWriteUp);
        textViewWriteup.setText("test");
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }
}
