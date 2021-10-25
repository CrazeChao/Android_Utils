package com.android.utilslibrary;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by lizhichao on 2020/11/6
 *  activity 管理器
 *
 *  使用教程
 * 初始化
 *  Application{
 *      onCreate(){
 *          AppManager.initialization(this)
 *      }
 *  }
 *
 *  使用
 *  2
 *    AppManager.finish....
 *    AppManager.currentA...
 *    AppManager.appExit...
 */
public class AppManager {
    private static Stack<Activity> activityStack;
        private AppManager() {
        }
        public static void initialization(Application application){
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    AppManager.addActivity(activity);
                }

                @Override
                public void onActivityStarted(Activity activity) {
                }

                @Override
                public void onActivityResumed(Activity activity) {
                }

                @Override
                public void onActivityPaused(Activity activity) {
                }

                @Override
                public void onActivityStopped(Activity activity) {
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    AppManager.removeActivity(activity);
                }
            });
        }

        /**
         * 添加Activity到堆栈
         */
        private static void addActivity(Activity activity) {
            if (activityStack == null) {
                activityStack = new Stack<Activity>();
            }
            activityStack.add(activity);
        }
        private static void removeActivity(Activity activity){
            activityStack.remove(activity);
        }

        /**
         * 获取当前Activity（堆栈中最后一个压入的）
         */
        public static Activity currentActivity() {
            try {
                Activity activity = activityStack.lastElement();
                return activity;
            }catch (NoSuchElementException e){
                return null;
            }
        }

        /**
         * 结束当前Activity（堆栈中最后一个压入的）
         */
        public static void finishActivity() {
            Activity activity = activityStack.lastElement();
            finishActivity(activity);
        }

        /**
         * 结束指定的Activity
         */
        public static void finishActivity(Activity activity) {
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
                activity = null;
            }
        }

        /**
         * 结束指定类名的Activity
         */
        public static void finishActivity(Class<?> cls) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    finishActivity(activity);
                }
            }
        }

        /**
         * 结束所有Activity
         */
        public static void finishAllActivity() {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }

        /**
         * 退出应用程序
         */
        @SuppressWarnings("deprecation")
        public static void appExit(Context context) {
            try {
                finishAllActivity();
                ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                activityManager.restartPackage(context.getPackageName());
                System.exit(0);
            } catch (Exception e) {
                System.exit(0);
                e.printStackTrace();
            }
    }

}
