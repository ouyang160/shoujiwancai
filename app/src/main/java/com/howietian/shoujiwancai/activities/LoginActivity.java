package com.howietian.shoujiwancai.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import com.howietian.shoujiwancai.R;
import com.howietian.shoujiwancai.entities.User;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity {
  @Bind(R.id.et_phoneNum) EditText et_phoneNum;
  @Bind(R.id.et_pwd) EditText et_pwd;
  @Bind(R.id.tv_forgetPwd) TextView tv_forgetPwd;
  @Bind(R.id.btn_login) Button btn_login;
@Bind(R.id.tv_visitor) TextView tv_visitor;
  @Bind(R.id.tv_register) TextView tv_register;
  String phoneNum;
  String pwd;
  boolean flag;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void setContentViews() {
    setContentView(R.layout.activity_login);
  }

  @Override public void initViews() {

  }

  @Override public void initListeners() {

  }

  @Override public void initDatas() {

  }
  //跳转注册界面
  @OnClick(R.id.tv_register)
  public void register(){
    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
    startActivity(intent);

  }
  //随便看看，跳转主界面
  @OnClick(R.id.tv_visitor) public void visit(){
    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
    startActivity(intent);
  }
  //登陆功能
  @OnClick(R.id.btn_login) public void login(){
    userLogin();
  }

  private void userLogin(){
    if(isCheckData()){
      final ProgressDialog dialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
      dialog.setMessage("正在登陆中...");
      dialog.setCanceledOnTouchOutside(false);
      dialog.show();
      BmobUser.loginByAccount(phoneNum, pwd, new LogInListener<User>() {
        @Override public void done(User user, BmobException e) {
          if(e==null){



            showToast("登陆成功");
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
          }else{
            showToast(e.toString());
          }
        }
      });
      dialog.dismiss();
    }
  }

  ////判断输入的合法性
  public boolean isCheckData() {
    phoneNum = et_phoneNum.getText().toString();
    pwd = et_pwd.getText().toString();
    //判断3个输入是否为空
  if (TextUtils.isEmpty(phoneNum)) {
      et_phoneNum.setError("手机号码不能为空");
      return false;
    } else if (TextUtils.isEmpty(pwd)) {
      et_pwd.setError("密码不能为空");
      return false;
    } else if (!isStringFormatCorrect( pwd)) {
      showToast("只允许中文、字母、数字、下划线");
      return false;
    } else if (pwd.length() < 6) {
      showToast("密码长度至少为6位");
      return false;
    } else if (!isPhoneNum(phoneNum)) {
      showToast("手机号码错误");
      return false;
    } //else if (!isExist()) {
    //  showToast("该手机号码未注册");
    //  return false;
    //}
    return true;
  }

  //判断用户名是否存在
  public boolean isExist() {

    BmobQuery<User> query = new BmobQuery<User>();
    query.addWhereEqualTo("username", phoneNum);
    query.findObjects(new FindListener<User>() {
      @Override public void done(List<User> list, BmobException e) {
        if (e == null) {
          flag = true;
          Log.e("TAG", phoneNum);
        } else {
          Log.e("TAG", "HHHH");
          flag = false;
        }
      }
    });
    return flag;
  }

  //利用正则表达式判断只为数字、字母、下划线、中文
  public boolean isStringFormatCorrect(String str) {
    String strPattern = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
    Pattern p = Pattern.compile(strPattern);
    Matcher m = p.matcher(str);
    return m.matches();
  }

  //利用正则表达式判断手机号码的合法性
  public boolean isPhoneNum(String str) {
    String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
    Pattern p = Pattern.compile(regExp);
    Matcher m = p.matcher(str);
    return m.find();
  }


}
