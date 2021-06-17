package com.example.travel.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.travel.base.IBasePresenter;
import com.example.travel.view.ISecondListCallback;

import java.util.Set;

public interface ISecondListPresenter extends IBasePresenter<ISecondListCallback> {
    void getSecond(Context context, Handler handler);

    void getCategory(Context context, Handler handler, Set<String> tagSet,Set<String> citySet);

}

