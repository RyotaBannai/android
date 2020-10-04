package com.example.playground;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public TextView detailView;

    public ViewHolder(View itemView) {
        super(itemView);
        /*
        * inflate した layout に対して findViewById
        * */
        titleView = (TextView) itemView.findViewById(R.id.title);
        detailView = (TextView) itemView.findViewById(R.id.detail);
    }
}
