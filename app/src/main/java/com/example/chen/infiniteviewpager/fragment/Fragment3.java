package com.example.chen.infiniteviewpager.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.infiniteviewpager.R;
import com.example.chen.library.AbstractFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends AbstractFragment<DataEntity> {
    private DataEntity entity;
    private TextView textView;

    public static Fragment3 newInstance(DataEntity entity) {
        Fragment3 fragment = new Fragment3();
        fragment.setData(entity);
        return fragment;
    }

    public void setData(DataEntity entity) {
        this.entity = entity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        textView = view.findViewById(R.id.text_view);
        refreshTextView();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entity.setRefresh(true);
                entity.setCount(entity.getCount() + 1);
                refreshTextView();
            }
        });
        return view;
    }

    @Override
    public void refreshData(DataEntity data) {
        this.entity = data;
        refreshTextView();
    }

    private void refreshTextView() {
        textView.setText(entity.getName() + "\n这是Fragment3" + "\ncount = " + entity.getCount());
    }
}
