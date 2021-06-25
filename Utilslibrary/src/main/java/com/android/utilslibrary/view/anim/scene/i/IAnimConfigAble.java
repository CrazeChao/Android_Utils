 package com.android.utilslibrary.view.anim.scene.i;

 import android.transition.Transition;

 /**
 * Created by lizhichao on 4/26/21
 */
  public interface IAnimConfigAble<TRANS extends Transition> {
     TRANS addFilter(int id, IOnAnimReady onAnimReady);
}