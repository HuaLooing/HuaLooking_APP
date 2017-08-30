package hualookingcollege.manage;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by 13156 on 2017/8/17.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        private List<Member> list;
        private Context context;
        private int resouce_id;
        private Statement st;
        private String sql;
        int flag=0;


//    public void initDB(){
//                try
//                {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    java.sql.Connection cn = null;
//                    cn = DriverManager.getConnection("jdbc:mysql://192.168.1.103:3306/Demo", "root", "123456");
//                    sql = "insert into practical_participation values ('11111111','15416119','1','1')";
//                    st = cn.createStatement();
//                    flag = st.executeUpdate(sql);
//                    cn.close();
//                    st.close();
//                } catch(ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                } catch(SQLException e)
//                {
//                    e.printStackTrace();
//                }
//        }
public class MyThread implements Runnable {
    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection cn = null;
            cn = DriverManager.getConnection("jdbc:mysql://192.168.123.32:3306/Demo", "root", "123456");
            sql = "insert into practical_participation values ('11111222','15416119','1','1')";
            st = (Statement) cn.createStatement();
            flag = st.executeUpdate(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





    public MyAdapter(List<Member> list, Context context, int resouce_id) {
        this.list = list;
        this.context = context;
        this.resouce_id = resouce_id;
        }

    @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resouce_id,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
        }
        @Override
       public void onBindViewHolder(MyHolder holder, final int position) {
        holder.Title.setText(list.get(position).getTitle());
        holder.SubTitle.setText(list.get(position).getSubTitle());
        holder.Date.setText(list.get(position).getDate());
        //holder.btn_join.setOnClickListener((View.OnClickListener) this);


//        holder.btn_join.setOnClickListener(new Button.OnClickListener(){
//            public void onClick(View v){
//                Toast toast = new Toast(context);
//                toast.setText("报名成功!");
//                toast.setDuration(Toast.LENGTH_SHORT);
//                toast.show();
//                //Toast.makeText(this,"报名成功！",Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.btn_join.setTag(position);
        holder.btn_join.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
               new Thread(new MyThread()).start();

                //initDB();
                if(flag!=0){
                     showInfo(position);
                }
            }
        });
        }
        @Override
        public int getItemCount() {
        return list.size();
        }

        public class MyHolder extends ViewHolder {
        private TextView Title;
        private TextView SubTitle;
        private TextView Date;
        private Button btn_join;

        public MyHolder(View view) {
        super(view);
        Title = (TextView) view.findViewById(R.id.tv_card_main_1_title);
        SubTitle = (TextView) view.findViewById(R.id.tv_card_main1_subtitle);
        Date=(TextView)view.findViewById(R.id.date);
        btn_join=(Button)view.findViewById(R.id.btn_card_main1_action1);
        }
       }

       public void showInfo(int position)  {
           new AlertDialog.Builder(this.context)
                   .setTitle("详情")
                   .setMessage("报名成功！")
                   .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                       }
                   })
                   .show();
    }



}
