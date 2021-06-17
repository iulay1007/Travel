package com.example.travel.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import com.example.travel.model.domain.FirstListResponse;
import com.example.travel.presenter.impl.FirstListPresenterImpl;
import com.example.travel.ui.adapter.FirstRecyclerviewAdapter;
import com.example.travel.ui.adapter.SecondRecyclerviewAdapter;
import com.example.travel.ui.dialog.ProvinceChooseDialog;
import com.example.travel.ui.dialog.TagChooseDialog;
import com.example.travel.view.IFirstListCallback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FirstFragment  extends Fragment implements IFirstListCallback {

    private static final int MESSAGE_UPDATE_LIST = 1;
    private Unbinder mBind;


    @BindView(R.id.btn_province)
    Button button;

    @BindView(R.id.btn_change)
    Button btn_change;

    boolean btn_statue= false;

    @BindView(R.id.recyclerview_first)
    public RecyclerView recyclerView;

    private FirstRecyclerviewAdapter recyclerviewAdapter;

    private FirstListPresenterImpl presenter;

    private InnerHandler innerHandler;

    Set<String> setTag = new HashSet<>();

    ProvinceChooseDialog dialog;

    List<FirstListResponse> responseList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        mBind= ButterKnife.bind(this,view);
        initView(view);
        initPresenter();
        loadData();
        initListener();
        return view;

    }

    private void initListener() {
    }

    private void loadData() {
        innerHandler = new InnerHandler();

        presenter.getFirst(getActivity(),innerHandler,setTag,btn_statue);
    }

    class InnerHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_UPDATE_LIST:

                    recyclerviewAdapter.setData(responseList);

            }
        }
    }
    private void initPresenter() {
        presenter = new FirstListPresenterImpl();
        presenter.registerViewCallback(this);
    }

   public void updateListByProvince(Set<String> set){
        this.setTag = set;
        presenter.getFirst(getActivity(),innerHandler,setTag,btn_statue);

    }

    private void initView(View view) {
        btn_statue=false;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        recyclerviewAdapter = new FirstRecyclerviewAdapter();
        recyclerView.setAdapter(recyclerviewAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!btn_statue){
                    btn_statue=true;
                    presenter.getFirst(getContext(),innerHandler,setTag,btn_statue);
                    //btn_statue=true;
                    btn_change.setText("按热度排");
                }
                else {
                    btn_statue=false;
                    presenter.getFirst(getContext(),innerHandler,setTag,btn_statue);


                    btn_change.setText("按评论排");
                }
            }
        });
    }

    private void register() {
        dialog = new ProvinceChooseDialog(this);
        dialog.show(getActivity().getFragmentManager(), "province");
    }

    @Override
    public void onFirstListLoad(List<FirstListResponse> listResponses) {
        this.responseList = listResponses;
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
