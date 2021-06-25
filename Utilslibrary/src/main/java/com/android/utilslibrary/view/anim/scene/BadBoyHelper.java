 package com.android.utilslibrary.view.anim.scene;

 import android.animation.Animator;
 import android.animation.TimeInterpolator;

 import androidx.annotation.IdRes;


 import com.android.utilslibrary.view.anim.scene.i.IAnimConfig;
 import com.android.utilslibrary.view.anim.scene.i.IOnAnimReady;

 import java.util.HashMap;

 /**
 * Created by lizhichao on 4/26/21
 */
public class BadBoyHelper{
    private HashMap<Integer, IOnAnimReady> onAnimReadyHashMap = new HashMap<>();
     public void config(Animator animator,@IdRes  int id){
         IOnAnimReady onAnimReady = onAnimReadyHashMap.get(id);
         if (onAnimReady == null)return;
         onAnimReady.onReady(animator);
     }

     public void addFilter(int id, IOnAnimReady onAnimReady) {
            onAnimReadyHashMap.put(id,onAnimReady);
     }

     public static class AnimReadyAdapter extends AnimConfg implements IOnAnimReady {
         public AnimReadyAdapter() {
         }
         @Override
         public void onReady(Animator animator) {
             if (this.interpolator != null){
                 animator.setInterpolator(this.interpolator);
             }
             if (this.delay != -1){
                 animator.setDuration(this.delay);
             }
             if (this.startDelay != -1){
                 animator.setDuration(this.startDelay);
             }

         }
     }
     public static class AnimConfg implements IAnimConfig<AnimConfg> {
         protected long delay = -1;
         protected TimeInterpolator interpolator;
         protected long startDelay = -1;
         @Override
         public AnimConfg delay(long delay) {
             this.delay = delay;
             return this;
         }

         @Override
         public AnimConfg interpolator(TimeInterpolator interpolator) {
             this.interpolator = interpolator;
             return this;
         }

         @Override
         public AnimConfg startDelay(long delay) {
             this.startDelay = delay;
             return this;
         }
     }
}