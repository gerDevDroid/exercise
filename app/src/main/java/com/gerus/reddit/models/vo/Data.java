package com.gerus.reddit.models.vo;

import java.util.List;

/**
 * Created by gerus-mac on 14/03/17.
 */

public class Data {

    private String kind;
    private DataInfo data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public DataInfo getData() {
        return data;
    }

    public void setData(DataInfo data) {
        this.data = data;
    }

    public class DataInfo {
        private String modhash;
        private List<News> children;

        public String getModhash() {
            return modhash;
        }

        public void setModhash(String modhash) {
            this.modhash = modhash;
        }

        public List<News> getChildrens() {
            return children;
        }

        public void setChildrens(List<News> children) {
            this.children = children;
        }
    }
}
