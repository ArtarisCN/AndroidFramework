package library.artaris.cn.androidlibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import library.artaris.cn.library.activity.ArtarisActivity;

public class MainActivity extends ArtarisActivity  {

    @BindView(R.id.sfdsa)
    TextView sfdsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTittle(library.artaris.cn.library.R.string.network_not_available);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //按钮1的点击事件
    public void changeButtonColor(View view)
    {
    }
}
