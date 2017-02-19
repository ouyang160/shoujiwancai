package com.howietian.shoujiwancai.activities;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import butterknife.Bind;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.howietian.shoujiwancai.R;
import com.howietian.shoujiwancai.fragments.HomeFragment;
import com.howietian.shoujiwancai.fragments.MeFragment;
import com.howietian.shoujiwancai.fragments.MsgFragment;
import com.howietian.shoujiwancai.fragments.ProjectFragment;

public class MainActivity extends BaseActivity {
private Fragment homeFrg;
  private Fragment meFrg;
  private Fragment msgFrg;
  private Fragment projectFrg;

  BottomNavigationBar bnvBar;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);



  }

  @Override public void setContentViews() {
    setContentView(R.layout.activity_main);

  }

  public void initViews() {
    Window window = getWindow();
    //清除状态栏透明
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //设置状态栏颜色的flag
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    //设置状态栏颜色
    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    bnvBar = (BottomNavigationBar) findViewById(R.id.bnvBar);
    bnvBar.setMode(BottomNavigationBar.MODE_SHIFTING);
    bnvBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
    bnvBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_grey_500_24dp,"首页").setActiveColorResource(R.color.colorPrimary))
        .addItem(new BottomNavigationItem(R.mipmap.ic_dashboard_grey_500_24dp,"项目").setActiveColorResource(R.color.colorPrimary))
        .addItem(new BottomNavigationItem(R.mipmap.ic_chat_bubble_grey_500_24dp,"消息").setActiveColorResource(R.color.colorPrimary))
        .addItem(new BottomNavigationItem(R.mipmap.ic_person_grey_500_24dp,"我的").setActiveColorResource(R.color.colorPrimary))
        .initialise();

  }

   public void initListeners() {
    setBnvBarListenner();
  }

  public void initDatas() {
    setFragments(0);
  }

  private void setFragments(int num){
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    switch (num){
      case 0:
        if(homeFrg==null){
          homeFrg = new HomeFragment();
        }
        ft.replace(R.id.frameLayout,homeFrg);
        break;
      case 1:
        if(projectFrg ==null){
          projectFrg = new ProjectFragment();
        }
        ft.replace(R.id.frameLayout,projectFrg);
        break;
      case 2:
        if(msgFrg == null){
          msgFrg = new MsgFragment();
        }
        ft.replace(R.id.frameLayout,msgFrg);
        break;
      case 3:
        if(meFrg==null){
          meFrg = new MeFragment();
        }
        ft.replace(R.id.frameLayout,meFrg);
        break;
    }
    ft.commit();
  }

  private void setBnvBarListenner(){
    bnvBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
      @Override public void onTabSelected(int position) {
        setFragments(position);
      }

      @Override public void onTabUnselected(int position) {

      }

      @Override public void onTabReselected(int position) {

      }
    });
  }
}
