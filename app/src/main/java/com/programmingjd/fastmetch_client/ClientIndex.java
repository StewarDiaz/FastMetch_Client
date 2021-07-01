package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


public class ClientIndex extends AppCompatActivity {

    ListView listClientIndex;
    List<Client> CliList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_index);

        listClientIndex = findViewById(R.id.lvClients);
        GetListFromClient();
    }

    private void GetListFromClient(){
        Call<List<Client>> callingClient = RetrofitClient.getInstance().getMyApy().getClientList();
        callingClient.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                String msj = "";
                CliList = response.body();

                if(response.isSuccessful()){
                    for (int i = 0; i < CliList.size(); i++){
                        msj = CliList.get(i).getEmailAddressClient().toString();
                        Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_SHORT).show();
                    }
                    ClientsAdapter adapater = new ClientsAdapter(ClientIndex.this, CliList);
                    listClientIndex.setAdapter(adapater);
                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Error de red", Toast.LENGTH_SHORT).show();
            }
        });

    }
}