package com.example.chen.infiniteviewpager.fragment;

public class DataEntity {
    private String name;
    private int count;
    private boolean refresh = false;

    public DataEntity(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }
}
