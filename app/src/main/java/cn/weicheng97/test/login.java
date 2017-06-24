package cn.weicheng97.test;

import android.app.Activity;

import android.app.backup.SharedPreferencesBackupHelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * Created by Weicheng on 2017/6/22.
 */
public class login extends Activity {
    private EditText userName;
    private EditText password;
    private CheckBox rem_pw;
    private Button btn_login;
    private String userNameValue;
    private String passwordValue;
    private SharedPreferences sp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        //获得实例对象
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        userName = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);
        rem_pw = (CheckBox) findViewById(R.id.checkBox_Remember);
        btn_login = (Button) findViewById(R.id.btn_login);

        //判断记住密码多选框的状态
        if (sp.getBoolean("ISCHECK", false)) {
            //设置默认是记录密码状态
            rem_pw.setChecked(true);
            userName.setText(sp.getString("USER_NAME", ""));
            password.setText(sp.getString("PASSWORD", ""));

        }

        // 登录监听事件  登录网络事件待完成
        btn_login.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    userNameValue = userName.getText().toString();
                    passwordValue = password.getText().toString();
                    //判断空
                    if (userNameValue.equals("")||passwordValue.equals(""))
                    {  
                        Toast.makeText(login.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
                    } 
                    //登录成功
                    else if(userNameValue.equals("15416117") && passwordValue.equals("271519"))
                    {
                        Toast.makeText(login.this, "登录成功",Toast.LENGTH_SHORT).show();
                    }
                    //校验失败
                    else
                    {
                        Toast.makeText(login.this, "用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                
                }
            });

        //监听记住密码多选框按钮事件
        rem_pw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                    if (rem_pw.isChecked()) {
                        System.out.println("记住密码已选中");
                        sp.edit().putBoolean("ISCHECK", true).commit();
                    } else {
                        System.out.println("记住密码没有选中");
                        sp.edit().putBoolean("ISCHECK", false).commit();
                    }
                }
            });

        //监听自动登录多选框事件

    }
}
