package com.example.e_learn;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

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
    }
}
