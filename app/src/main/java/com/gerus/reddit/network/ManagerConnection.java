package com.gerus.reddit.network;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.gerus.reddit.BuildConfig;
import com.gerus.reddit.R;
import com.gerus.reddit.models.network.NetworkModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by gerus-mac on 14/03/17.
 */

public class ManagerConnection {

    protected Context mContext;
    protected URL urlConnect;
    protected HttpURLConnection conn = null;
    private NetworkModel mNetworkModel;

    public ManagerConnection(Context poContext) {
        mContext = poContext;
        mNetworkModel = new NetworkModel(poContext.getString(R.string.error_network));
    }


    private void prcLog(String psLog) {
        if (BuildConfig.LOG) Log.i(this.getClass().getSimpleName(), psLog);
    }


    public NetworkModel sendRequestGET(String psURL, int timeout) {
        try {
            prcLog(psURL);
            prcLog("GET");

            urlConnect = new URL(psURL);
            setConnect(urlConnect, timeout);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int viCode = conn.getResponseCode();
            String vsMsg = convertStreamToString((viCode >= 200 && viCode < 400) ? conn.getInputStream() : conn.getErrorStream());

            prcLog("CODE:" + viCode);
            prcLog("MSG:" + vsMsg);

            mNetworkModel.setStatusCode(viCode);
            mNetworkModel.setMessage(vsMsg);

            return mNetworkModel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mNetworkModel;
    }

    public NetworkModel sendRequestPOST(String psURL, String psjson, int timeout, Type poType) {

        try {
            prcLog(psURL);
            prcLog("POST");
            prcLog("JSON: " + psjson);

            urlConnect = new URL(psURL);
            setConnect(urlConnect, timeout);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(psjson);
            out.close();
            int viCode = conn.getResponseCode();

            String vsMsg = convertStreamToString((viCode >= 200 && viCode < 400) ? conn.getInputStream() : conn.getErrorStream());

            prcLog("CODE:" + viCode);
            prcLog("MSG:" + vsMsg);

            mNetworkModel.setStatusCode(viCode);
            mNetworkModel.setMessage(vsMsg);
            return mNetworkModel;
        } catch (Exception e) {
            //Log.e(MimoniApp.TAG, MimoniApp.ERROR, e);
            mNetworkModel.setMessage(e.getMessage());
        }
        return mNetworkModel;
    }

    public NetworkModel sendRequestPUT(String psURL, int timeout, Type poType) {
        try {
            prcLog(psURL);
            prcLog("PUT");
            urlConnect = new URL(psURL);
            setConnect(urlConnect, timeout);

            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type", "application/json");

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.close();
            int viCode = conn.getResponseCode();

            String vsMsg = convertStreamToString((viCode >= 200 && viCode < 400) ? conn.getInputStream() : conn.getErrorStream());

            prcLog("CODE:" + viCode);
            prcLog("MSG:" + vsMsg);

            mNetworkModel.setStatusCode(viCode);
            mNetworkModel.setMessage(vsMsg);

            return mNetworkModel;
        } catch (IOException e) {
            mNetworkModel.setMessage(e.getMessage());
        }
        return mNetworkModel;
    }


    //////////////////////////////////////////////////////////////////////
    /////////////////////////////   UTILS        /////////////////////////
    //////////////////////////////////////////////////////////////////////

    public void setConnect(URL urlConnect, int timeOut) throws IOException {
        if (urlConnect.getProtocol().startsWith("https")) {
            conn = (HttpsURLConnection) urlConnect.openConnection();
        } else {
            conn = (HttpURLConnection) urlConnect.openConnection();
        }
        conn.setConnectTimeout(timeOut);
        conn.setReadTimeout(timeOut);
    }

    private String getQuery(List<Pair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Pair pair : params) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode(pair.first.toString(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.second.toString(), "UTF-8"));
        }
        return result.toString();
    }

    public String convertStreamToString(InputStream poInputStream) throws IOException {
        if (poInputStream != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(poInputStream, Charset.forName("UTF-8")));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                poInputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }


}
