package com.programmingjd.fastmetch_client.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.programmingjd.fastmetch_client.R;
import com.programmingjd.fastmetch_client.models.Departament;

import java.util.List;

public class DepartamentAdapater extends BaseAdapter {

    protected Activity activity;
    protected List<Departament> myDepartament;

    public DepartamentAdapater(Activity activity, List<Departament> departamentList){
        this.activity = activity;
        this.myDepartament = departamentList;
    }

    @Override
    public int getCount() {
        return myDepartament.size();
    }

    @Override
    public Object getItem(int position) {
        return myDepartament.get(position);
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
            v = inf.inflate(R.layout.departament_item, null);
        }

        Departament dep_info = myDepartament.get(position);

        TextView nameDepartament = v.findViewById(R.id.tvNameDepartament);
        nameDepartament.setText(dep_info.getNameDepartament());

        return v;
    }
}
