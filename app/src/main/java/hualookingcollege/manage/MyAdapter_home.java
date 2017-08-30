package hualookingcollege.manage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 13156 on 2017/8/30.
 */


public class MyAdapter_home extends RecyclerView.Adapter<MyAdapter_home.MyHolder> {

    private List<Member> list;
    private Context context;
    private int resouce_id;




    public MyAdapter_home(List<Member> list, Context context, int resouce_id) {
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
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView Title;
        private TextView SubTitle;
        private TextView Date;

        public MyHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.tv_card_main_1_title);
            SubTitle = (TextView) view.findViewById(R.id.tv_card_main1_subtitle);
            Date=(TextView)view.findViewById(R.id.date);
        }
    }
}
