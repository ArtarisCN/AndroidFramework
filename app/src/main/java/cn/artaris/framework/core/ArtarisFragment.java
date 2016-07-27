package cn.artaris.framework.core;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.eventbus.EventBus;

import cn.artaris.framework.widgets.ShowToast;


/**
 * Created by Rick on 16/6/8.
 */

public class ArtarisFragment extends Fragment {
    private ArtarisActivity mActivity;
    protected Resources mResources;
    protected EventBus mEventBus;
    protected ShowToast mToast;


    //=========================Fragment Lifecycle Start===================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (ArtarisActivity)getActivity();
        mResources = mActivity.getResources();
        mToast = new ShowToast(mActivity);
        mEventBus = getArtarisApplication().getEventBus();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //=========================Fragment Lifecycle End===================

    protected ArtarisApplication getArtarisApplication(){
        return ((ArtarisActivity) getActivity()).getArtarisApplication();
    }

    public ActionBar getActionBar(){
        return mActivity.getSupportActionBar();
    }

    public EventBus getEventBus() {
        return mEventBus;
    }
}
