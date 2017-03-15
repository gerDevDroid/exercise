package com.gerus.reddit.network;

import android.content.Context;
import android.os.AsyncTask;

import com.gerus.reddit.BuildConfig;
import com.gerus.reddit.interfaces.WebTasksInterface;
import com.gerus.reddit.models.network.NetworkModel;
import com.gerus.reddit.models.vo.Data;
import com.gerus.reddit.models.vo.NewsData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerus-mac on 14/03/17.
 */

public class WebTasks {

    private static final String URL = BuildConfig.URL;
    private static final int POSITION_URL = 0;

    protected AsyncTask mAsynkTask;
    protected Context mContext;
    protected ManagerConnection mConnect;

    protected static final int timeoutAsynctask = 15000;

    public WebTasks(Context context) {
        mConnect = new ManagerConnection(context);
        mContext = context;
    }

    public void cancel() {
        if (mAsynkTask != null) {
            mConnect.conn.disconnect();
            mAsynkTask.cancel(true);
        }
    }

    public void prcGetNews(final WebTasksInterface.NewsResult poResult) {
        mAsynkTask = new AsyncTask<String, Void, NetworkModel>() {

            @Override
            protected NetworkModel doInBackground(String ... params) {
                return mConnect.sendRequestGET(params[POSITION_URL], timeoutAsynctask);
            }

            @Override
            protected void onPostExecute(NetworkModel poNetWorkModel) {
                if (poNetWorkModel.getStatusCode() == 200) {
                    try {
                        Data poData = new Gson().fromJson(poNetWorkModel.getMessage(), Data.class);
                        List<NewsData> poNewsDatas = new ArrayList<NewsData>();
                        for (int i = 0; i < poData.getData().getChildrens().size(); i++) {
                            poNewsDatas.add(poData.getData().getChildrens().get(i).getData());
                        }
                        poResult.onResult(poNewsDatas);
                    } catch (Exception e) {
                        poResult.onError(poNetWorkModel.getMessage());
                    }
                } else {
                    poResult.onError(poNetWorkModel.getMessage());
                }
            }
        }.execute(URL);
    }


}
