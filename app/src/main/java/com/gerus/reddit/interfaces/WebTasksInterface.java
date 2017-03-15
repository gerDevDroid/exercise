package com.gerus.reddit.interfaces;

import com.gerus.reddit.models.vo.News;

import java.util.List;

/**
 * Created by gerus-mac on 14/03/17.
 */

public interface WebTasksInterface {

    interface NewsResult {
        void onResult(List<News> news);
        void onError(String errorMsg);
    }

}
