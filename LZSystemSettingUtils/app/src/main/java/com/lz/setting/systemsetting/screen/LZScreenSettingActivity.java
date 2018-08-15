package com.lz.setting.systemsetting.screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lz.setting.R;
import com.lz.setting.base.LZBaseActivity;
import com.lz.setting.view.dialog.DFSecondaryConfirmationFragment;
import com.lz.star.utils.LZCommonUtils;
import com.lz.star.utils.systemsetting.LZScreenLightUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LZScreenSettingActivity extends LZBaseActivity {
    private static final String TAG = "LZScreenSettingActivity";

    private static final int REQUEST_CODE_WRITE_SETTINGS = 1;

    RelativeLayout mRlytContain;
    @BindView(R.id.id_tv_demo)
    TextView mTvTest;
    @BindView(R.id.id_et_input_light)
    EditText mEtInputLight;
    @BindView(R.id.id_tv_current_light)
    TextView mTvCurrentLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_screen_setting);
        bindView();

        initView();
        initPermission();
    }

    private void initView() {
//        mTvTest.setText("当前亮度");
        refreshCurrentLight();
    }

    private void refreshCurrentLight() {
        int screenBrightness = LZScreenLightUtils.getScreenBrightness(this);
        mTvCurrentLight.setText(String.valueOf(screenBrightness));
    }

    private void initPermission() {
        if (isMarshmallow()) {
            if (!Settings.System.canWrite(this)) {
                // Permission has not been granted and must be
                // requested.
                LZCommonUtils.logI(TAG, "initPermission", "no");
                showRequestSystemSettingDialog();
            }
            LZCommonUtils.logI(TAG, "initPermission", "have");
        }
    }

    private void showRequestSystemSettingDialog() {
        String tips = getString(R.string.app_request_system_setting);
        DFSecondaryConfirmationFragment confirmationFragment = DFSecondaryConfirmationFragment.getInstance(tips);
        confirmationFragment.setConfirmListener(new DFSecondaryConfirmationFragment.DFSecondaryConfirmationListener() {
            @Override
            public void onClickConfirm() {
                requestWriteSettings();
            }
        });
        addFragmentAllowingStateLoss(confirmationFragment);
    }

    private void requestWriteSettings() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE_WRITE_SETTINGS);
    }

    private static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }

    @OnClick(R.id.id_tv_demo)
    public void onClickDemo() {
        int screenBrightness = LZScreenLightUtils.getScreenBrightness(this);
        LZCommonUtils.logI(TAG, "onClickDemo", "screenBrightness", screenBrightness);
    }

    @OnClick(R.id.id_tv_set_screen_light)
    public void onClickScreenLight() {
        //如果不是手动调节模式则更改为手动调节模式
        if (!LZScreenLightUtils.istManualBrightness(this)) {
            LZScreenLightUtils.startManualBrightness(this);
        }
        String inputTextLight = mEtInputLight.getText().toString();
        int light = -1;
        try {
            light = Integer.parseInt(inputTextLight);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (light >= 0) {
            LZScreenLightUtils.saveScreenBrightness(this, light);
        }

        refreshCurrentLight();

        LZCommonUtils.logI(TAG, "onClickDemo", "onClickScreenLight", "light", light);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_WRITE_SETTINGS) {
            if (isMarshmallow()) {
                if (Settings.System.canWrite(this)) {
                    LZCommonUtils.logI(TAG, "canWrite", "true");
                } else {
                    LZCommonUtils.logI(TAG, "canWrite", "false");
                }
            }
        }
    }
}
