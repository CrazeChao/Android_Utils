package com.android.utilslibrary.view.helper;

import android.view.View;
import android.view.ViewGroup;

import com.android.utilslibrary.view.helper.Function;

/**
 * Created by lizhichao on 2021/1/12
 */
public class NestedChildHelper<T> extends NestedHelper<T> {
    public NestedChildHelper(ViewGroup group) { super(group); }
    protected void nestedView(View view,Function <T> f){
        if (view instanceof ViewGroup){
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                View childview = group.getChildAt(i);
                try {
                    f.apply((T) childview);
                } catch (ClassCastException e) {
                }
                nestedView(childview,f);
            }
        }
    }

}
