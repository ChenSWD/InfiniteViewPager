package com.example.chen.infiniteviewpager.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.chen.infiniteviewpager.R;
import com.example.chen.library.AbstractFragment;
import com.example.chen.library.ViewPagerCopy;

import java.util.ArrayList;
import java.util.List;

public class FragmentPagerActivity extends AppCompatActivity implements FragmentPagerAdapter.FragmentAdapterCallBack {
    private List<DataEntity> entityList = new ArrayList<>();
    private ViewPagerCopy viewPagerCopy;
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        List<AbstractFragment> fragments = new ArrayList<>();
        entityList.add(new DataEntity("第一个Fragment"));
        entityList.add(new DataEntity("第二个Fragment"));
        entityList.add(new DataEntity("第三个Fragment"));
        fragments.add(Fragment1.newInstance(entityList.get(0)));
        fragments.add(Fragment2.newInstance(entityList.get(1)));
        fragments.add(Fragment3.newInstance(entityList.get(2)));
        viewPagerCopy = findViewById(R.id.view_pager);
        //一般默认缓存1个，这里为了测试设置4个
        viewPagerCopy.setOffscreenPageLimit(4);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments, this, entityList);
        viewPagerCopy.setAdapter(adapter);
        viewPagerCopy.setCurrentItem(adapter.getExtendItem(1));

        viewPagerCopy.addOnPageChangeListener(new ViewPagerCopy.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public AbstractFragment generateFragmentByPosition(int pos) {
        switch (pos) {
            case 0:
                return Fragment1.newInstance(entityList.get(0));
            case 1:
                return Fragment2.newInstance(entityList.get(1));
            case 2:
                return Fragment3.newInstance(entityList.get(2));
        }
        throw new NullPointerException("没有这个类型的fragment");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
