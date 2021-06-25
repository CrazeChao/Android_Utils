package com.android.utilslibrary.view.anim.scene;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Scale extends Transition {
    private static final String PROPNAME_SCALE = "android:leanback:scale";

    public Scale() {
    }

    private void captureValues(TransitionValues values) {
        View view = values.view;
        values.values.put(PROPNAME_SCALE, view.getScaleX());
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(final ViewGroup sceneRoot, TransitionValues startValues,
                                   TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        final float startScale = (Float) startValues.values.get(PROPNAME_SCALE);
        final float endScale = (Float) endValues.values.get(PROPNAME_SCALE);

        final View view = startValues.view;
        view.setScaleX(startScale);
        view.setScaleY(startScale);
        if (startScale == endScale)return null;
        ValueAnimator animator = ValueAnimator.ofFloat(startScale, endScale);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float scale = (Float) animation.getAnimatedValue();
                view.setScaleX(scale);
                view.setScaleY(scale);
                if (view instanceof TextView){
                    view.setAlpha(scale);//修改字体透明度
                }
            }
        });
        return animator;
    }
}