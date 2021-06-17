package com.example.travel.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.R;
import com.example.travel.model.domain.Tagl;
import com.example.travel.presenter.impl.DialogListPresenterImpl;
import com.example.travel.presenter.impl.DialogProvincePresenterImpl;
import com.example.travel.ui.adapter.DialogAdapter;
import com.example.travel.ui.fragment.FirstFragment;
import com.example.travel.ui.fragment.SecondFragment;
import com.example.travel.view.IDialogListCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class ProvinceChooseDialog extends DialogFragment implements IDialogListCallback {

    private Unbinder mBind;

    private DialogAdapter adapter;

    private List<Tagl> list =new ArrayList<>();

    @BindView(R.id.recyclerview_tag)
    public RecyclerView recyclerView;

    @BindView(R.id.btn_confirm)
    public Button button;

    private DialogProvincePresenterImpl presenter;
    private static final int MESSAGE_UPDATE_LIST = 1;

    private FirstFragment firstFragment;

    @SuppressLint("ValidFragment")
    public ProvinceChooseDialog(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }

    @SuppressLint("ValidFragment")


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_tag, container);
        mBind= ButterKnife.bind(this,view);
        initView();
        initPresenter();
        loadData();
        return view;
    }

    private void loadData() {
        InnerHandler innerHandler = new InnerHandler();
        presenter.getProvince(getActivity(),innerHandler);
    }

    class InnerHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_UPDATE_LIST:
                    adapter.setmData(list);
                    //setupState(State.SUCCESS);

                    break;


            }
        }
    }


    private void initPresenter() {
        presenter = new DialogProvincePresenterImpl();
        presenter.registerViewCallback(this);
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        adapter = new DialogAdapter();
        recyclerView.setAdapter(adapter);
        // Button button=(Button) view.findViewById(R.id.);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
                Log.d("qww",adapter.checkBox.toString());
                firstFragment.updateListByProvince(adapter.checkBox);
                //! secondFragment.updateListByTag(adapter.checkBox);
            }
        });
    }

    @Override
    public void onStart() {

        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            getDialog().setCanceledOnTouchOutside(false);
            super.onStart();
        }


    }

    @Override
    public void onTagListLoad(List<Tagl> listResponses) {
        list=listResponses;
    }
}

