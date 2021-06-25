 package com.android.utilslibrary.view.anim.scene;

 import android.animation.Animator;
 import android.transition.ChangeBounds;
 import android.transition.TransitionValues;
 import android.view.ViewGroup;

 import com.android.utilslibrary.view.anim.scene.i.IAnimConfigAble;
 import com.android.utilslibrary.view.anim.scene.i.IOnAnimReady;

 /**
 * Created by lizhichao on 4/26/21
 */
 public  class BadyBoyChangeBounds extends ChangeBounds implements IAnimConfigAble<BadyBoyChangeBounds> {
     BadBoyHelper badBoyHelper = new BadBoyHelper();
     @Override
     public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
         Animator animator =  super.createAnimator(sceneRoot, startValues, endValues);
         if (animator != null){
             badBoyHelper.config(animator,startValues.view.getId());
         }
         return animator;
     }
     public BadyBoyChangeBounds addFilter(int id, IOnAnimReady onAnimReady){
         badBoyHelper.addFilter(id,onAnimReady);
         return this;
     }






 }