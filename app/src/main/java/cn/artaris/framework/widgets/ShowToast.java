package cn.artaris.framework.widgets;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * Created by Rick on 16/6/8.
 */

public class ShowToast{
    private Toast mToast;
    private Context mContext;

    public ShowToast(Context context){
        this.mContext = context;
        this.mToast = null;
    }

    public ShowToast Short(String string){
        if(mToast == null)
            mToast = Toast.makeText(mContext,string,Toast.LENGTH_SHORT);
        else {
            mToast.setText(string);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        return this;
    }
    public ShowToast Long(String string){
        if(mToast == null)
            mToast = Toast.makeText(mContext,string,Toast.LENGTH_LONG);
        else {
            mToast.setText(string);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        return this;
    }

    public ShowToast MidScreen(){
        mToast.setGravity(Gravity.CENTER, 0, 0);
        return this;
    }

    public void Show(){
        mToast.show();
    }
}
