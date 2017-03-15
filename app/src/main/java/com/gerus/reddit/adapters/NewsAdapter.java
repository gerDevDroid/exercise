package com.gerus.reddit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gerus.reddit.R;
import com.gerus.reddit.models.vo.News;
import com.gerus.reddit.models.vo.NewsData;
import com.gerus.reddit.utils.UString;

import java.util.List;

/**
 * Created by gerus-mac on 14/03/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private static final int MAX_LENGHT = 25;
    private Context mContext;
    private List<News> mList;

    public NewsAdapter(Context poContext, List<News> poList){
        mContext = poContext;
        mList = poList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsData poNewsData = mList.get(position).getData();
        holder.mTitle.setText(UString.fncsSplit(poNewsData.getTitle(),MAX_LENGHT));
        Glide.with(mContext).load(poNewsData.getThumbnail()).placeholder(R.drawable.vc_warning_black).error(R.mipmap.ic_launcher).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mTitle;
        public final ImageView mImageView;

        public ViewHolder(View poView) {
            super(poView);
            mView = poView;
            mTitle = (TextView) poView.findViewById(R.id.txt_card_news);
            mImageView = (ImageView) poView.findViewById(R.id.image_card_news);

        }
    }
}
