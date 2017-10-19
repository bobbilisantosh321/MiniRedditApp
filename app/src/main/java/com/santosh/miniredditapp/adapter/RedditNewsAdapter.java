package com.santosh.miniredditapp.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.santosh.miniredditapp.R;
import com.santosh.miniredditapp.data.RedditChildrenResponseData;
import com.santosh.miniredditapp.databinder.OnClickHandler;
import com.santosh.miniredditapp.databinding.RedditNewsDataRowItemBinding;
import com.santosh.miniredditapp.util.UserFriendlyTimeConverterUtil;

import java.util.List;

public class RedditNewsAdapter extends RecyclerView.Adapter<RedditNewsAdapter.RedditNewsViewHolder> {

    private List<RedditChildrenResponseData> redditResponseDataList;
    private Context context;
    private UserFriendlyTimeConverterUtil userFriendlyTimeConverterUtil;


    public RedditNewsAdapter(Context context) {
        this.context = context;
        userFriendlyTimeConverterUtil = UserFriendlyTimeConverterUtil.getInstance();
    }

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
        holder.dateAndTime.setText(userFriendlyTimeConverterUtil.getTimeAgo(responseData.getRedditNewsData().getCreateDate()));
    }

    @Override
    public int getItemCount() {
        return redditResponseDataList == null ? 0 : redditResponseDataList.size();
    }

    public static class RedditNewsViewHolder extends RecyclerView.ViewHolder{

        private TextView dateAndTime;
        RedditNewsDataRowItemBinding binding;
        Context context;

        public RedditNewsViewHolder(RedditNewsDataRowItemBinding binding, Context context) {
            super(binding.getRoot());
            this.binding = binding;
            this.context = context;
            dateAndTime = (TextView) itemView.findViewById(R.id.time);
        }

        public void update(RedditChildrenResponseData redditNewResponse){
            binding.setRedditNewsItem(redditNewResponse.getRedditNewsData());
            binding.setClickHandler(new OnClickHandler(redditNewResponse.getRedditNewsData(), context));
            binding.executePendingBindings();
        }
    }
}
