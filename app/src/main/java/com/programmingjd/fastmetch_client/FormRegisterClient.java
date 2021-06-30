package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class FormRegisterClient extends AppCompatActivity {

    EditText newClientName;
    EditText newClientSurname;
    EditText newClientNIdentification;
    EditText newClientGender;
    EditText newClientPhone;
    EditText newClientVehicule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register_client);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void startLoginClient(View v){
        Intent i = new Intent(this, Login_Client.class);
        startActivity(i);
    }

    public void ValidarCamposTxt(){
        newClientName.setError(null);
        newClientSurname.setError(null);
        newClientNIdentification.setError(null);
        newClientGender.setError(null);
        newClientPhone.setError(null);
        newClientVehicule.setError(null);

        String NameClient = newClientName.getText().toString();

    }
}