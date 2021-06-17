package com.example.travel.presenter.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.travel.model.domain.SecondListCategoryResponse;
import com.example.travel.model.domain.SecondListStrResCate;
import com.example.travel.model.domain.TagResponse;
import com.example.travel.presenter.ISecondListPresenter;
import com.example.travel.view.ISecondListCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SecondListPresenterImpl implements ISecondListPresenter {

    private static final int MESSAGE_UPDATE_LIST = 1;
    private ISecondListCallback mSecondListCallback = null;
    Map<String,String> cityMap = new HashMap<>();

    @Override
    public void getSecond(Context context, Handler handler) {
        List<SecondListCategoryResponse> responses = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO:Gson
                AssetManager assetManager=context.getAssets();
                Workbook workbook = null;

                try {
                   // List<String> mPictureList = new ArrayList<>();
                    //List<String> mTitleList = new ArrayList<>();
                    List<TagResponse> tagResponseList = new ArrayList<>();
                    workbook = Workbook.getWorkbook(assetManager.open("HaikouSight.xls"));
                    Sheet sheet = workbook.getSheet(0);
                    int rows=sheet.getRows();

                    for(int i=1;i<rows;i++){
                        Gson gson = new Gson();
                        tagResponseList.clear();
                        Log.d("response",sheet.getCell(1,i).getContents());
                      //  mTitleList.clear();
                       tagResponseList = gson.fromJson(sheet.getCell(6,i).getContents(),new TypeToken<ArrayList<TagResponse>>(){}.getType());
                          responses.add(new SecondListCategoryResponse(sheet.getCell(2,i).getContents(),sheet.getCell(3,i).getContents(),sheet.getCell(4,i).getContents(),sheet.getCell(5,i).getContents(),sheet.getCell(7,i).getContents(),sheet.getCell(8,i).getContents(),sheet.getCell(9,i).getContents(),new ArrayList<>(tagResponseList)));
                    }
                    mSecondListCallback.onTagListLoad(responses);
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

    @Override
    public void getCategory(Context context, Handler handler, Set<String> tagSet,Set<String> citySet) {
       initMap();
        /*if(tagSet.size()==0){
            getSecond(context,handler);
        return;
        }*/
        List<SecondListCategoryResponse> responses = new ArrayList<>();
        Set<String> titleSet = new HashSet<>();
        Log.d("tags",citySet.size()+"");
        if(citySet.size()!=0) {
            if(tagSet.size()!=0){
                responses.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //TODO:Gson
                        AssetManager assetManager=context.getAssets();
                        Workbook workbook = null;
                        Log.d("tttt",tagSet.toString());
                        try {
                            if(citySet.size()!=0){
                                for (String s : citySet) {
                                    System.out.println(s);
                                    List<SecondListStrResCate> tagResponseList = new ArrayList<>();
                                    List<TagResponse> tags = new ArrayList<>();
                                    String filename = "TagDetail"+cityMap.get(s)+".xls";
                                    workbook = Workbook.getWorkbook(assetManager.open(filename));
                                    Sheet sheet = workbook.getSheet(0);
                                    int rows=sheet.getRows();
                                    for(int i=1;i<rows;i++) {
                                        Gson gson = new Gson();
                                        tagResponseList.clear();
                                        if(tagSet.contains(sheet.getCell(2, i).getContents())){
                                            tagResponseList = gson.fromJson(sheet.getCell(3, i).getContents(), new TypeToken<ArrayList<SecondListStrResCate>>() {
                                            }.getType());
                                            for(int k =0;k<tagResponseList.size();k++){
                                                if(!titleSet.contains(tagResponseList.get(k).getTitle())){
                                                    tags = gson.fromJson(tagResponseList.get(k).getTagList(), new TypeToken<ArrayList<TagResponse>>() {
                                                    }.getType());
                                                    responses.add(new SecondListCategoryResponse(tagResponseList.get(k).getProvince(),tagResponseList.get(k).getCity(),tagResponseList.get(k).getTitle(),tagResponseList.get(k).getAddress(),tagResponseList.get(k).getImg(),tagResponseList.get(k).getInfo(),tagResponseList.get(k).getWordCloud(),tags));
                                                    titleSet.add(tagResponseList.get(k).getTitle());
                                                }

                                            }

                                        }
                                    }
                                }}
                            mSecondListCallback.onTagListLoad(responses);
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


            else{
                new Thread(new Runnable() {
                @Override
                public void run() {
                    //TODO:Gson
                    AssetManager assetManager = context.getAssets();
                    Workbook workbook = null;

                    try {
                        // List<String> mPictureList = new ArrayList<>();
                        //List<String> mTitleList = new ArrayList<>();
                        for (String s : citySet) {
                            System.out.println(s);

                            List<TagResponse> tagResponseList = new ArrayList<>();
                            String fileName = cityMap.get(s) + "Sight.xls";
                            workbook = Workbook.getWorkbook(assetManager.open(fileName));
                            Sheet sheet = workbook.getSheet(0);
                            int rows = sheet.getRows();

                            for (int i = 1; i < rows; i++) {
                                Gson gson = new Gson();
                                tagResponseList.clear();
                                Log.d("response", sheet.getCell(1, i).getContents());
                                //  mTitleList.clear();
                                if (!titleSet.contains(sheet.getCell(4, i).getContents())) {
                                    tagResponseList = gson.fromJson(sheet.getCell(6, i).getContents(), new TypeToken<ArrayList<TagResponse>>() {
                                    }.getType());
                                    responses.add(new SecondListCategoryResponse(sheet.getCell(2, i).getContents(), sheet.getCell(3, i).getContents(), sheet.getCell(4, i).getContents(), sheet.getCell(5, i).getContents(), sheet.getCell(7, i).getContents(), sheet.getCell(8, i).getContents(),sheet.getCell(9, i).getContents(), new ArrayList<>(tagResponseList)));
                                    titleSet.add(sheet.getCell(4, i).getContents());
                                }
                            }
                        }
                        mSecondListCallback.onTagListLoad(responses);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO:Gson
                AssetManager assetManager=context.getAssets();
                Workbook workbook = null;
            Log.d("tttt",tagSet.toString());
                try {
                    /*if(citySet.size()!=0){
                    for (String s : citySet) {
                        System.out.println(s);
                    List<SecondListStrResCate> tagResponseList = new ArrayList<>();
                    List<TagResponse> tags = new ArrayList<>();
                    String filename = "TagDetail"+cityMap.get(s)+".xls";
                    workbook = Workbook.getWorkbook(assetManager.open(filename));
                    Sheet sheet = workbook.getSheet(0);
                    int rows=sheet.getRows();
                    for(int i=1;i<rows;i++) {
                        Gson gson = new Gson();
                        tagResponseList.clear();
                        if(tagSet.contains(sheet.getCell(2, i).getContents())){
                            tagResponseList = gson.fromJson(sheet.getCell(3, i).getContents(), new TypeToken<ArrayList<SecondListStrResCate>>() {
                            }.getType());
                            for(int k =0;k<tagResponseList.size();k++){
                                if(!titleSet.contains(tagResponseList.get(k).getTitle())){
                                    tags = gson.fromJson(tagResponseList.get(k).getTagList(), new TypeToken<ArrayList<TagResponse>>() {
                                    }.getType());
                                    responses.add(new SecondListCategoryResponse(tagResponseList.get(k).getProvince(),tagResponseList.get(k).getCity(),tagResponseList.get(k).getTitle(),tagResponseList.get(k).getAddress(),tagResponseList.get(k).getImg(),tagResponseList.get(k).getInfo(),tags));
                                    titleSet.add(tagResponseList.get(k).getTitle());
                                }

                            }

                        }
                          }
                    }}else */{
                        //#TODO:所有里面找
                        List<SecondListStrResCate> tagResponseList = new ArrayList<>();
                        List<TagResponse> tags = new ArrayList<>();
                        String filename = "TagDetailHaikou.xls";
                        workbook = Workbook.getWorkbook(assetManager.open(filename));
                        Sheet sheet = workbook.getSheet(0);
                        int rows=sheet.getRows();
                        for(int i=1;i<rows;i++) {
                            Gson gson = new Gson();
                            tagResponseList.clear();
                            if(tagSet.contains(sheet.getCell(2, i).getContents())){
                                tagResponseList = gson.fromJson(sheet.getCell(3, i).getContents(), new TypeToken<ArrayList<SecondListStrResCate>>() {
                                }.getType());
                                for(int k =0;k<tagResponseList.size();k++){
                                    if(!titleSet.contains(tagResponseList.get(k).getTitle())){
                                        tags = gson.fromJson(tagResponseList.get(k).getTagList(), new TypeToken<ArrayList<TagResponse>>() {
                                        }.getType());
                                        responses.add(new SecondListCategoryResponse(tagResponseList.get(k).getProvince(),tagResponseList.get(k).getCity(),tagResponseList.get(k).getTitle(),tagResponseList.get(k).getAddress(),tagResponseList.get(k).getImg(),tagResponseList.get(k).getInfo(),tagResponseList.get(k).getWordCloud(),tags));
                                        titleSet.add(tagResponseList.get(k).getTitle());
                                    }

                                }

                            }
                        }

                    }
                    //Log.d("yyyy",responses.get(0).getTagList()+"");
                    mSecondListCallback.onTagListLoad(responses);
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

    private void initMap() {
        cityMap.put("海口","Haikou");
        cityMap.put("三亚","Sanya");
        cityMap.put("武汉","Wuhan");
        cityMap.put("长沙","Changsha");
    }


    @Override
    public void registerViewCallback(ISecondListCallback callback) {
        mSecondListCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ISecondListCallback callback) {
        mSecondListCallback = null;
    }
}
