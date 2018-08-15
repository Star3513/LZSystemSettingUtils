package com.lz.setting.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lz.setting.R;
import com.lz.setting.base.LZBaseActivity;
import com.lz.setting.main.adapter.LZMainDemoListAdapter;
import com.lz.setting.main.model.LZMainDemoListModel;
import com.lz.setting.systemsetting.screen.LZScreenSettingActivity;
import com.lz.setting.view.DFItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LZMainActivity extends LZBaseActivity implements DFItemOnClickListener<LZMainDemoListModel> {

    @BindView(R.id.id_rv_demo_list)
    RecyclerView mRvDemoList;

    private static final int LZ_TYPE_SCREEN_SETTING = 100;

    private LZMainDemoListAdapter mDemoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();

        initView();
        initData();
    }

    private void initView() {
        mRvDemoList.setLayoutManager(new LinearLayoutManager(this));

        mDemoListAdapter = new LZMainDemoListAdapter(this);
        mDemoListAdapter.setItemClickListener(this);
        mRvDemoList.setAdapter(mDemoListAdapter);
    }

    private void initData() {
        List<LZMainDemoListModel> demoListModelList = new ArrayList<>();
        LZMainDemoListModel screenModel = new LZMainDemoListModel();
        screenModel.setContent("亮度调节");
        screenModel.setType(LZ_TYPE_SCREEN_SETTING);
        demoListModelList.add(screenModel);
        mDemoListAdapter.refreshData(demoListModelList);
    }

    @Override
    public void onClick(int position, LZMainDemoListModel lzMainDemoListModel) {
        if (lzMainDemoListModel != null) {
            int type = lzMainDemoListModel.getType();
            switch (type) {
                case LZ_TYPE_SCREEN_SETTING:
                    toScreenSettingActivity();
                    break;
            }
        }
    }

    private void toScreenSettingActivity() {
        Intent intent = new Intent(this, LZScreenSettingActivity.class);
        startActivity(intent);
    }
}
