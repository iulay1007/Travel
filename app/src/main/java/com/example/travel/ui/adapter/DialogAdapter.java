package com.example.travel.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.R;
import com.example.travel.model.domain.SecondListResponse;
import com.example.travel.model.domain.TagList;
import com.example.travel.model.domain.Tagl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.InnerHolder> {
    private List<Tagl> mData=new ArrayList<>();
    public Set<String> checkBox = new HashSet<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_tag,parent,false);

        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Tagl bean = mData.get(position);
        holder.setData(bean);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    checkBox.add(bean.getTagName());
                }
                else
                {
                    if(checkBox.contains(bean.getTagName()))
                    checkBox.remove(bean.getTagName());
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        if(mData!=null)
            return mData.size();
        return 10;
    }

    public void setmData(List<Tagl> tagLists) {
        mData.clear();
        mData.addAll(tagLists);
        notifyDataSetChanged();
    }

    class InnerHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.dialog_tv)
        TextView textView;

        @BindView(R.id.dialog_checkbox)
        CheckBox checkBox;
         public InnerHolder(@NonNull  View itemView) {
             super(itemView);
             ButterKnife.bind(this,itemView);

         }

        public void setData(Tagl response){
         textView.setText(response.getTagName());
         }
    }
}
