package com.howietian.shoujiwancai.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import com.howietian.shoujiwancai.R;
import com.howietian.shoujiwancai.entities.User;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity {
  @Bind(R.id.btn_register) Button btn_register;
  @Bind(R.id.et_name) EditText et_name;
  @Bind(R.id.et_phoneNum) EditText et_phoneNum;
  @Bind(R.id.et_pwd) EditText et_pwd;
  @Bind(R.id.tv_login) TextView tv_login;
  LinearLayout dialog_view;
  MaterialEditText met_phoneNum;
  MaterialEditText met_code;
  TextView dialog_tv;
  String name;
  String phoneNum;
  String pwd;
  MyCounter timer;
  boolean isFinish = true;//判断时间是否倒计时结束的标志
  final User user = new User();
  boolean flag;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void setContentViews() {
    setContentView(R.layout.activity_register);
  }

  @Override public void initViews() {

  }

  //在activity的这个生命周期里实现加载对话框的操作
  @Override protected void onResume() {
    super.onResume();
    dialog_view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_phone, null);
    met_phoneNum = (MaterialEditText) dialog_view.findViewById(R.id.dialog_phoneNum);
    met_code = (MaterialEditText) dialog_view.findViewById(R.id.dialog_code);
    dialog_tv = (TextView) dialog_view.findViewById(R.id.dialog_tv);
    //对话框里的按钮的点击事件，获取验证码
    dialog_tv.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        //获取验证码的方法
        requestSMSCode();
      }
    });
  }

  @Override public void initListeners() {

  }

  @Override public void initDatas() {

  }

  ////判断输入的合法性
  public boolean isCheckData() {
    name = et_name.getText().toString();
    phoneNum = et_phoneNum.getText().toString();
    pwd = et_pwd.getText().toString();
    //判断3个输入是否为空
    if (TextUtils.isEmpty(name)) {
      et_name.setError("名字不能为空");
      return false;
    } else if (TextUtils.isEmpty(phoneNum)) {
      et_phoneNum.setError("手机号码不能为空");
      return false;
    } else if (TextUtils.isEmpty(pwd)) {
      et_pwd.setError("密码不能为空");
      return false;
    } else if (!isStringFormatCorrect(name + pwd)) {
      showToast("只允许中文、字母、数字、下划线");
      return false;
    } else if (pwd.length() < 6) {
      showToast("密码长度至少为6位");
      return false;
    } else if (!isPhoneNum(phoneNum)) {
      showToast("手机号码错误");
      return false;
    } else if (isExist()) {
      showToast("该手机号码已经被注册");
      return false;
    } else if (!isFinish) {
      showToast("请等待60S后再试");
      return false;
    }
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

  //显示对话框
  public void showDialog() {

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("验证手机号");
    builder.setView(dialog_view);
    met_phoneNum.setText(phoneNum);
    builder.setPositiveButton("完成注册", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialogInterface, int i) {
        //验证验证码+注册功能
        if (verifyCode()) {
          userRegister();
        }
      }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  //
  //注册按钮点击事件
  @OnClick(R.id.btn_register) public void register(View view) {
    if (isCheckData()) {
      requestSMSCode();
      showDialog();
    }
  }

  //跳转登录界面
  @OnClick(R.id.tv_login) public void login() {
    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
    startActivity(intent);
  }

  //请求发送验证码
  private void requestSMSCode() {

    timer = new MyCounter(60000, 1000);
    timer.start();

    BmobSMS.requestSMSCode(phoneNum, "标准模板", new QueryListener<Integer>() {
      @Override public void done(Integer integer, BmobException e) {
        if (e == null) {// 验证码发送成功
          showToast("验证码发送成功");
        } else {//如果验证码发送错误，可停止计时
          showToast("验证码发送失败" + e.toString());
          timer.cancel();
        }
      }
    });
  }

  //验证验证码的方法

  private boolean verifyCode() {
    final String phone = met_phoneNum.getText().toString();
    String code = met_code.getText().toString();
    if (TextUtils.isEmpty(phone)) {
      met_phoneNum.setError("手机号码不能为空");
      return false;
    }

    if (TextUtils.isEmpty(code)) {
      met_code.setError("验证码不能为空");
      return false;
    }

    BmobSMS.verifySmsCode(phone, code, new UpdateListener() {
      @Override public void done(BmobException e) {
        if (e == null) {
          showToast("手机号码已验证");
         flag = true;

        } else {
          showToast("验证失败：code=" + e.getErrorCode() + "，错误描述：" + e.getLocalizedMessage());
          flag = false;
        }
      }
    });
    return flag;
  }

  //注册方法
  public void userRegister() {
    final ProgressDialog progress = new ProgressDialog(RegisterActivity.this);
    progress.setMessage("正在登录中...");
    progress.setCanceledOnTouchOutside(false);
    progress.show();
    user.setUsername(phoneNum);
    user.setNick(name);
    user.setPassword(pwd);
    user.signUp(new SaveListener<User>() {
      @Override public void done(User user, BmobException e) {
        if (e == null) {
          progress.dismiss();
          showToast("注册成功---用户名：" + user.getUsername() + "，年龄：" + user.getAge());

          Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
          startActivity(intent);
          finish();
        } else {
          showToast("验证失败：code=" + e.getErrorCode() + "，错误描述：" + e.getLocalizedMessage());
          progress.dismiss();
        }
      }
    });
  }

  //自定义的计数器
  class MyCounter extends CountDownTimer {
    public MyCounter(long millisInFuture, long countDownInterval) {
      super(millisInFuture, countDownInterval);
    }

    //倒计时的的方法
    @Override public void onTick(long l) {
      dialog_tv.setText("已发送(" + l / 1000 + ")S");
      dialog_tv.setClickable(false);
      isFinish = false;
    }
    @Override public void onFinish() {
      dialog_tv.setClickable(true);
      dialog_tv.setText("获得验证码");
      isFinish = true;
    }
  }
}






