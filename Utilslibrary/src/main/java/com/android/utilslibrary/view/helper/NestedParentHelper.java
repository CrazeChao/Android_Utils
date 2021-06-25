package com.android.utilslibrary.view.helper;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lizhichao on 2021/1/12
 */
public class NestedParentHelper<T> extends NestedHelper<T> {
    public NestedParentHelper(View group) {
        super(group); }
    protected void nestedView(View view, Function<T> f){
        if (view.getParent() == null || !(view.getParent() instanceof ViewGroup))return;
        ViewGroup groupParent = (ViewGroup) view.getParent();
        try {
            f.apply((T)groupParent);
        }catch (ClassCastException e){
            nestedView(groupParent,f);
        }
    }
}
