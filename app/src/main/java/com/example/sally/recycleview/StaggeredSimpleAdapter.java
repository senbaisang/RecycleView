package com.example.sally.recycleview;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sally on 16/1/2.
 */
public class StaggeredSimpleAdapter extends SimpleAdapter {

    private List<Integer> mHeights;

    public StaggeredSimpleAdapter(Context context, List<String> datas) {
        super(context, datas);

        mHeights = new ArrayList<Integer>();
        for(int i=0; i<mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random()*300));
        }
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = mHeights.get(position);
        holder.itemView.setLayoutParams(params);
        holder.tv.setText(mDatas.get(position));
        addItemEvent(holder, position);
    }


}

