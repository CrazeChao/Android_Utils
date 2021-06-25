package com.android.utilslibrary.view.touch;

import android.view.MotionEvent;
import android.view.View;


import com.android.utilslibrary.view.anim.CustomAnim;

import java.lang.ref.WeakReference;

/**
 * 锁屏专用适配器
 * */
public class ClickAlphaAnimAdapter implements ITouchAgent {
     WeakReference<View> viewWeakReference;
     public ClickAlphaAnimAdapter(View view) {
         this.viewWeakReference = new WeakReference<>(view);
         view.setAlpha(0.89f);
     }

     @Override
     public void callOnTouchEvent(MotionEvent event) {
         View view = viewWeakReference.get();
         if (view == null)return;
         switch (event.getAction()){
             case MotionEvent.ACTION_DOWN:
                 creatEndAction();
                 view.animate().scaleX(0.95f).scaleY(0.95f).alpha(1).setDuration(CustomAnim.getDefaultDurationTime()/2).setInterpolator(CustomAnim.getDefaultInterception()).withEndAction(endAction).start();break;
             case MotionEvent.ACTION_MOVE:break;
             case MotionEvent.ACTION_CANCEL:
             case MotionEvent.ACTION_UP:
                 waitUpAction = upAction;
                 salfeEndAction();
                 break;
         }
     }



     Runnable waitUpAction = null;
     Runnable endAction = creatEndAction();

     private void salfeEndAction(){
         if (endAction == null)upAction.run();
     }

     private Runnable creatEndAction() {
         return endAction = () -> {
             if (waitUpAction != null){
                 waitUpAction.run();
             }
             endAction = null;
         };
     }

     Runnable upAction = ()->{
         View view = viewWeakReference.get();
         if (view == null)return;
         view.animate().scaleY(1).scaleX(1).alpha(0.89f).setDuration(CustomAnim.getDefaultDurationTime()/2).setInterpolator(CustomAnim.getDefaultInterception()).start();
         waitUpAction = null;
     };
 }