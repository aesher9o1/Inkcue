package com.example.android.inkcue.pref_and_requestes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.inkcue.R;

import java.util.List;

/**
 * Created by aesher on 8/10/2017.
 */

public class notification_adapter extends RecyclerView.Adapter<notification_adapter.MyViewHolder>{

    private Context myContext;
    private List<notification_provider> myProvider;


    public notification_adapter(Context mContext, List<notification_provider> mProvider){
        this.myContext= mContext;
        this.myProvider = mProvider;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        notification_provider pro= myProvider.get(position);
        holder.TITLE.setText(pro.getMyname());
        Glide.with(myContext).load(pro.getMyIMAGE()).fitCenter().into(holder.IMAGE);
    }

    @Override
    public int getItemCount() {
        return myProvider.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView TITLE;
        ImageView IMAGE;


        MyViewHolder(View itemView) {
            super(itemView);
            TITLE = (TextView) itemView.findViewById(R.id.Text);
            IMAGE = (ImageView) itemView.findViewById(R.id.Image);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
