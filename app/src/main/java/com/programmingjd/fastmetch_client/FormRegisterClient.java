package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.models.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRegisterClient extends AppCompatActivity {

    Client client;

    EditText newClientName;
    EditText newClientSurname;
    EditText newClientNIdentification;
    EditText newClientGender;
    EditText newClientPhone;
    EditText newClientEmail;
    EditText newClientVehicule;
    Spinner newClientIdCity;
    Spinner newClientIdTypeDocument;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register_client);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        newClientName = findViewById(R.id.etNewNameClient);
        newClientSurname = findViewById(R.id.etNewSurnameClient);
        newClientNIdentification = findViewById(R.id.etNewIdentificationClient);
        newClientGender = findViewById(R.id.etNewGenderClient);
        newClientPhone = findViewById(R.id.etNewPhoneClient);
        newClientVehicule = findViewById(R.id.edtNewVehiculeClient);
        newClientIdCity = findViewById(R.id.spNewIDCityClient);
        newClientIdTypeDocument = findViewById(R.id.spNewTypeDocumentClient);
        newClientEmail = findViewById(R.id.etNewClientEmail);
        btnSend = findViewById(R.id.btnSaveNewClient);


    }

    private void postDataForClient(String nameClient, String surnmaeClient, String identificationClient, String genderClient,
                                   String phoneClient, String emailClient, String vehiculeClient, int idCityClient, int idTypeDocumentClient){
        Call<Client> sendingClient = RetrofitClient.getInstance().getMyApy().postClientData(nameClient, surnmaeClient, identificationClient, genderClient,
                phoneClient , emailClient, vehiculeClient, idCityClient, idTypeDocumentClient);
        sendingClient.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Se registro Correctamente a " + response.body().getNameClient(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void startLoginClient(View v){
        Intent i = new Intent(this, Login_Client.class);
        startActivity(i);
    }

    public void startNewClient(View v) {
        String NameClient = newClientName.getText().toString();
        String SurnameClient = newClientSurname.getText().toString();
        String IdentifyClient = newClientNIdentification.getText().toString();
        String GenderClient = newClientGender.getText().toString();
        String PhoneClient = newClientPhone.getText().toString();
        String EmailClient = newClientEmail.getText().toString();
        String VehiculeClient = newClientName.getText().toString();
        int idCityClient = 1;
        int idTypeDocument = 1;

        postDataForClient(NameClient, SurnameClient, IdentifyClient, GenderClient, PhoneClient, EmailClient, VehiculeClient, idCityClient, idTypeDocument);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



    public void ValidarCamposTxt(){
        newClientName.setError(null);
        newClientSurname.setError(null);
        newClientNIdentification.setError(null);
        newClientGender.setError(null);
        newClientPhone.setError(null);
        newClientVehicule.setError(null);

    }


}