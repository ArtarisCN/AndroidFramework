package cn.artaris.framework.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 - ViewHolder相关
 新建 XXXViewHolder 继承自BaseViewHolder，指定泛型类型（也就是Item中数据的数据类型）。
 删掉构造方法中的layoutRes参数，在super里面显式指定Layout ID。
 用ButterKnife绑定控件。
 在bindData()方法中完成控件的初始化以及点击事件的传递（别忘了listener的验空）
 - Adapter相关
 新建 XXXAdapter 继承自BaseAdapter，指定泛型类型（也就是Item中数据的数据类型）。
 return new XXXViewHolder(context, parent);
 - 外界相关
 绑定RecyclerView，新建XXXAdapter。
 调用 BaseAdapter.refreshData()方法传入数据列表。
 如果有对点击事件处理的需求，则调用BaseAdapter.setOnClickListener()方法。
 * Created by Rick on 16/6/13.
 */

public abstract class BaseAdapter<V> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 装载了每个Item的Value的列表
     */
    private List<V> mValueList;

    /**
     * 我写的一个接口，通过回调分发点击事件
     */
    private OnItemClickListener<V> mOnItemClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(parent.getContext(), parent);
    }

    @Override
    @SuppressWarnings("unchecked")//一定会是BaseViewHolder的子类，因为createViewHolder()的返回值
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //BaseViewHolder是我抽象出来的RecyclerView.ViewHolder的基类，下面会有详细讲解
        ((BaseViewHolder) holder).setData(mValueList.get(position), position, mOnItemClickListener);
    }

    /**
     * 设置每个Item的点击事件
     * @param listener
     */
    public void setOnClickListener(OnItemClickListener<V> listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 刷新数据
     * @param valueList 新的数据列表
     */
    public void refreshData(List<V> valueList) {
        this.mValueList = valueList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValueList == null ? 0 : mValueList.size();
    }

    /**
     * 生成ViewHolder
     * @param context
     * @param parent
     * @return
     */
    protected abstract BaseViewHolder createViewHolder(Context context, ViewGroup parent);

}