package library.artaris.cn.androidlibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import library.artaris.cn.library.activity.BaseActivity;
import library.artaris.cn.library.http.BaseHttpError;
import library.artaris.cn.library.http.OnRequestListener;
import library.artaris.cn.library.utils.ImageLoader;

public class MainActivity extends BaseActivity {

    @BindView(R.id.sfdsa)
    TextView sfdsa;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
         profile = (ImageView)findViewById(R.id.image);

        setTittle(library.artaris.cn.library.R.string.network_not_available);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //按钮1的点击事件

    //按钮1的点击事件
    public void changeButtonColor2(View view)
    {
        NetworkHelper.getInstance().GetList(new OnRequestListener<GetListResponseInfo>() {
            @Override
            public void onResponse(int requestCode, GetListResponseInfo requestInfo, BaseHttpError error) {
                mToast.Long(requestInfo.getDate().getDate().get(0).getThumbnail()).Show();
                ImageLoader.with(MainActivity.this,requestInfo.getDate().getDate().get(0).getThumbnail(),profile);

            }
        });

    }
}
