package com.gerus.reddit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gerus.reddit.R;
import com.gerus.reddit.interfaces.NewsAdapterInterface;
import com.gerus.reddit.models.vo.NewsData;
import com.gerus.reddit.utils.UString;

import java.util.List;

/**
 * Created by gerus-mac on 14/03/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private static final int MAX_LENGHT = 25;
    private Context mContext;
    private List<NewsData> mList;
    private NewsAdapterInterface mInterface;

    public NewsAdapter(Context poContext, List<NewsData> poList, NewsAdapterInterface poInterface){
        mContext = poContext;
        mList = poList;
        mInterface = poInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NewsData poNewsData = mList.get(position);

        holder.mTitle.setText(UString.fncsSplit(poNewsData.getTitle(),MAX_LENGHT));
        Glide.with(mContext).load(poNewsData.getThumbnail()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.vc_warning_black).error(R.mipmap.ic_launcher).into(holder.mImageView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterface!=null) mInterface.onItemSelected(poNewsData);
            }
        });
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
