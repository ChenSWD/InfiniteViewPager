package com.example.chen.library;

/**
 * 定义一个可以无限滑动的ViewPagerAdapter
 */
public abstract class InfinitePagerAdapter extends PagerAdapterCopy {
    public static final int INFINITE_COUNT = Integer.MAX_VALUE;

    @Override
    public int getCount() {
        //返回无限个
        return INFINITE_COUNT;
    }

    /**
     * 在调用setCurrentItem()时使用该方法返回扩展后的位置
     * 该方法需要在getRealCount()有返回值的时候使用，
     * ViewPager的setCurrentItem()方法要在设置数据之后调用，因为不知道 RealCount，就不能算出扩展后的位置
     */
    public int getExtendItem(int item) {
        int real = getRealCount();
        int total = getCount();
        if (real <= 0) {
            return item;
        }
        //找到大概中间位置的组号
        int index = total / real / 2;
        //返回中间组的第item个位置
        return index * real + item;
    }
}
