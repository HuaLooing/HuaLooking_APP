package hualookingcollege.manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static hualookingcollege.manage.R.layout.login;


/**
 * Created by 13156 on 2017/7/27.
 */

public class Login_Activity extends Activity {


    private EditText Student_ID;
    private EditText Password;
    private java.sql.Connection cn;
    private Button btn;
    private String Stu_ID;
    private String Pasw;
    int flag = 0;
    private ResultSet rs;
    private Statement st;
    private String loginsql;




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(login);
        btn = (Button) findViewById(R.id.btn_login);
        Student_ID = (EditText) findViewById(R.id.input_username);
        Password = (EditText) findViewById(R.id.input_password);


        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    cn = DriverManager.getConnection("jdbc:mysql://192.168.123.32:3306/Demo", "root", "123456");
                    loginsql = "select * from student";
                    try {
                        st = (Statement) cn.createStatement();
                       rs = st.executeQuery(loginsql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    while (rs.next()) {
                        Stu_ID = rs.getString("Student_ID");
                        Pasw = rs.getString("Password");
                        if (Stu_ID.equals(Student_ID.getText().toString()) && Pasw.equals(Password.getText().toString())) {
                            flag++;
                        }
                    }
                    cn.close();
                    st.close();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (flag == 1) {
                    Intent C = new Intent();
                    C.setClass(Login_Activity.this, Card.class);
                    startActivity(C);
                    Login_Activity.this.finish();
                } else {
                    Toast.makeText(Login_Activity.this, "学号或密码错误！请重新输入！", Toast.LENGTH_SHORT).show();
                    //flag = 0;
                    Login_Activity.this.finish();
                    Intent C = new Intent();
                    C.setClass(Login_Activity.this, Login_Activity.class);
                    startActivity(C);
                }

            }
        });


    }








//    public void login(View v) {
//        try {
//            while (rs.next()) {
//                Stu_ID = rs.getString("Student_ID");
//                Pasw = rs.getString("Password");
//                if (Stu_ID.equals(Student_ID.getText().toString()) && Pasw.equals(Password.getText().toString())) {
//                    flag++;
//                }
//            }
//            cn.close();
//            st.close();
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (flag == 1) {
//            Intent C = new Intent();
//            C.setClass(this, Card.class);
//            startActivity(C);
//        } else {
//            Toast.makeText(this, "学号或密码错误！请重新输入！", Toast.LENGTH_SHORT).show();
//            flag = 0;
//        }
//    }

}



