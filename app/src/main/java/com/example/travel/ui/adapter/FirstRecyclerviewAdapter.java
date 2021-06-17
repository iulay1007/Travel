package com.example.travel.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travel.R;
import com.example.travel.model.domain.FirstListResponse;
import com.example.travel.model.domain.SecondListCategoryResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstRecyclerviewAdapter extends RecyclerView.Adapter<FirstRecyclerviewAdapter.InnerHolder>{

    List<FirstListResponse> data = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first,parent,false);

        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        FirstListResponse bean;
        if(data.size()!=0)
        {
            bean = data.get(position);
        holder.setData(bean);
        }
    }

   public void setData(List<FirstListResponse> responsesList){
        data.clear();
        data.addAll(responsesList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(data.size()!=0)
            return data.size();
        return 10;
    }

    class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.first_act_im)
        ImageView first_act_im;
        @BindView(R.id.first_act_title)
        TextView first_act_title;

        public InnerHolder(@NonNull  View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void setData(FirstListResponse response){
            Glide.with(itemView.getContext()).load(response.getImg()).into(first_act_im);
            first_act_title.setText(response.getTitle());
        }
    }
}
