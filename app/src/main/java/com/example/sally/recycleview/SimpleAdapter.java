package com.example.sally.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sally on 16/1/2.
 */
public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    protected List<String> mDatas;

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void SetOnClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public SimpleAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_simpleview, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));

        addItemEvent(holder, position);
    }

    protected void addItemEvent(final MyViewHolder holder, final int position) {
        if(onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, currentPosition);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int currentPosition = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, currentPosition);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position) {
        mDatas.add(position, "insert one");
        notifyItemInserted(position);
    }

    public void delData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }


}

class MyViewHolder extends ViewHolder {

    TextView tv;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}

