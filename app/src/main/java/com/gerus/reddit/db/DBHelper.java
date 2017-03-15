package com.gerus.reddit.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gerus.reddit.models.vo.News;
import com.gerus.reddit.models.vo.NewsData;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by gerus-mac on 15/03/17.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private Context mContext;
    private Dao<News, Integer> voNewsDAO;
    private Dao<NewsData, String> voNewsDataDAO;

    public DBHelper(Context context) {
        super(context, ManagerDatabase.DATABASE_NAME, null, ManagerDatabase.DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, News.class);
            TableUtils.createTable(connectionSource, NewsData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    protected Dao<News, Integer> getNewsDAO() throws SQLException {
        if (voNewsDAO == null) voNewsDAO = getDao(News.class);
        return voNewsDAO;
    }

    protected Dao<NewsData, String> getNewsDataDAO() throws SQLException {
        if (voNewsDataDAO == null) voNewsDataDAO = getDao(NewsData.class);
        return voNewsDataDAO;
    }
}
