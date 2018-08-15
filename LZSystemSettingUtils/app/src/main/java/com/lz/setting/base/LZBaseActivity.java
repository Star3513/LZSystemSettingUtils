package com.lz.setting.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class LZBaseActivity extends Activity {
    private Unbinder mViewBind;

    protected void bindView() {
        mViewBind = ButterKnife.bind(this);
    }

    protected void unBidView() {
        if (mViewBind != null) {
            mViewBind.unbind();
        }
    }

    protected void replaceFragment(int containId, Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containId, fragment);
        fragmentTransaction.commit();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void addFragmentAllowingStateLoss(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(fragment, this.getClass().getSimpleName());
        ft.commitAllowingStateLoss();//注意这里使用commitAllowingStateLoss()
    }
}
