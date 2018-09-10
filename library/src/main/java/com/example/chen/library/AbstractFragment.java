package com.example.chen.library;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AbstractFragment<T> extends Fragment {
    //是否可被添加，初始的true
    private boolean isUsable = true;

    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean isUsable) {
        this.isUsable = isUsable;
    }

    //用来刷新fragment的状态
    public abstract void refreshData(T data);
}
