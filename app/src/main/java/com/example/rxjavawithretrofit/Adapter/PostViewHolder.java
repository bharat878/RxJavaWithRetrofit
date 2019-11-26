package com.example.rxjavawithretrofit.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavawithretrofit.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView title, subtitle, content;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.txt1);
        subtitle = itemView.findViewById(R.id.txt2);
        content = itemView.findViewById(R.id.txt3);


    }
}
