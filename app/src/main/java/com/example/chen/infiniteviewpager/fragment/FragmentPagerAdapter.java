package com.example.chen.infiniteviewpager.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.chen.library.AbstractFragment;
import com.example.chen.library.InfiniteFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentPagerAdapter extends InfiniteFragmentPagerAdapter {
    private int realCount;
    private Map<Integer, List<AbstractFragment>> fragmentMap = new HashMap<>();
    private FragmentAdapterCallBack callBack;
    private List<DataEntity> entityList;

    public FragmentPagerAdapter(FragmentManager fm, List<AbstractFragment> fragments, FragmentAdapterCallBack callBack, List<DataEntity> entityList) {
        super(fm);
        realCount = fragments.size();
        for (int i = 0; i < fragments.size(); i++) {
            List<AbstractFragment> list = new ArrayList<>();
            list.add(fragments.get(i));
            fragmentMap.put(i, list);
        }
        this.callBack = callBack;
        this.entityList = entityList;
    }

    @Override
    public Fragment getItem(int position) {
        int realPos = position % getRealCount();
        List<AbstractFragment> fragments = fragmentMap.get(realPos);
        //根据实际位置去查找Map，找到list里面第一个可用的fragment返回
        for (AbstractFragment fragment : fragments) {
            if (fragment.isUsable()) {
                return fragment;
            }
        }
        AbstractFragment fragment = null;
        //如果list中无当前位置可用的fragment，就去新建一个
        if (callBack != null) {
            fragment = callBack.generateFragmentByPosition(realPos);
            fragments.add(fragment);
        }
        if (fragment == null)
            throw new NullPointerException("新生成的fragment不能为空");
        return fragment;
    }

    @Override
    protected String findUsableFragmentTag(int position) {
        int realPos = position % getRealCount();
        //根据实际位置去查找Map，找到可用的就返回它的Tag
        List<AbstractFragment> fragments = fragmentMap.get(realPos);
        for (AbstractFragment fragment : fragments) {
            if (fragment.isUsable()) {
                return fragment.getTag();
            }
        }
        return null;
    }

    @Override
    public int getRealCount() {
        return realCount;
    }

    @Override
    public void onPageSelected(int extendPosition, int realPosition) {
        //在换页的时候，判断是不是要刷新扩展后的fragment的状态
        refreshFragmentsIfNeed(realPosition, entityList.get(realPosition));
    }

    //当前fragment是不是扩展添加的，用来判断是否刷新Fragment的状态
    public void refreshFragmentsIfNeed(int pos, DataEntity entity) {
        //在当前位置有扩展的fragment的情况下，且当前位置的数据被更改过，查找当前已经attach的fragment，刷新它们的状态
        if (entity != null && entity.isRefresh()) {
            List<AbstractFragment> fragments = fragmentMap.get(pos);
            if (fragments != null && fragments.size() > 1) {
                for (AbstractFragment fragment : fragments) {
                    if (!fragment.isUsable()) {
                        fragment.refreshData(entity);
                    }
                }
            }
            entity.setRefresh(false);
        }
    }
}
