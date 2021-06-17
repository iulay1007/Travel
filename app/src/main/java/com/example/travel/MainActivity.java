package com.example.travel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.travel.ui.adapter.ViewPageAdapter;
import com.example.travel.ui.fragment.FirstFragment;
import com.example.travel.ui.fragment.SecondFragment;
import com.example.travel.ui.fragment.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder mBind;

    @BindView(R.id.viewpager)
    public ViewPager viewPager;

    @BindView(R.id.nav_view)
    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window=getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        initView();
    }
    private void initView() {

       // viewPager=(ViewPager)findViewById(R.id.viewpager);

        //bottomNavigationView=(BottomNavigationView)findViewById(R.id.nav_view);

        viewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager(),getFragmentList()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.first:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.second:
                        viewPager.setCurrentItem(1);
                        break;


                }
                return false;
            }
        });
    }

    private List<Fragment> getFragmentList() {
        List<Fragment> list=new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());

        return list;
    }
}