package com.programmingjd.fastmetch_client.adapters;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.programmingjd.fastmetch_client.R;
import com.programmingjd.fastmetch_client.models.Client;

import java.util.List;

public class ClientsAdapter extends BaseAdapter {

    protected Activity activity;
    protected List<Client> myClient;

    public ClientsAdapter(Activity activity, List<Client> clientList){
        this.activity = activity;
        this.myClient = clientList;
    }

    @Override
    public int getCount() {
        return myClient.size();
    }

    @Override
    public Object getItem(int position) {
        return myClient.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getLayoutInflater().getContext()
                                                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_client, null);
        }

        Client cli_info = myClient.get(position);

        TextView fullNameClient = v.findViewById(R.id.tvFullNameClient);
        fullNameClient.setText(cli_info.getNameClient() + " " + cli_info.getSurnameClient() + ".");

        return v;
    }
}
