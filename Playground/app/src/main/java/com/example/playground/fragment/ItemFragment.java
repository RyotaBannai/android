package com.example.playground.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.playground.R;

public class ItemFragment extends ListFragment {
    @Override
    public void setListAdapter(ListAdapter adapter) {
        super.setListAdapter(adapter);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.item_fragment,
                R.id.textview,
                RandomDataList.listData);
        setListAdapter(arrayAdapter);
    }
}
