package com.howietian.shoujiwancai.entities;

import cn.bmob.v3.BmobUser;

/**
 * Created by 83624 on 2017/2/5.
 */

public class User extends BmobUser {
  private Boolean sex;
  private String avatar;
  private Integer age;
  private String nick;


  @Override public String toString() {
    return "User{" +
        "sex=" + sex +
        ", avatar='" + avatar + '\'' +
        ", age=" + age +
        ", nick='" + nick + '\'' +
        '}';
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public Boolean getSex() {
    return sex;
  }

  public void setSex(Boolean sex) {
    this.sex = sex;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
