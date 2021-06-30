package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.adapters.DepartamentAdapater;
import com.programmingjd.fastmetch_client.models.Departament;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartamentClient extends AppCompatActivity {

    ListView listDepartament;
    List<Departament> DepList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departament);
        listDepartament = findViewById(R.id.lvDepartamen);

        getFromDepartament();
    }

    private void getFromDepartament(){
        Call<List<Departament>> callinDep = RetrofitClient.getInstance().getMyApy().getDepartamentList();
        callinDep.enqueue(new Callback<List<Departament>>() {
            @Override
            public void onResponse(Call<List<Departament>> call, Response<List<Departament>> response) {
                if(response.isSuccessful()){
                    DepList = response.body();
                    DepartamentAdapater adapater = new DepartamentAdapater(DepartamentClient.this, DepList);
                    listDepartament.setAdapter(adapater);
                }
            }

            @Override
            public void onFailure(Call<List<Departament>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}