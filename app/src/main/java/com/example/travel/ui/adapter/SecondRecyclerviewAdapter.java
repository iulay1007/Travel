package com.example.travel.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.R;
import com.example.travel.model.domain.SecondListCategoryResponse;
import com.example.travel.model.domain.SecondListResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondRecyclerviewAdapter extends RecyclerView.Adapter<SecondRecyclerviewAdapter.InnerHolder>{


    private List<SecondListCategoryResponse> cateData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_second,parent,false);

        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondRecyclerviewAdapter.InnerHolder holder, int position) {

           SecondListCategoryResponse bean = cateData.get(position);
           holder.setDataByCate(bean);
          holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onSecondListItemClickListener.onItemClick(bean);
               }
           });

    }

    public void setOnSecondListItemClickListener(onSecondListItemClickListener onSecondListItemClickListener) {
        this.onSecondListItemClickListener = onSecondListItemClickListener;
    }

    private onSecondListItemClickListener onSecondListItemClickListener= null;

    public void setDataByTag(List<SecondListCategoryResponse> secondListCategoryResponses) {
        cateData.clear();
        cateData.addAll(secondListCategoryResponses);
        notifyDataSetChanged();
    }

    public interface onSecondListItemClickListener{
        void onItemClick(SecondListCategoryResponse response);
    }

    @Override
    public int getItemCount() {
        if(cateData!=null)

         return cateData.size();
        return 10;
    }



    static class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_tv)
        TextView title_tv;
        @BindView(R.id.info_tv)
        TextView info_tv;
        @BindView(R.id.tag1_tv)
        TextView tag1;
        @BindView(R.id.tag2_tv)
        TextView tag2;
        @BindView(R.id.tag3_tv)
        TextView tag3;
        @BindView(R.id.tag4_tv)
        TextView tag4;

            public InnerHolder(@NonNull  View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);

            }

        public void setDataByCate(SecondListCategoryResponse response){
            List<TextView> tags= new ArrayList<>();
            tags.add(tag1);
            tags.add(tag2);
            tags.add(tag3);
            tags.add(tag4);
            title_tv.setText(response.getTitle());
            info_tv.setText(response.getInfo());
            int i;
            Log.d("tags",response.title+response.getTagList().toString());
            for( i = 0;i<response.getTagList().size()&&i<4;i++)
            {
                Log.d("tags",response.getTagList().get(i).getTagName());

                tags.get(i).setText(response.getTagList().get(i).getTagName());
            }
            if(i==4)
                return;
            while (i<4){
                tags.get(i).setVisibility(View.GONE);
                i++;
            }
        }
        }
}
