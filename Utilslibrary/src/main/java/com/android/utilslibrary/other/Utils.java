package com.android.utilslibrary.other;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.TypedValue;

/**
 * Created by lizhichao on 2021/10/25
 */
public class Utils  {

    /**
     * 拷贝
     * */
    public static void copy(String content, Context context) {
        ClipboardUtils.copy(content,context);
    }

    /**
     * 粘贴功能
     * @param context
     * @return
     */
    public static String paste(Context context) {
        return ClipboardUtils.paste(context);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);  //缩放系数
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dp 转 px
     * */
    public static float dpToPixel( float dp) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
    public static int px2dip(float pxValue) {
        final float scale =  Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService
                (Context.TELEPHONY_SERVICE);
        String IMEI = tm.getDeviceId();
        if (TextUtils.isEmpty(IMEI)) {
            IMEI = Settings.Secure.getString(context.getApplicationContext().getContentResolver()
                    , Settings.Secure.ANDROID_ID);

        }
        return IMEI;
    }

}