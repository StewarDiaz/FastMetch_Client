package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.models.Client;

import java.util.regex.Pattern;

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
                    DynamicToast.makeSuccess(getApplicationContext(), "Se registro Correctamente a " + response.body().getNameClient(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                DynamicToast.makeError(getApplicationContext(), "Error de red.", Toast.LENGTH_SHORT).show();
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
        String NameC = newClientName.getText().toString();
        String SurnameC = newClientSurname.getText().toString();
        String IdentifyC = newClientNIdentification.getText().toString();
        String GenderC = newClientGender.getText().toString();
        String PhoneC = newClientPhone.getText().toString();
        String EmailC = newClientEmail.getText().toString();
        String VehiculeC = newClientName.getText().toString();

        if(NameC.isEmpty()){
            DynamicToast.makeWarning(this, "El nombre esta vacio.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(SurnameC.isEmpty()){
            DynamicToast.makeWarning(this, "El apellid esta vacio.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(IdentifyC.isEmpty()){
            DynamicToast.makeWarning(this, "El numero de identificacion esta vacio.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(GenderC.isEmpty()){
            DynamicToast.makeWarning(this, "El genero esta vacio.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(PhoneC.isEmpty()){
            DynamicToast.makeWarning(this, "El telefono esta vacio.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(EmailC.isEmpty()){
            DynamicToast.makeWarning(this, "El email esta vacio.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(VehiculeC.isEmpty()){
            DynamicToast.makeWarning(this, "El vehiculo esta vacio.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isValidEmail(EmailC)){
            DynamicToast.makeWarning(this, "Correo incorrecto.", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private Boolean isValidEmail(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


}