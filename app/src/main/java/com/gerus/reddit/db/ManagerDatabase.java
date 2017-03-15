package com.gerus.reddit.db;

import android.content.Context;

import com.gerus.reddit.models.vo.NewsData;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerus-mac on 15/03/17.
 */

public class ManagerDatabase {

    private File mFile;
    public static final String DATABASE_NAME = "reddit_db";
    public static final int DATABASE_VERSION = 1;
    private DBHelper dbHelper;

    private static ManagerDatabase sInstance;

    private ManagerDatabase(Context context) {
        this.dbHelper = OpenHelperManager.getHelper(context, DBHelper.class);
        mFile = context.getDatabasePath(DATABASE_NAME);
    }

    public static synchronized ManagerDatabase getInstance(Context context) {
        if (sInstance == null) sInstance = new ManagerDatabase(context);
        return sInstance;
    }

    public boolean checkDataBase() {
        return mFile.exists();
    }


    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public List<NewsData> getNewsData() {
        try {
            return dbHelper.getNewsDataDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<NewsData>();
        }
    }

    public boolean saveNewsData(List<NewsData> poNewses) {
        try {
            TableUtils.clearTable(dbHelper.getConnectionSource(), NewsData.class);
            for (int i = 0; i < poNewses.size(); i++) {
                saveNewData(poNewses.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void saveNewData(NewsData newsData) throws SQLException {
        dbHelper.getNewsDataDAO().createOrUpdate(newsData);
    }

}
