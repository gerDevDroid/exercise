package com.gerus.reddit.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gerus.reddit.R;
import com.gerus.reddit.models.vo.NewsData;

public class DetailNewActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mImage;
    private NewsData poNewsData;

    private CollapsingToolbarLayout collapsingToolbarLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mContext = this;

        prcInitComponents();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));


        if(getIntent().getExtras()!=null){
            poNewsData = getIntent().getExtras().getParcelable(NewsData.ID);
            Glide.with(mContext).load(poNewsData.getThumbnail()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.vc_warning_black).error(R.mipmap.ic_launcher).into(mImage);
        } else {
            finish();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void prcInitComponents() {
        mImage = (ImageView) findViewById(R.id.image);
    }
}
