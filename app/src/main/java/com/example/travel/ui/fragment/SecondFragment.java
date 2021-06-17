package com.example.travel.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.R;
import com.example.travel.model.domain.SecondListCategoryResponse;
import com.example.travel.model.domain.SecondListResponse;
import com.example.travel.presenter.impl.SecondListPresenterImpl;
import com.example.travel.ui.adapter.SecondRecyclerviewAdapter;
import com.example.travel.ui.ativity.ShowSecondListItemActivity;
import com.example.travel.ui.dialog.CityChooseDialog;
import com.example.travel.view.ISecondListCallback;
import com.example.travel.ui.dialog.TagChooseDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SecondFragment  extends Fragment implements ISecondListCallback, SecondRecyclerviewAdapter.onSecondListItemClickListener {
    private static final int MESSAGE_UPDATE_LIST_BY_TAG = 2;
    private Unbinder mBind;

    @BindView(R.id.recyclerview_second)
    public RecyclerView recyclerView;

    TagChooseDialog tagChooseDialog;

    CityChooseDialog cityChooseDialog;

    Set<String> setTag = new HashSet<>();

    public Set<String> setCity = new HashSet<>();

    InnerHandler innerHandler;

    List<SecondListCategoryResponse> secondListCategoryResponses = new ArrayList<>();

    private SecondRecyclerviewAdapter recyclerviewAdapter;

    private SecondListPresenterImpl secondListPresenter;

    private static final int MESSAGE_UPDATE_LIST = 1;
    private List<SecondListResponse> secondListResponses = new ArrayList<>();

    @BindView(R.id.btn_choose)
    public Button button;

    @BindView(R.id.btn_city)
    public Button button_city;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mBind= ButterKnife.bind(this,view);
        initView(view);
        initPresenter();
        loadData();
        initListener();
        return view;
    }

    private void initListener() {
        recyclerviewAdapter.setOnSecondListItemClickListener(this);
    }

    private void initPresenter() {
        secondListPresenter = new SecondListPresenterImpl();
        secondListPresenter.registerViewCallback(this);
    }
    protected void loadData() {
         innerHandler = new InnerHandler();
        secondListPresenter.getSecond(getActivity(),innerHandler);
    }

    @Override
    public void onItemClick(SecondListCategoryResponse response) {
        Intent intent = new Intent(getContext(), ShowSecondListItemActivity.class);
        intent.putExtra("title",response.getTitle());
        intent.putExtra("img",response.getImg());
        intent.putExtra("province",response.getProvince());
        intent.putExtra("city",response.getCity());
        intent.putExtra("address",response.getAddress());
        intent.putExtra("info",response.getInfo());
        intent.putExtra("wordCloud",response.getWordCloud());
        startActivity(intent);
    }

    class InnerHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_UPDATE_LIST:

                   recyclerviewAdapter.setDataByTag(secondListCategoryResponses);

            }
        }
    }

    private void initView(View view) {

         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        recyclerviewAdapter = new SecondRecyclerviewAdapter();
        recyclerView.setAdapter(recyclerviewAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            register();
            }
        });

        button_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCityDialog();
            }
        });
    }

    public void register() {

         tagChooseDialog = new TagChooseDialog(this);
       // registerDialog.show(getFragmentManager(), "TagChooseDialog");
        tagChooseDialog.show(getActivity().getFragmentManager(), "tag");



    }

    public void registerCityDialog() {
        cityChooseDialog = new CityChooseDialog(this);
        cityChooseDialog.show(getActivity().getFragmentManager(),"city");

    }


    public void updateListByTag(Set<String> setTag){
        this.setTag = setTag;
        secondListPresenter.getCategory(getActivity(),innerHandler,setTag,setCity);

    }

    public void updateListByCity(Set<String> setCity){
        this.setCity = setCity;
        secondListPresenter.getCategory(getActivity(),innerHandler,setTag,setCity);
    }



    @Override
    public void onTagListLoad(List<SecondListCategoryResponse> listResponses) {
        this.secondListCategoryResponses =listResponses;
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }
}