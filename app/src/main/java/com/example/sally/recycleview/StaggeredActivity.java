package com.example.sally.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * recycleview
 *  不关心item的位置
 *  不关心item的分割线
 *
 *  只关心item的复用
 */
public class StaggeredActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private List<String> mDatas;
    private StaggeredSimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();

        initView();

        mAdapter = new StaggeredSimpleAdapter(this, mDatas);

        mRecycleView.setAdapter(mAdapter);

        mAdapter.SetOnClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {
                mAdapter.delData(position);
            }
        });

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(staggeredGridLayoutManager);
    }

    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.recycle_view);
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for(int i = 'A'; i<='z'; i++) {
            mDatas.add(" " + (char)i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
