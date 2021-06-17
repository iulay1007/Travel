package com.example.travel.presenter.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.travel.model.domain.FirstListResponse;
import com.example.travel.model.domain.SecondListCategoryResponse;
import com.example.travel.model.domain.TagResponse;
import com.example.travel.presenter.IFirstListPresenter;
import com.example.travel.view.IFirstListCallback;
import com.example.travel.view.ISecondListCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FirstListPresenterImpl implements IFirstListPresenter {
    private static final int MESSAGE_UPDATE_LIST = 1;
    private IFirstListCallback callback;
    Map<String,String> ProvinceMap = new HashMap<>();

    @Override
    public void getFirst(Context context, Handler handler, Set<String> provinceSet,boolean statue) {
        Log.d("wwwset",provinceSet.toString());
        initMap();
        List<FirstListResponse> responses = new ArrayList<>();
        Log.d("ddd",provinceSet.size()+"");
        if(provinceSet.size()==0) {
            if(statue)
            {
                new Thread(new Runnable() {
                @Override
                public void run() {
                    //TODO:Gson
                    AssetManager assetManager = context.getAssets();
                    Workbook workbook = null;

                    try {
                        // List<String> mPictureList = new ArrayList<>();
                        //List<String> mTitleList = new ArrayList<>();

                        workbook = Workbook.getWorkbook(assetManager.open("GuizhouProvinceData.xls"));
                        Sheet sheet = workbook.getSheet(0);
                        int rows = sheet.getRows();

                        for (int i = 1; i < rows - 1; i++) {
                            //  Gson gson = new Gson();
                            //  mTitleList.clear();

                            responses.add(new FirstListResponse(sheet.getCell(0, i).getContents(), sheet.getCell(1, i).getContents()));
                            Log.d("ee", responses.get(i - 1).toString());
                        }
                        Log.d("ee", responses.size() + "");
                        callback.onFirstListLoad(responses);
                        Message msg = Message.obtain(handler);
                        msg.what = MESSAGE_UPDATE_LIST;
                        handler.sendMessage(msg);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }).start();

        }
            else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //TODO:Gson
                        AssetManager assetManager = context.getAssets();
                        Workbook workbook = null;

                        try {
                            // List<String> mPictureList = new ArrayList<>();
                            //List<String> mTitleList = new ArrayList<>();

                            workbook = Workbook.getWorkbook(assetManager.open("Guizhouanalyze.xls"));
                            Sheet sheet = workbook.getSheet(0);
                            int rows = sheet.getRows();

                            for (int i = 1; i < rows - 1; i++) {


                                responses.add(new FirstListResponse(sheet.getCell(1, i).getContents(), sheet.getCell(2, i).getContents()));
                                Log.d("ee", responses.get(i - 1).toString());
                            }
                            Log.d("ee", responses.size() + "");
                            callback.onFirstListLoad(responses);
                            Message msg = Message.obtain(handler);
                            msg.what = MESSAGE_UPDATE_LIST;
                            handler.sendMessage(msg);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (BiffException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                }).start();
            }

    }
        else {
            responses.clear();



            if(statue){ new Thread(new Runnable() {
                @Override
                public void run() {
                    //TODO:Gson
                    AssetManager assetManager = context.getAssets();
                    Workbook workbook = null;

                    try {
                        // List<String> mPictureList = new ArrayList<>();
                        //List<String> mTitleList = new ArrayList<>();
                        for (String s : provinceSet) {
                            Log.d("ee", "inin");
                            String filename = ProvinceMap.get(s) + "ProvinceData.xls";
                            workbook = Workbook.getWorkbook(assetManager.open(filename));
                            Sheet sheet = workbook.getSheet(0);
                            int rows = sheet.getRows();

                            for (int i = 1; i < rows - 1; i++) {

                                responses.add(new FirstListResponse(sheet.getCell(0, i).getContents(), sheet.getCell(1, i).getContents()));
                                Log.d("ee", responses.get(i - 1).toString());
                            }
                            Log.d("ee", "inin0");
                        }
                        Log.d("ee", responses.size() + "");
                        callback.onFirstListLoad(responses);
                        Message msg = Message.obtain(handler);
                        msg.what = MESSAGE_UPDATE_LIST;
                        handler.sendMessage(msg);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }).start();
        }
            else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //TODO:Gson
                        AssetManager assetManager = context.getAssets();
                        Workbook workbook = null;

                        try {
                            // List<String> mPictureList = new ArrayList<>();
                            //List<String> mTitleList = new ArrayList<>();
                            for (String s : provinceSet) {
                                Log.d("ee", "inin");
                                String filename = ProvinceMap.get(s) + "analyze.xls";
                                workbook = Workbook.getWorkbook(assetManager.open(filename));
                                Sheet sheet = workbook.getSheet(0);
                                int rows = sheet.getRows();

                                for (int i = 1; i < rows - 1; i++) {

                                    responses.add(new FirstListResponse(sheet.getCell(1, i).getContents(), sheet.getCell(2, i).getContents()));
                                    Log.d("ee", responses.get(i - 1).toString());
                                }
                                Log.d("ee", "inin0");
                            }
                            Log.d("ee", responses.size() + "");
                            callback.onFirstListLoad(responses);
                            Message msg = Message.obtain(handler);
                            msg.what = MESSAGE_UPDATE_LIST;
                            handler.sendMessage(msg);

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (BiffException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                }).start();

            }
        }
    }

    private void initMap() {
        ProvinceMap.put("贵州","Guizhou");
        ProvinceMap.put("海南","Hainan");
        ProvinceMap.put("河北","Hebei");
        ProvinceMap.put("辽宁","Liaoning");


    }

    @Override
    public void registerViewCallback(IFirstListCallback callback) {
        this.callback = callback;
    }

    @Override
    public void unregisterViewCallback(IFirstListCallback callback) {
        this.callback = null;
    }
}
