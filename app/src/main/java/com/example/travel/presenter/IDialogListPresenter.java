package com.example.travel.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.travel.base.IBasePresenter;
import com.example.travel.view.IDialogListCallback;
import com.example.travel.view.ISecondListCallback;

import java.util.Set;

public interface IDialogListPresenter extends IBasePresenter<IDialogListCallback> {
    void getDialog(Context context, Handler handler, Set<String> citySet);

    void getCity(Context context, Handler handler);
}
