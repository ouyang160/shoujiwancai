package com.howietian.shoujiwancai.entities;

import android.widget.ImageView;

/**
 * Created by 78421 on 2017/2/19.
 */

public class MyMsg {
    private boolean isRead=false;
    private String avatar;
    private String name;
    private String time;
    private String infor;

    public MyMsg( String avatar, String name, String time, String infor) {
        this.avatar = avatar;
        this.name = name;
        this.time = time;
        this.infor = infor;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    @Override
    public String toString() {
        return "MyMsg{" +
                "isRead=" + isRead +
                ", avatar=" + avatar +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", infor='" + infor + '\'' +
                '}';
    }
}
