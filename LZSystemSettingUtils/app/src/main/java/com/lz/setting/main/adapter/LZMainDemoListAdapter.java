package com.lz.setting.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lz.setting.R;
import com.lz.setting.main.model.LZMainDemoListModel;
import com.lz.setting.view.DFItemOnClickListener;
import com.lz.star.utils.LZCommonUtils;

import java.util.List;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class LZMainDemoListAdapter extends RecyclerView.Adapter<LZMainDemoListAdapter.LZMainDemoListViewHolder> {

    private List<LZMainDemoListModel> mDataList;
    private LayoutInflater mLayoutInflater;

    private DFItemOnClickListener<LZMainDemoListModel> mItemClickListener;

    public LZMainDemoListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void refreshData(List<LZMainDemoListModel> dataList) {
        mDataList = LZCommonUtils.refreshList(dataList, mDataList);
        notifyDataSetChanged();
    }

    @Override
    public LZMainDemoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.app_recycler_item_main_demo_list, null);
        LZMainDemoListViewHolder viewHolder = new LZMainDemoListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LZMainDemoListViewHolder holder, int position) {
        LZMainDemoListModel demoListModel = mDataList.get(position);
        holder.mTvContent.setText(demoListModel.getContent());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                if (mItemClickListener != null) {
                    LZMainDemoListModel demoListModel = mDataList.get(position);
                    mItemClickListener.onClick(position, demoListModel);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void setItemClickListener(DFItemOnClickListener<LZMainDemoListModel> itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    class LZMainDemoListViewHolder extends RecyclerView.ViewHolder {
        TextView mTvContent;

        public LZMainDemoListViewHolder(View itemView) {
            super(itemView);
            mTvContent = itemView.findViewById(R.id.id_tv_content);
        }
    }
}
