package com.gerus.reddit.utils;

import android.app.Activity;
import android.content.Intent;

import com.gerus.reddit.activities.DetailNewActivity;
import com.gerus.reddit.models.vo.NewsData;

/**
 * Created by gerus-mac on 15/03/17.
 */

public class UIntents {

    public static Intent poDetailActivity(Activity poActivity, NewsData poNewsData){
        return new Intent(poActivity, DetailNewActivity.class).putExtra(NewsData.ID, poNewsData);
    }

}
