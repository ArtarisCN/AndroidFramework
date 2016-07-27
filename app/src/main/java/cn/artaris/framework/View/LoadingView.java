package cn.artaris.framework.View;

import android.content.Context;
import android.view.View;


import butterknife.BindView;
import butterknife.ButterKnife;
import cn.artaris.framework.R;
import cn.artaris.framework.module.BaseModule;

/**
 * Created by Rick on 16/7/15.
 */
public class LoadingView extends BaseModule {

    @BindView(R.id.empty_container)
     View mEmptyContainer;
    @BindView(R.id.load_view)
    com.mingle.widget.LoadingView mLoadingView;


    private View mView;

    private boolean showing;

    @Override
    public void onCreate(Context context) {
        mView = View.inflate(context, R.layout.view_loading, null);
        ButterKnife.bind(this, mView);
    }

    @Override
    public void onDestroy(Context context) {
    }
    public View getView(){
        return mView;
    }

    public void show(){
        showing = true;
        mEmptyContainer.setVisibility(View.VISIBLE);
    }

    public boolean isShowing(){
        return showing;
    }

    public void hide(){
        showing = false;
        mEmptyContainer.setVisibility(View.GONE);
    }
}
