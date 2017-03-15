package com.gerus.reddit.models.network;

/**
 * Created by gerus-mac on 14/03/17.
 */

public class NetworkModel {

    private int statusCode = 0;
    private String message;

    public NetworkModel(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public NetworkModel(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
