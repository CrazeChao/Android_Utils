package com.android.utilslibrary.view.helper;
import android.view.View;

/**
 * Created by lizhichao on 2021/1/12
 */
public abstract class NestedHelper<T>{
    private View group;
    public NestedHelper(View group) {
        this.group = group;
    }
    public void invokeNested(Function<T> f){
        nestedView(group,f);
    }
    protected abstract void nestedView(View group, Function <T> f);
}
