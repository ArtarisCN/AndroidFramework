package cn.artaris.framework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.artaris.framework.View.LoadingView;
import cn.artaris.framework.core.ArtarisActivity;

public class MainActivity extends ArtarisActivity {

    LinearLayout emptyContainer;


    private Button button01 = null;
    private LoadingView loadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setBackButtonVisible(true);
//        setActionBarVisible(false);
        button01 = (Button)findViewById(R.id.button_first);
        emptyContainer = (LinearLayout)findViewById(R.id.empty_container);
        loadView = new LoadingView();
        loadView.onCreate(this);
        emptyContainer.addView(loadView.getView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadView.show();
    }

    //按钮1的点击事件
    public void changeButtonColor(View view)
    {
            loadView.hide();
    }
}
