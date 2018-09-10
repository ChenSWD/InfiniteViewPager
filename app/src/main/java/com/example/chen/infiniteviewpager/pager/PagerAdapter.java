package com.example.chen.infiniteviewpager.pager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.infiniteviewpager.R;
import com.example.chen.library.InfinitePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends InfinitePagerAdapter {
    View[] viewList;
    List<Integer> textList = new ArrayList<>();

    public PagerAdapter(int size) {
        viewList = new View[size];
    }

    public void setTextList(List<Integer> textList) {
        this.textList = textList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ViewHolder holder;
        //这里position是扩展后的位置，所以要根据位置，计算出当前要复用的item的下标位置
        //循环复用viewList里的view
        int viewPos = position % viewList.length;
        if (viewList[viewPos] == null) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.pager_adapter_item, container, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
            viewList[viewPos] = view;
        } else {
            holder = (ViewHolder) viewList[viewPos].getTag();
        }
        final int realPos = position % textList.size();
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textList.set(realPos, textList.get(realPos) + 1);
                holder.textView.setText("实际位置 " + realPos
                        + "\n是第" + position + "个扩展的位置"
                        + "\ncount = " + textList.get(realPos));
            }
        });
        bindViewHolder(position);
        container.addView(viewList[viewPos]);
        return viewList[viewPos];
    }

    //每一次切换页面的时候刷新一下状态
    public void bindViewHolder(int extendPos) {
        int viewPos = extendPos % viewList.length;
        if (viewList[viewPos] != null) {
            final ViewHolder holder = (ViewHolder) viewList[viewPos].getTag();
            final int realPos = extendPos % textList.size();
            holder.textView.setText("实际位置 " + realPos
                    + "\n是第" + extendPos + "个扩展的位置"
                    + "\ncount = " + textList.get(realPos));
        }
    }

    @Override
    public int getRealCount() {
        return textList.size();
    }

    @Override
    public void onPageSelected(int extendPosition, int realPosition) {
        bindViewHolder(extendPosition);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private static class ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            textView = view.findViewById(R.id.text);
        }
    }
}