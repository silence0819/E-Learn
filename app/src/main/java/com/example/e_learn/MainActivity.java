package com.example.e_learn;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Button home;
    private Button map;
    private Button scene;
    private Button video;
    private Button company;
    private StringBuffer sb;



    class MainMenuClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {


            switch (v.getId()) {


                case R.id.button_home:
                    map.setBackgroundResource(R.drawable.map_1);
                    scene.setBackgroundResource(R.drawable.scene_1);
                    video.setBackgroundResource(R.drawable.vedio_1);
                    company.setBackgroundResource(R.drawable.company_1);
                    home.setBackgroundResource(R.drawable.home_2);
                    break;
                case R.id.scene:
                    home.setBackgroundResource(R.drawable.home_1);
                    map.setBackgroundResource(R.drawable.map_1);
                    video.setBackgroundResource(R.drawable.vedio_1);
                    company.setBackgroundResource(R.drawable.company_1);
                    scene.setBackgroundResource(R.drawable.scene_2);
                    break;
                case R.id.vedio:
                    home.setBackgroundResource(R.drawable.home_1);
                    map.setBackgroundResource(R.drawable.map_1);
                    scene.setBackgroundResource(R.drawable.scene_1);
                    company.setBackgroundResource(R.drawable.company_1);
                    video.setBackgroundResource(R.drawable.vedio_2);
                    break;
                case R.id.company:
                    home.setBackgroundResource(R.drawable.home_1);
                    map.setBackgroundResource(R.drawable.map_1);
                    scene.setBackgroundResource(R.drawable.scene_1);
                    video.setBackgroundResource(R.drawable.vedio_1);
                    company.setBackgroundResource(R.drawable.company_2);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类




        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {


        }
        mLocationClient.registerLocationListener( myListener );
        initLocation();
        mLocationClient.start();


        home = (Button) findViewById(R.id.button_home);
        home.setOnClickListener(new MainMenuClick());
        map = (Button) findViewById(R.id.map);
        map.setOnClickListener(new MainMenuClick());
        scene = (Button) findViewById(R.id.scene);
        scene.setOnClickListener(new MainMenuClick());
        video = (Button) findViewById(R.id.vedio);
        video.setOnClickListener(new MainMenuClick());
        company = (Button) findViewById(R.id.company);
        company.setOnClickListener(new MainMenuClick());

        Button button1 = (Button) findViewById(R.id.button_learn);
        button1.getBackground().setAlpha(125);
        Button button2 = (Button) findViewById(R.id.button_review);
        button2.getBackground().setAlpha(125);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });//learn and review

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.getBackground().setAlpha(0);//toolbar

        Button button3 = (Button) findViewById(R.id.button_signIn);
        button3.getBackground().setAlpha(175);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic2);

        }//drawerlayout

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.message);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });//navView

        Button ConnectDatabases = (Button) findViewById(R.id.Connect);
        ConnectDatabases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabasesTest.class);
                startActivity(intent);
            }
        });

    }
}
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
}
