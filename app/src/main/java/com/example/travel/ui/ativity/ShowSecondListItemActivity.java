package com.example.travel.ui.ativity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.travel.R;
import com.example.travel.ui.dialog.PictureDialog;
import com.example.travel.ui.dialog.TagChooseDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShowSecondListItemActivity extends AppCompatActivity {

    @BindView(R.id.title_second_act)
    TextView title_second_act;
    @BindView(R.id.img_second_act)
    ImageView img_second_act;
    @BindView(R.id.province_tv_second_act)
    TextView province_tv_second_act;
    @BindView(R.id.city_tv_second_act)
    TextView city_tv_second_act;
    @BindView(R.id.address_tv_second_act)
    TextView address_tv_second_act;
    @BindView(R.id.info_second_act)
    TextView info_second_act;

    private Unbinder mBind = null;

    private String mtitle = "";
    private String mimg = "";
    private String mprovince = "";
    private String mcity = "";
    private String maddress = "";
    private String minfo = " ";
    private String mwordCloud="";


    private PictureDialog pictureDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window=getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_show_second_list_item);
        mBind = ButterKnife.bind(this);
        getIntentMsg();
        initView();
    }

    private void initView() {
        title_second_act.setText(mtitle);
        city_tv_second_act.setText(mcity);
        province_tv_second_act.setText(mprovince);
        address_tv_second_act.setText(maddress);
        info_second_act.setText(minfo);
        Log.d("img",mimg);
        Glide.with(getApplicationContext()).load(mimg).into(img_second_act);
        img_second_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mwordCloud="https://i.loli.net/2021/06/15/SIWsjeQ9JKiM4Fv.png";
               /// Glide.with(getApplicationContext()).load(mwordCloud).into(img_second_act);
                register();
            }
        });
    }

    public void register() {

        pictureDialog = new PictureDialog(mwordCloud);
        pictureDialog.show(getFragmentManager(),"word");
        // registerDialog.show(getFragmentManager(), "TagChooseDialog");
        //pictureDialog.show(getWindow().getWindowManager().getCurrentWindowMetrics(), "word");
        //pictureDialog.show(get,);



    }

    private void getIntentMsg() {
        Intent intent = getIntent();
        mtitle=intent.getStringExtra("title");
        mimg=intent.getStringExtra("img");
        mcity=intent.getStringExtra("city");
        mprovince=intent.getStringExtra("province");
        maddress=intent.getStringExtra("address");
        minfo=intent.getStringExtra("info");
        mwordCloud=intent.getStringExtra("wordCloud");
    }
}