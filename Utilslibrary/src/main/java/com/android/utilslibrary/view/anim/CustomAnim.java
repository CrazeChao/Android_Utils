package com.android.utilslibrary.view.anim;

import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;

public    class CustomAnim {
   public static final long DefaultDurationTime = 300;
    public static long getDefaultDurationTime() {
        return DefaultDurationTime;
    }


    public static Path getDefaultPath(){
           Path path = new Path();
           path.moveTo(0, 0);
           path.cubicTo(0.25f, 0.1f, 0.25f, 1f, 1f, 1f);
        return path;
   }
    public static Path getCustomPath(){
        Path path = new Path();
        path.moveTo(0, 0);
        path.cubicTo(0.5f, 0f, 0.5f, 1f, 1f, 1f);
        return path;
    }

   public static Interpolator getDefaultInterception(){
       return new PathInterpolator(getDefaultPath());
   }
   public static Interpolator getOverShootInterception(){
        return new OvershootInterpolator();
   }

   public static Interpolator getSpringInterception(){
        return new SpringScaleInterpolator(0.4f);
   }
    public static Interpolator getSpringInterception(float factor ){
        return new SpringScaleInterpolator(factor);
    }
    public static class SpringScaleInterpolator implements Interpolator {
        //弹性因数
        private float factor;

        public SpringScaleInterpolator(float factor) {
            this.factor = factor;
        }

        @Override
        public float getInterpolation(float input) {
            return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
        }


    }
    public static Interpolator getClickInterpolator(){
        return new OvershootInterpolator(5.0f);
    }

}