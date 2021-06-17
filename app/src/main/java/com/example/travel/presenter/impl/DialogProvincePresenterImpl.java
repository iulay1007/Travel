package com.example.travel.presenter.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;

import com.example.travel.model.domain.Tagl;
import com.example.travel.presenter.IDialogProvincePresenter;
import com.example.travel.view.IDialogListCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DialogProvincePresenterImpl implements IDialogProvincePresenter {
    private static final int MESSAGE_UPDATE_LIST = 1;
    IDialogListCallback callback=null;


    @Override
    public void registerViewCallback(IDialogListCallback callback) {
        this.callback = callback;
    }

    @Override
    public void unregisterViewCallback(IDialogListCallback callback) {
        this.callback = null;
    }

    @Override
    public void getProvince(Context context, Handler handler) {
        List<Tagl> responses = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO:Gson
                AssetManager assetManager=context.getAssets();
                Workbook workbook = null;

                try {
                    // List<String> mPictureList = new ArrayList<>();
                    //List<Tagl> tags = new ArrayList<>();

                    workbook = Workbook.getWorkbook(assetManager.open("ProvinceList.xls"));
                    Sheet sheet = workbook.getSheet(0);
                    int rows=sheet.getRows();

                    for(int i=0;i<rows;i++) {
                        // Gson gson = new Gson();
                        // tags.clear();
                        //Log.d("response",sheet.getCell(1,i).getContents());
                        //  mTitleList.clear();
                        // tags = gson.fromJson(sheet.getCell(2,1).getContents(),new TypeToken<ArrayList<Tagl>>(){}.getType());

                        responses.add(new Tagl(sheet.getCell(0,i).getContents()));
                        //Log.d("response", tags.toString());
                    }

                    callback.onTagListLoad(responses);
                    Message msg =Message.obtain(handler);
                    msg.what = MESSAGE_UPDATE_LIST;
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BiffException e) {
                    e.printStackTrace();
                }finally {
                }
            }
        }).start();

    }
}
