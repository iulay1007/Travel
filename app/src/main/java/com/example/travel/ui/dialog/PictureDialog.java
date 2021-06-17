package com.example.travel.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.travel.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class PictureDialog extends DialogFragment {
    private Unbinder mBind;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.dialog_word_ig)
    public ImageView imageView;

    private String word_url;

    @SuppressLint("ValidFragment")
    public PictureDialog(String word_url) {
        this.word_url = word_url;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_picture, container);
        mBind= ButterKnife.bind(this,view);
        initView();

        return view;
    }

   // @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        Log.d("pic","dialog");

         Glide.with(getActivity().getApplicationContext()).load(word_url).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

    }

    public void onStart() {

        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            getDialog().setCanceledOnTouchOutside(false);
            super.onStart();
        }


    }

}
