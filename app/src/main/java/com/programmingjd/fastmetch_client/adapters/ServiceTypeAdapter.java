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
import com.programmingjd.fastmetch_client.models.ServiceType;

import java.util.List;

public class ServiceTypeAdapter extends BaseAdapter {

    protected Activity activity;
    protected List<ServiceType> myServiceType;

    public ServiceTypeAdapter(Activity activity, List<ServiceType> serviceTypeList){
        this.activity = activity;
        this.myServiceType = serviceTypeList;
    }

    @Override
    public int getCount() {
        return myServiceType.size();
    }

    @Override
    public Object getItem(int position) {
        return myServiceType.get(position);
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

        ServiceType serType_info = myServiceType.get(position);

        TextView nameServiceType = v.findViewById(R.id.tvNameDepartament);
        nameServiceType.setText(serType_info.getNameServiceType());

        return v;
    }
}
