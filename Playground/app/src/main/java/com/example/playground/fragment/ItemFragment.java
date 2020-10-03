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

        /*
        * Render item_fragment
        * */
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                R.layout.item_fragment,
//                R.id.textview,
//                RandomDataList.listData);

        /*
         * Render relative_item_fragment
         * */
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                R.layout.relative_item_fragment,
//                R.id.textView1);
//
//        arrayAdapter.add("Daredevil1");
//        arrayAdapter.add("Daredevil2");
//
//        setListAdapter(arrayAdapter);

        /*
         * Render constraint_item_fragment
         * */
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.constraint_item_fragment,
                R.id.textView1);

        arrayAdapter.add("Daredevil1");
        arrayAdapter.add("Daredevil2");

        setListAdapter(arrayAdapter);
    }
}
