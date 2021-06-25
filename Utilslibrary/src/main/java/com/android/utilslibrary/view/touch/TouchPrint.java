 package com.android.utilslibrary.view.touch;

 import android.util.Log;
 import android.view.MotionEvent;

 /**
 * Created by lizhichao on 6/3/21
  * 触摸日志
 */
 public  class TouchPrint {
     public static void print(MotionEvent motionEvent){
         String event = "";
         switch (motionEvent.getAction()){
             case MotionEvent.ACTION_DOWN: event = "ACTION_DOWN";break;
             case MotionEvent.ACTION_MOVE:event = "ACTION_MOVE";break;
             case MotionEvent.ACTION_UP:event = "ACTION_UP";break;
             case MotionEvent.ACTION_CANCEL:event = "ACTION_CANCEL";break;
         }
         Log.i(Thread.currentThread().getStackTrace()[3].getMethodName(),event);
     }
}