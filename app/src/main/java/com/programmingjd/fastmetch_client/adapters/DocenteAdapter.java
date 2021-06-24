package com.programmingjd.fastmetch_client.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.programmingjd.fastmetch_client.R;
import com.programmingjd.fastmetch_client.models.Docentes_info;

import java.util.List;

public class DocenteAdapter extends BaseAdapter {

    protected Activity activity;
    protected List<Docentes_info> myDocente;

    public DocenteAdapter(Activity activity, List<Docentes_info> docenteList){
        this.activity = activity;
        this.myDocente = docenteList;
    }

    @Override
    public int getCount() {
        return myDocente.size();
    }

    @Override
    public Object getItem(int position) {
        return myDocente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.docente_item, null);
        }

        Docentes_info dis_info = myDocente.get(position);

        TextView nameDistilleries = v.findViewById(R.id.tvNombreCompleto);
        nameDistilleries.setText(dis_info.getNombreCompleto());

        return v;
    }
}
