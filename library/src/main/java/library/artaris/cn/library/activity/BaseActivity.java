package library.artaris.cn.library.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.common.eventbus.EventBus;

import butterknife.ButterKnife;
import library.artaris.cn.library.R;
import library.artaris.cn.library.core.BaseApplication;
import library.artaris.cn.library.utils.DeviceInfoUtils;
import library.artaris.cn.library.utils.SystemUtils;
import library.artaris.cn.library.utils.logger.Logger;
import library.artaris.cn.library.widgets.ShowToast;

/**
 * Created by Rick on 16/8/24.
 */
public class BaseActivity extends AppCompatActivity {
    private boolean isBackground;

    private ActionBar mActionBar;
    protected Resources mResources;
    protected int mContentID;
    protected ShowToast mToast;

    //=======================Activity Lifecycle Start=========================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResources = this.getResources();
        mToast = new ShowToast(this);
        mActionBar = getSupportActionBar();
        getEventBus().register(this);

        Logger.d("Activtiy",this.getClass().getSimpleName());

        if(getContentID() != 0) {
            setContentView(getContentID());
            ButterKnife.bind(this);
        }
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

    public BaseApplication getArtarisApplication(){
        return (BaseApplication) getApplication();
    }


    protected int getContentID(){
        return mContentID;
    }

    protected void setContentID(int contentID){
        if(contentID != 0)
            mContentID = contentID;
    }

    protected EventBus getEventBus() {
        return getArtarisApplication().getEventBus();
    }

}