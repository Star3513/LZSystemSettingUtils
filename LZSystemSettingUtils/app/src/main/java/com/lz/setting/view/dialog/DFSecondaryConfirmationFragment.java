package com.lz.setting.view.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.lz.setting.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFSecondaryConfirmationFragment extends DialogFragment {

    private static final String KEY_DATA_CONTENT = "key_data_content";

    private DFSecondaryConfirmationListener mConfirmListener;

    @BindView(R.id.id_tv_secondary_confirmation_info)
    TextView mTvConfirmInfo;
    private TextView mTvInfoTitle;

    private String mTips;

    public static DFSecondaryConfirmationFragment getInstance(String tips) {
        DFSecondaryConfirmationFragment fragment = new DFSecondaryConfirmationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DATA_CONTENT, tips);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DFDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.app_fragment_secondary_confirmation, null);

        ButterKnife.bind(this, view);
        //添加这一行
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        Window window = getDialog().getWindow();
//        if (window != null) {
//            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initArgues();
        initView();
    }

    private void initArgues() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTips = arguments.getString(KEY_DATA_CONTENT);
        }
    }

    private void initView() {
        mTvConfirmInfo.setText(mTips);
    }


    @OnClick(R.id.id_tv_cancel)
    public void onClickCancel() {
        dismiss();
    }

    @OnClick(R.id.id_tv_confirm)
    public void onClickConfirm() {
        dismiss();
        if (mConfirmListener != null) {
            mConfirmListener.onClickConfirm();
        }
    }

    public void setConfirmListener(DFSecondaryConfirmationListener confirmListener) {
        this.mConfirmListener = confirmListener;
    }

    public interface DFSecondaryConfirmationListener {
        void onClickConfirm();
    }
}
