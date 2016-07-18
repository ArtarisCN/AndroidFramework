package cn.artaris.framework.artarisframework.core;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.common.eventbus.EventBus;

import cn.artaris.framework.artarisframework.R;
import cn.artaris.framework.artarisframework.utils.DeviceInfoUtils;
import cn.artaris.framework.artarisframework.utils.SystemUtils;
import cn.artaris.framework.artarisframework.widgets.ShowToast;

/**
 * Created by Rick on 16/6/8.
 */

public class ArtarisActivity extends AppCompatActivity {
    private boolean isBackground;

    private ActionBar mActionBar;
    protected Resources mResources;
    protected ShowToast mToast;

    //=======================Activity Lifecycle Start=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResources = this.getResources();
        mToast = new ShowToast(this);
        mActionBar = getSupportActionBar();

        getEventBus().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isBackground = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (SystemUtils.isBackground(getApplicationContext())) {
            isBackground = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getEventBus().unregister(this);
    }

    //=======================Activity Lifecycle Finish=========================================

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void finishIfWithoutNetwork(){
        if (!DeviceInfoUtils.isNetworkAvailable(this)){
            mToast.Long(mResources.getString(R.string.network_not_available)).Show();
            finish();
        }
    }

    protected void setActionBarVisible(boolean visible){
        if( null != mActionBar){
            if(visible)
                mActionBar.show();
            else
                mActionBar.hide();
        }
    }

    protected void setTittle(int title){
        if(null != mActionBar)
            mActionBar.setTitle(mResources.getString(title));
    }

    protected void setBackButtonVisible(boolean hasBackButton){
        if(null != mActionBar)
        mActionBar.setDisplayHomeAsUpEnabled(hasBackButton);
    }

    protected ArtarisApplication getArtarisApplication(){
        return (ArtarisApplication) getApplication();
    }



    protected EventBus getEventBus() {
        return getArtarisApplication().getEventBus();
    }

}
