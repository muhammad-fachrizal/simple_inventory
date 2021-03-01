package com.simpleinventory.adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.simpleinventory.R;
import com.simpleinventory.data.DataBenda;

import java.util.List;


public class AdapterBenda extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataBenda> item;

    public AdapterBenda(Activity activity, List<DataBenda> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_isi_spinner_benda, null);

        TextView text_nama_benda = (TextView) convertView.findViewById(R.id.text_nama_benda);

        DataBenda data;
        data = item.get(position);

        text_nama_benda.setText(data.getNama_Barang());

        return convertView;
    }
}