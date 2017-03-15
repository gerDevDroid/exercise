package com.gerus.reddit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gerus.reddit.R;
import com.gerus.reddit.adapters.NewsAdapter;
import com.gerus.reddit.db.ManagerDatabase;
import com.gerus.reddit.interfaces.NewsAdapterInterface;
import com.gerus.reddit.interfaces.WebTasksInterface;
import com.gerus.reddit.models.vo.NewsData;
import com.gerus.reddit.network.WebTasks;
import com.gerus.reddit.utils.UIntents;
import com.gerus.reddit.utils.UNetwork;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Activity mContext;

    //Components
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CoordinatorLayout mCoordinator;

    private NewsAdapter mNewsAdapter;
    private ArrayList<NewsData> mNewsList = new ArrayList<NewsData>();

    protected ManagerDatabase mDB;
    private WebTasks mWebTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mDB = ManagerDatabase.getInstance(mContext);
        mWebTasks = new WebTasks(mContext);

        if (savedInstanceState != null) {
            mNewsList = savedInstanceState.getParcelableArrayList(NewsData.LIST_ID);
        }

        prcInitComponents();
        prcSetReyclerView();

        //Adapter ReyclerView
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext,R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prcRefreshValues();
            }
        });

        // Verify if exist data
        if(mNewsList.isEmpty()){
            if(UNetwork.isOnline(this)){
                mSwipeRefreshLayout.setRefreshing(true);
                prcRefreshValues();
            } else {
                updateRecyclerView(mDB.getNewsData());
            }
        }

    }

    private void updateRecyclerView(List<NewsData> poNewsDatas) {
        mNewsList.clear();
        for (int i = 0; i < poNewsDatas.size(); i++) {
            mNewsList.add(poNewsDatas.get(i));
            mNewsAdapter.notifyItemChanged(i);
        }
    }

    private void prcSetReyclerView() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, getResources().getInteger(R.integer.numberColumns));
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mNewsAdapter.getNumberColumns();
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNewsAdapter = new NewsAdapter(mContext, mNewsList, new NewsAdapterInterface() {
            @Override
            public void onItemSelected(NewsData poNews) {
                startActivity(UIntents.poDetailActivity(mContext, poNews));
            }

            @Override
            public void onClickEmptyLayout() {

            }
        });
        mRecyclerView.setAdapter(mNewsAdapter);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setChangeDuration(1500);
        mRecyclerView.setItemAnimator(itemAnimator);

    }

    @Override
    protected void onSaveInstanceState(Bundle poBundle) {
        super.onSaveInstanceState(poBundle);
        poBundle.putParcelableArrayList(NewsData.LIST_ID,mNewsList);
    }

    /**
     * Get values form server
     */
    private void prcRefreshValues() {
        mWebTasks.prcGetNews(new WebTasksInterface.NewsResult() {

            @Override
            public void onResult(List<NewsData> news) {

                mSwipeRefreshLayout.setRefreshing(false);
                mDB.saveNewsData(news);
                updateRecyclerView(news);
            }

            @Override
            public void onError(String errorMsg) {
                mSwipeRefreshLayout.setRefreshing(false);
                showSnackErrorMessage(errorMsg);
            }
        });
    }

    private void prcInitComponents() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
    }


    public void showSnackErrorMessage(String psMsg) {
        Snackbar mSnackbar = Snackbar.make(mCoordinator, psMsg, Snackbar.LENGTH_LONG);
        mSnackbar.getView().setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        mSnackbar.show();
    }
}
