 package com.android.utilslibrary.other;

 import android.util.Log;


 /**
 * Created by lizhichao on 3/31/21
  *
  * 记时工具
  *
 */
 public  class TimeLog {
    private static TimeLog log = new TimeLog();
    private volatile String TAG;
    public Long recordTime;
    private static boolean isOpen;

     public boolean isOpen() {
         return isOpen;
     }

     public void setOpen(boolean open) {
         isOpen = open;
     }

     public void pe(){
        pe(TAG+"：执行耗时  "+ (System.currentTimeMillis() - recordTime)+"毫秒");
    }

    public void pe(String info) {
        Log.e(TAG,info);
    }

    public static  void beginRecord(String TAG){
           if (!isOpen)return;
            if (log.recordTime != null){
                throw new RuntimeException("未结束就开始的标记："+log.TAG);
            }
            log.recordTime = System.currentTimeMillis();
            log.TAG = TAG;
    }
    public synchronized static void endRecord(){
        if (!isOpen)return;
          if (log.recordTime == null){
              throw new RuntimeException("反复结束标记："+log.TAG);
          }
          log.pe();
          log.recordTime = null;
          log.TAG =null;
    }
}