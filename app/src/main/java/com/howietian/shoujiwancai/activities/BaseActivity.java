package com.howietian.shoujiwancai.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.howietian.shoujiwancai.R;

public abstract class BaseActivity extends AppCompatActivity {

  /**
   * 做一次修改，提交一次远程仓库的标记！
   * @param savedInstanceState
     */

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentViews();
    ButterKnife.bind(this);
    initViews();
    initListeners();
    initDatas();
  }
  public abstract void setContentViews();
  public abstract void initViews();
  public abstract void initListeners();
  public abstract void initDatas();
  public void showToast(String s){
    Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
  }
}
