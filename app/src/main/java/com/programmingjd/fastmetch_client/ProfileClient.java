package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.models.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileClient extends AppCompatActivity {

    private ImageView imPictureProfilClient;
    private TextView tvNameProfileClient;
    private TextView tvSurnameProfileClient;
    String IdToUseClient;

    Client clientObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        IdToUseClient = intent.getStringExtra("IdToUseClient");
        setContentView(R.layout.activity_profile_client);

        tvNameProfileClient = findViewById(R.id.tvNameClientProfile);
        tvSurnameProfileClient = findViewById(R.id.tvSurnameClientProfile);
        startProfileDataClient();
    }

    private void startProfileDataClient(){
        getDataForProfileClient(Integer.valueOf(IdToUseClient));
    }


    private void getDataForProfileClient(int idToView){
        Call<Client> callingDataCLient = RetrofitClient.getInstance().getMyApy().getClientById(idToView);
        callingDataCLient.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                clientObj = response.body();
                if(response.isSuccessful()){
                    tvNameProfileClient.setText(clientObj.getNameClient());
                    tvSurnameProfileClient.setText(clientObj.getSurnameClient());
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }
}