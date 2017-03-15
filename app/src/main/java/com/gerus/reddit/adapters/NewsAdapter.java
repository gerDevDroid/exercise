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

import java.util.List;

/**
 * Created by gerus-mac on 14/03/17.
 */

public class NewsAdapter extends RecyclerView.Adapter{

    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;

    private Context mContext;
    private List<NewsData> mList;
    private NewsAdapterInterface mInterface;

    public NewsAdapter(Context poContext, List<NewsData> poList, NewsAdapterInterface poInterface){
        mContext = poContext;
        mList = poList;
        mInterface = poInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = null;
        switch(viewType) {
            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_layout, parent, false);
                return new ViewEmpty(mView);
            case VIEW_TYPE_OBJECT_VIEW:
                mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
                return new ViewHolder(mView);
        }
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder poHolder, int piPosition) {
        if(poHolder instanceof ViewHolder) {
            final ViewHolder voHolder = (ViewHolder) poHolder;
            final NewsData poNewsData = mList.get(piPosition);

            voHolder.mTitle.setText(poNewsData.getTitle());
            Glide.with(mContext).load(poNewsData.getThumbnail()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.vc_image).error(R.mipmap.ic_launcher).into(voHolder.mImageView);
            voHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mInterface!=null) mInterface.onItemSelected(poNewsData);
                }
            });
        } else if (poHolder instanceof ViewEmpty){
            final ViewEmpty voHolder = (ViewEmpty) poHolder;
            voHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mInterface!=null) mInterface.onClickEmptyLayout();
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mList.isEmpty()) {
            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
        } else {
            return VIEW_TYPE_OBJECT_VIEW;
        }
    }

    public int getNumberColumns() {
        if(mList.isEmpty()){
            return mContext.getResources().getInteger(R.integer.numberColumns);
        }else {
            return 1;
        }
    }


    @Override
    public int getItemCount() {
        if(mList.isEmpty()){
            return 1;
        }else {
            return mList.size();
        }
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

    public class ViewEmpty extends RecyclerView.ViewHolder {
        public final View mView;

        public ViewEmpty(View view) {
            super(view);
            mView = view;
        }
    }
}
