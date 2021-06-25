package com.android.utilslibrary.other;

public interface IRegistry<T>   {
      void register(T t);
      void unregister(T t);
      void unregisterAll();
      void notify(Object obj);
      void setDistributionDevice(IDistribution<T> listener);
      interface IDistribution<T>{
            void onDistribution(T t,Object object);
      }
}