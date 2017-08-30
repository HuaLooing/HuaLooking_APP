package hualookingcollege.manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13156 on 2017/8/19.
 */

public class Choose extends Activity {
    private TextView tv_home,tv_list;
    private List<Member> list;
    private RecyclerView recyclerView;

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose);
        initData();
        initView();
        bottom();


    }





    public void onStart(){
        super.onStart();
    }




    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_home);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        MyAdapter_home adpter = new MyAdapter_home(list,Choose.this, R.layout.card_main_2);
        recyclerView.setAdapter(adpter);
        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }

    private void initData() {

        list = new ArrayList<Member>();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection cn = DriverManager.getConnection("jdbc:mysql://192.168.123.32:3306/Demo", "root", "123456");
                    String sql = "select * from event";
                    Statement st = (Statement) cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        String Name = rs.getString("Name");
                        String Content = rs.getString("Content");
                        String Date=rs.getString("Date");
                        list.add(new Member(Name, Content,Date));
                    }
                    cn.close();
                    st.close();
                    rs.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    private void bottom() {
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_list = (TextView) findViewById(R.id.tv_list);


        tv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent C = new Intent();
                C.setClass(Choose.this, Card.class);
                startActivity(C);
                Choose.this.finish();
            }
        });
        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choose = new Intent();
                choose.setClass(Choose.this, Choose.class);
                startActivity(choose);
            }
        });
    }
}
