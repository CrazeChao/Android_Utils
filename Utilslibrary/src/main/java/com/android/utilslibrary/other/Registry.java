package com.android.utilslibrary.other;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by lizhichao on 2020/11/6
 * 尽量使用LiveData
 * listener管理
 */
public class Registry<T> implements  IRegistry<T> {
    protected ConcurrentLinkedQueue<T> mRegisters = new ConcurrentLinkedQueue<>();
    public ConcurrentLinkedQueue<T> getRegisters() {
        return mRegisters;
    }
    public  void register(T t){
        mRegisters.add(t);
    }
    public  void unregister(T t){
        mRegisters.remove(t);
    }
    public  void unregisterAll(){
        mRegisters.clear();
    }

    IDistribution<T> listener;
    public void setDistributionDevice(IDistribution<T> listener){
        this.listener = listener;
    }

    @Override
    public void notify(Object obj) {
        for (T t : mRegisters) {
            listener.onDistribution(t,obj);
        }

    }
}