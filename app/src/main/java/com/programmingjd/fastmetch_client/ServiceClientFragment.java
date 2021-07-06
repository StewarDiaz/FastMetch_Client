package com.programmingjd.fastmetch_client;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.adapters.ClientsAdapter;
import com.programmingjd.fastmetch_client.adapters.DepartamentAdapater;
import com.programmingjd.fastmetch_client.models.Client;
import com.programmingjd.fastmetch_client.models.Departament;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ServiceClientFragment extends Fragment {

    List<Client> CliList;
    ListView listClientIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_client, container, false);

        listClientIndex = v.findViewById(R.id.lvClients);

        return v;
    }

    private void GetListFromClient(){
        Call<List<Client>> callingClient = RetrofitClient.getInstance().getMyApy().getClientList();
        callingClient.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if(response.isSuccessful()){
                    CliList = response.body();
                    String msj = "";
                    for (Client obClient: CliList) {


                    }
                    //aun falta hacerlo y se estalla al intentar mostrar la listView en
                    //este fragmente
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast.makeText(getContext(), "error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setListAdapter(ListAdapter adapter){

    }

}