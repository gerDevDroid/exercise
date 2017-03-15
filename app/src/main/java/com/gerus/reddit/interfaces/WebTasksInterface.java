package com.gerus.reddit.interfaces;

import com.gerus.reddit.models.vo.News;
import com.gerus.reddit.models.vo.NewsData;

import java.util.List;

/**
 * Created by gerus-mac on 14/03/17.
 */

public interface WebTasksInterface {

    interface NewsResult {
        void onResult(List<NewsData> news);
        void onError(String errorMsg);
    }

}
