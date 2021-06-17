package com.example.travel.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.travel.base.IBasePresenter;
import com.example.travel.view.IFirstListCallback;
import com.example.travel.view.ISecondListCallback;

import java.util.Set;

public interface IFirstListPresenter extends IBasePresenter<IFirstListCallback> {
    void getFirst(Context context, Handler handler, Set<String> set,boolean statue);
}
