package hhan.fiserv.com.recyclerviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hhan on 6/04/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {

    private List<FeedItem> feedItemList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public MyRecyclerViewAdapter(Context context, List<FeedItem> feedItemList){
        this.context = context;
        this.feedItemList = feedItemList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        final FeedItem feedItem = feedItemList.get(position);

        // Render image using Picasso library
        if (!TextUtils.isEmpty(feedItem.getThumbnail())){
            Picasso.with(context).load(feedItem.getThumbnail())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.imageView);
        }

        // Setting text view title
        holder.textView.setText(Html.fromHtml(feedItem.getTitle()));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(feedItem);
            }
        };

        holder.imageView.setOnClickListener(listener);
        holder.textView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        if (feedItemList == null ){
            return 0;
        } else {
            return feedItemList.size();
        }

    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        // Those views need to be kept

        protected ImageView imageView;
        protected TextView textView;

        // Need a default constructor
        public CustomViewHolder(View view){
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }
    // implement the interface OnItemClickListener

    public OnItemClickListener getOnItemClickListener(){

        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
