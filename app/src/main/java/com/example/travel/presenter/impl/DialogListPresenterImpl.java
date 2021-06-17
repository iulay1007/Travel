package com.example.travel.presenter.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.travel.model.domain.Tagl;
import com.example.travel.presenter.IDialogListPresenter;
import com.example.travel.view.IDialogListCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DialogListPresenterImpl implements IDialogListPresenter {
    private static final int MESSAGE_UPDATE_LIST = 1;
    private IDialogListCallback callback;
    Map<String,String> cityMap = new HashMap<>();

    @Override
    public void getDialog(Context context, Handler handler, Set<String> citySet) {
        List<Tagl> responses = new ArrayList<>();
        if(citySet.size()==0){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO:Gson
                AssetManager assetManager=context.getAssets();
                Workbook workbook = null;

                try {
                    // List<String> mPictureList = new ArrayList<>();
                    List<Tagl> tags = new ArrayList<>();

                    workbook = Workbook.getWorkbook(assetManager.open("TagListHaikou.xls"));
                    Sheet sheet = workbook.getSheet(0);
                    int rows=sheet.getRows();

                    //for(int i=1;i<rows;i++){
                        Gson gson = new Gson();
                       // tags.clear();
                        //Log.d("response",sheet.getCell(1,i).getContents());
                        //  mTitleList.clear();
                       tags = gson.fromJson(sheet.getCell(2,1).getContents(),new TypeToken<ArrayList<Tagl>>(){}.getType());

                        Log.d("response",tags.toString());
                        //  mPictureList = gson.fromJson(sheet.getCell(4,i).getContents(), new  TypeToken<ArrayList<String>>(){}.getType());
                        // mTitleList = gson.fromJson(sheet.getCell(5,i).getContents(), new  TypeToken<ArrayList<String>>(){}.getType());
                        // if(!sheet.getCell(5,i).getContents().equals("none"))
                       // responses.add(new ArrayList<>(tags));
                  //  }
                    responses.addAll(tags);
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
        }).start();}
        else {
            Set<String> mtagSet  = new HashSet<>();
            initMap();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //TODO:Gson
                    AssetManager assetManager=context.getAssets();
                    Workbook workbook = null;

                    try {
                        // List<String> mPictureList = new ArrayList<>();
                        Log.d("rrrr",citySet.toString());
                        for (String s : citySet) {
                        List<Tagl> tags = new ArrayList<>();
                        String filename= "TagList"+cityMap.get(s)+".xls";
                        workbook = Workbook.getWorkbook(assetManager.open(filename));
                        Sheet sheet = workbook.getSheet(0);
                        int rows = sheet.getRows();

                        //for(int i=1;i<rows;i++){
                        Gson gson = new Gson();
                        // tags.clear();
                        //Log.d("response",sheet.getCell(1,i).getContents());
                        //  mTitleList.clear();
                        tags = gson.fromJson(sheet.getCell(2, 1).getContents(), new TypeToken<ArrayList<Tagl>>() {
                        }.getType());

                        Log.d("rrrr", tags.toString());
                        //  mPictureList = gson.fromJson(sheet.getCell(4,i).getContents(), new  TypeToken<ArrayList<String>>(){}.getType());
                        // mTitleList = gson.fromJson(sheet.getCell(5,i).getContents(), new  TypeToken<ArrayList<String>>(){}.getType());
                        // if(!sheet.getCell(5,i).getContents().equals("none"))
                        // responses.add(new ArrayList<>(tags));
                        //  }
                            System.out.println(tags);
                            for(int p = 0;p<tags.size();p++){
                                if(!mtagSet.contains(tags.get(p).getTagName())) {
                                    responses.add(tags.get(p));
                                    mtagSet.add(tags.get(p).getTagName());
                                }
                            }

                        }
                        callback.onTagListLoad(responses);
                        Message msg = Message.obtain(handler);
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

    @Override
    public void getCity(Context context, Handler handler) {
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

                    workbook = Workbook.getWorkbook(assetManager.open("CityList.xls"));
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
    private void initMap() {
        cityMap.put("海口","Haikou");
        cityMap.put("三亚","Sanya");
        cityMap.put("武汉","Wuhan");
        cityMap.put("长沙","Changsha");
    }


    @Override
    public void registerViewCallback(IDialogListCallback callback) {
        this.callback = callback;
    }

    @Override
    public void unregisterViewCallback(IDialogListCallback callback) {
        this.callback = null;
    }
}
