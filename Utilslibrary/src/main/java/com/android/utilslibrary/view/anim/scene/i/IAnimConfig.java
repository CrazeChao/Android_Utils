package com.android.utilslibrary.view.anim.scene.i;

import android.animation.TimeInterpolator;

public interface IAnimConfig<T extends IAnimConfig>{
        T delay(long delay);
        T interpolator(TimeInterpolator interpolator);
        T startDelay(long delay);
    }