package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.adapters.ServiceTypeAdapter;
import com.programmingjd.fastmetch_client.models.ServiceType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheServiceClient extends AppCompatActivity {

    Spinner spServiceType;
    List<ServiceType> serTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_service_client);

        spServiceType = findViewById(R.id.spTypeService);
        getListForSpinner();
    }

    private void getListForSpinner(){
        Call<List<ServiceType>> callingSerType = RetrofitClient.getInstance().getMyApy().getServiceTypeList();
        callingSerType.enqueue(new Callback<List<ServiceType>>() {
            @Override
            public void onResponse(Call<List<ServiceType>> call, Response<List<ServiceType>> response) {
                serTypeList = response.body();
                if(response.isSuccessful()){
                    ServiceTypeAdapter adapter = new ServiceTypeAdapter(TheServiceClient.this, serTypeList);
                    spServiceType.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<ServiceType>> call, Throwable t) {

            }
        });

    }


}