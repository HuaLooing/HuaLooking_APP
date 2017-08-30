package hualookingcollege.manage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13156 on 2017/7/29.
 */

public class Card extends Activity {
    private Button btn_join;
    private List<Member> list;
    private RecyclerView recyclerView;
    private TextView tv_home,tv_list;



    public void onBackPressed () {
        super.onBackPressed();
        Intent Main = new Intent();
        Main.setClass(Card.this, Login_Activity.class);
        startActivity(Main);
        Card.this.finish();
    }

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);
        initData();
        initView();
        bottom();
//        new Thread(new Runnable() {
//
//           list= new ArrayList<>();
//            @Override
//            public void run() {
//                try {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    java.sql.Connection cn= DriverManager.getConnection("jdbc:mysql://192.168.1.103:3306/Demo","root","123456");
//                    String sql="select * from event";
//                    Statement st=(Statement)cn.createStatement();
//                    ResultSet rs=st.executeQuery(sql);
//                    while(rs.next()){
//                        String Name=rs.getString("Name");
//                        String Content=rs.getString("Content");
//                        list.add(new Member(flag,Name));
//                        list.add(new Member(flag,Content));
//                        //Title.setText(Name);
//                        //SubTitle.setText(Content);
//                    }
//                    cn.close();
//                    st.close();
//                    rs.close();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();



    }


    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        MyAdapter adpter = new MyAdapter(list,Card.this, R.layout.card_main_1);
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
            tv_home=(TextView)findViewById(R.id.tv_home);
            tv_list=(TextView)findViewById(R.id.tv_list);


            tv_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent C = new Intent();
                    C.setClass(Card.this, Card.class);
                    startActivity(C);
                    Card.this.finish();
                }
            });
            tv_home.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Card.this.finish();
                    Intent choose=new Intent();
                    choose.setClass(Card.this, Choose.class);
                    startActivity(choose);
                }
            });


        }


   }

