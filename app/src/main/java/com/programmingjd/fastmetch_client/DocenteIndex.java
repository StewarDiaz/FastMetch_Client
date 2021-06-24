package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.adapters.DocenteAdapter;
import com.programmingjd.fastmetch_client.models.Docentes_info;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocenteIndex extends AppCompatActivity {

    ListView listDocente;
    List<Docentes_info> DocList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_index);

        listDocente = findViewById(R.id.lvDocentesLV);
        getFromDocentes();
    }

    private void getFromDocentes(){
        Call<List<Docentes_info>> callingDocent = RetrofitClient.getInstance().getMyApy().getDocentes_info_list();
        callingDocent.enqueue(new Callback<List<Docentes_info>>() {
            @Override
            public void onResponse(Call<List<Docentes_info>> call, Response<List<Docentes_info>> response) {
                if(response.isSuccessful()){
                    DocList = response.body();
                    DocenteAdapter adapter = new DocenteAdapter(DocenteIndex.this, DocList);
                    listDocente.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Docentes_info>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error con la conexion", Toast.LENGTH_SHORT).show();
            }
        });

    }

}