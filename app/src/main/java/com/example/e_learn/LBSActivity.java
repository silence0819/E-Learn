package com.example.e_learn;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Gama on 22/3/17.
 */

public class LBSActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private Button SceneChose1;
    private Button SceneChose2;
    private Button SceneChose3;
    private Button SceneChose4;
    private String[] Data;
    private Button StartLearn;
    private Button getScene;
    private Button changeScene;
    private Button changeScene2;
    private String sb2 = null;
    int choses;
    int[] Random;

    public LocationClient mLocClient = null;
    public BDLocationListener myListener = new MyLocationListener2();


    private MyLocationConfiguration myLocationConfiguration;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    boolean isFirstLoc = true;
    private PercentRelativeLayout Tip;


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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

    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.getScene:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Animation anim = AnimationUtils.loadAnimation(LBSActivity.this, R.anim.up_down);
                    getScene.startAnimation(anim);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Animation anim = AnimationUtils.loadAnimation(LBSActivity.this, R.anim.up_down2);
                    getScene.startAnimation(anim);
                }
                break;
            case R.id.startLearn:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Animation anim = AnimationUtils.loadAnimation(LBSActivity.this, R.anim.up_down);
                    StartLearn.startAnimation(anim);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Animation anim = AnimationUtils.loadAnimation(LBSActivity.this, R.anim.up_down2);
                    StartLearn.startAnimation(anim);
                }
                break;
        }
        return false;
    }

    public class MyLocationListener2 implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();


            sb2 = location.getLocationDescribe();
            Log.i("test", String.valueOf(sb2));
            //30.318594 120.385722
            //30.318387 120.385538

            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(19f);
                MapStatusUpdate u2 = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
                mBaiduMap.animateMapStatus(u2);

            }
        }
    }

}