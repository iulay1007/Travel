package com.example.travel.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.travel.base.IBasePresenter;
import com.example.travel.view.IDialogListCallback;

import java.util.Set;

public interface IDialogProvincePresenter extends IBasePresenter<IDialogListCallback> {
    void getProvince(Context context, Handler handler);
}
