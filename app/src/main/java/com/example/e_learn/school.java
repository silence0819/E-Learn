package com.example.e_learn;

/**
 * Created by lenovo on 2017/5/26.
 */

import org.litepal.crud.DataSupport;

/**
 * Created by Gama on 27/3/17.
 */

public class school extends DataSupport {
    private int id;
    private String Scene;
    private String EScene;

    public school(String a,String b){
        Scene=a;
        EScene=b;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScene() {
        return Scene;
    }
    public void setScene(String scene) {
        this.Scene = scene;
    }



    public void setEScene(String EScene) {
        this.EScene = EScene;
    }
    public String getEScene() {
        return EScene;
    }
}
