package com.juanocampo.test.androidtest;

import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.juanocampo.test.androidtest.fragments.DetailFragment;
import com.juanocampo.test.androidtest.fragments.ListStoresFragment;
import com.juanocampo.test.androidtest.model.Entry;
import com.juanocampo.test.androidtest.util.AnimationsCommons;

public class MainActivity extends AppCompatActivity implements ListStoresFragment.ListStoresActions {

    private Fragment fragment;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AnimationsCommons.isTablet(this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.activity_main);

    }

    private void executeFragment(Fragment fragmentToExecute) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragmentToExecute)
                .addToBackStack(fragmentToExecute.getTag())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        findViewById(R.id.splash).setVisibility(View.VISIBLE);

        CountDownTimer countDownTimer = new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                //no-op
            }

            @Override
            public void onFinish() {
                findViewById(R.id.splash).setVisibility(View.GONE);

                if (fragment == null) {
                    fragment = ListStoresFragment.newInstance();
                }

                if (!fragment.isAdded() && !fragment.isResumed()) {
                    executeFragment(fragment);
                }


            }
        };
        countDownTimer.start();
    }

    @Override
    public void onCardClicked(Entry entry) {
        detailFragment = DetailFragment.newInstance(entry);
        executeFragment(detailFragment);
    }

    @Override
    public void onBackPressed() {
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
        if (!fragment.isAdded() && !fragment.isResumed()) {
            super.onBackPressed();
        }
    }
}
