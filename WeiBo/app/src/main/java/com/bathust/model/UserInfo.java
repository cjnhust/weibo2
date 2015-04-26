package com.bathust.model;

import java.net.URL;

/**
 * Created by bathust on 15-4-21.
 */
public class UserInfo {
    private String name;
    private String weiBo;
    private URL photoUrl;
    public UserInfo(String name, String weiBo, URL photoUrl) {
        this.name=name;
        this.weiBo=weiBo;
        this.photoUrl=photoUrl;
    }

    public String getName() {
        return name;
    }

    public String getWeiBo() {
        return weiBo;
    }

    public URL getPhotoUrl() {
        return photoUrl;
    }
}
