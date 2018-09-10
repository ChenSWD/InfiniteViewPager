package com.example.chen.infiniteviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.chen.infiniteviewpager.fragment.FragmentPagerActivity;
import com.example.chen.infiniteviewpager.pager.PagerActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toPagerActivity(View view) {
        startActivity(new Intent(this, PagerActivity.class));
    }

    public void toFragmentActivity(View view) {
        startActivity(new Intent(this, FragmentPagerActivity.class));
    }
}
