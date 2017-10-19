package com.santosh.miniredditapp.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.santosh.miniredditapp.R;
import com.santosh.miniredditapp.data.RedditChildrenResponseData;
import com.santosh.miniredditapp.databinder.CreatedTimeConvertorHandler;
import com.santosh.miniredditapp.databinder.OnClickHandler;
import com.santosh.miniredditapp.databinding.RedditNewsDataRowItemBinding;
import java.util.List;

/**
 * Adapter class binding the RedditNewsResponse data.
 *
 */

public class RedditNewsAdapter extends RecyclerView.Adapter<RedditNewsAdapter.RedditNewsViewHolder> {

    private List<RedditChildrenResponseData> redditResponseDataList;
    private Context context;

    public RedditNewsAdapter(Context context) {
        this.context = context;
    }

    /**
     * Updating the RedditNewsResponse when user scrolls through the list.
     * @param redditResponseDataList
     */
    public void setRedditNewResponse(List<RedditChildrenResponseData> redditResponseDataList){
        this.redditResponseDataList = redditResponseDataList;
        notifyDataSetChanged();
    }

    @Override
    public RedditNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RedditNewsDataRowItemBinding binding = DataBindingUtil.inflate(inflater,R.layout.reddit_news_data_row_item,parent,false);
        return new RedditNewsViewHolder(binding,context);
    }

    @Override
    public void onBindViewHolder(RedditNewsViewHolder holder, int position) {
        final RedditChildrenResponseData responseData = redditResponseDataList.get(position);
        holder.update(responseData);
    }

    @Override
    public int getItemCount() {
        return redditResponseDataList == null ? 0 : redditResponseDataList.size();
    }

    /**
     * Used ViewHolder pattern here.
     * Which avoid frequent look up while accessing each list item, saving valuable processor times
     */

    public static class RedditNewsViewHolder extends RecyclerView.ViewHolder{

        //RedditNewsDataRowItemBinding is generated class by DataBinding library
        RedditNewsDataRowItemBinding binding;
        Context context;

        public RedditNewsViewHolder(RedditNewsDataRowItemBinding binding, Context context) {
            super(binding.getRoot());
            this.binding = binding;
            this.context = context;
        }

        public void update(RedditChildrenResponseData redditNewResponse){
            binding.setRedditNewsItem(redditNewResponse.getRedditNewsData());
            binding.setClickHandler(new OnClickHandler(redditNewResponse.getRedditNewsData(), context));
            binding.setDateConvertorHandler(new CreatedTimeConvertorHandler(redditNewResponse.getRedditNewsData()));
            binding.executePendingBindings();
        }
    }
}
