package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.adapters.DepartamentAdapater;
import com.programmingjd.fastmetch_client.models.Client;
import com.programmingjd.fastmetch_client.models.Departament;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Client extends AppCompatActivity {

    List<Client> clieList;

    private EditText User;
    private EditText Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__client);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        User = findViewById(R.id.etClientLogin);
        Pass = findViewById(R.id.etClienteContraseña);


    }

    public void startRegisterClient(View v){
        Intent i = new Intent(this, FormRegisterClient.class);
        startActivity(i);
    }

    public void startMain(View v){
        loginClient();
    }

    private void loginClient(){
        Call<List<Client>> callinDep = RetrofitClient.getInstance().getMyApy().getClientList();
        callinDep.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if(response.isSuccessful()){
                    String res = "";
                    String userL = User.getText().toString();
                    String passL = Pass.getText().toString();
                    clieList = response.body();
                    for (int i = 0; i < clieList.size(); i++){
                        if(userL.equalsIgnoreCase(clieList.get(i).getEmailAddressClient()) &&
                                passL.equalsIgnoreCase(clieList.get(i).getIdentificationNumberClient())){
                            Intent intentOk = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intentOk);
                            DynamicToast.makeSuccess(getApplicationContext(), "Estas adentro", Toast.LENGTH_SHORT).show();
                            res = "ok";
                        }else{
                            res = "ko";
                        }
                    }
                    if (res.equalsIgnoreCase("ko")){
                        DynamicToast.makeError(getApplicationContext(), "No existes aqui o algun dato esta mal :(", Toast.LENGTH_SHORT).show();
                    }else{

                    }

                }
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });

    }
}