package com.example.chen.infiniteviewpager.pager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.chen.infiniteviewpager.R;
import com.example.chen.library.ViewPagerCopy;

import java.util.ArrayList;
import java.util.List;

public class PagerActivity extends AppCompatActivity {
    private ViewPagerCopy mViewPager;
    private PagerAdapter mAdapter;
    List<Integer> text = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        for (int i = 0; i < 3; i++) {
            text.add(0);
        }
        //最多缓存两个(当前view一边两个，总共5个)
        int limit = 2;
        mViewPager = findViewById(R.id.view_pager);

        //缓存view的个数为limit*2 + 2
        //为什么是limit*2 + 2：正常情况下应该缓存 limit*2 + 1 个view，因为左右两边各缓存limit个，
        //在从左向右滑动换页时是没有问题的，因为会先执行destroyItem()方法再执行instantiateItem()方法，
        //这样instantiateItem时就可以复用destroyItem时remove的view
        //但是ViewPager在从右向左滑动的时候，会先执行instantiateItem()方法再执行destroyItem()方法，
        // 这样就必须多缓存一个，即：limit*2 + 2个
        mAdapter = new PagerAdapter(limit * 2 + 2);
        mAdapter.setTextList(text);
        mViewPager.setAdapter(mAdapter);
        //设置缓存的限制，默认会缓存1个
        mViewPager.setOffscreenPageLimit(limit);
        //数据的设置(setTextList())要在setCurrentItem()之前，
        mViewPager.setCurrentItem(mAdapter.getExtendItem(1));
        mAdapter.notifyDataSetChanged();
    }
}
