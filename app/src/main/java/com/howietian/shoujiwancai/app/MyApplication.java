package com.howietian.shoujiwancai.app;

import android.app.Application;
import cn.bmob.v3.Bmob;

/**
 * Created by 83624 on 2017/2/5.
 */

public class MyApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();
    Bmob.initialize(this,Constant.MyApplication_ID);
  }
}
