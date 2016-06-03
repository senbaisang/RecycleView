package com.example.sally.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * recycleview
 *  不关心item的位置
 *  不关心item的分割线
 *
 *  只关心item的复用
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private List<String> mDatas;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();

        initView();

        mAdapter = new SimpleAdapter(this, mDatas);
        mAdapter.SetOnClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "位置 ： " + position  , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "位置 long ： " + position, Toast.LENGTH_SHORT).show();
            }
        });


        mRecycleView.setAdapter(mAdapter);

        // 设置recycleview的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());




//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//        mRecycleView.setLayoutManager(gridLayoutManager);

        // 设置recycleview的item之间的分割线
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listview:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                mRecycleView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.gridview:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                mRecycleView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.hor_gridview:
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 5, GridLayoutManager.HORIZONTAL, false);
                mRecycleView.setLayoutManager(gridLayoutManager1);
                break;
            case R.id.staggered:
                Intent intent = new Intent(this, StaggeredActivity.class);
                startActivity(intent);
                break;
            case R.id.add:
                mAdapter.addData(1);
                break;
            case R.id.delete:
                mAdapter.delData(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
