package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.adapters.ServiceTypeAdapter;
import com.programmingjd.fastmetch_client.models.ServiceType;
import com.programmingjd.fastmetch_client.models.TheService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheServiceClient extends AppCompatActivity {

    Spinner spServiceType;
    Spinner spTheService;
    EditText newDescriptionToService;
    int useId;

    ArrayList<ServiceType> serTypeList = new ArrayList<>();
    ArrayList<TheService> theServList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_service_client);

        spServiceType = findViewById(R.id.spTypeService);
        spTheService = findViewById(R.id.spTheServiceArray);
        newDescriptionToService = findViewById(R.id.edDescrpcionTheService);
        getListForSpinner();
    }

    private void getListForSpinner() {
        Call<ArrayList<ServiceType>> callingSerType = RetrofitClient.getInstance().getMyApy().getServiceTypeList();
        callingSerType.enqueue(new Callback<ArrayList<ServiceType>>() {
            @Override
            public void onResponse(Call<ArrayList<ServiceType>> call, Response<ArrayList<ServiceType>> response) {
                serTypeList = response.body();
                if (response.isSuccessful()) {//
                    ArrayAdapter<ServiceType> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, serTypeList);
                    spServiceType.setAdapter(adapter);

                    spServiceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            useId = Integer.valueOf(serTypeList.get(position).getIdServiceType());
                            String msj = "el id de " + spServiceType.getItemAtPosition(position).toString() + " es " + useId;
                            //Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_LONG).show();
                            getArrayforTheService();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ServiceType>> call, Throwable t) {
                DynamicToast.makeError(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getArrayforTheService() {
        String pru = "" + useId;
        Toast.makeText(getApplicationContext(), pru, Toast.LENGTH_SHORT).show();

        if (useId != 0) {
            Call<ArrayList<TheService>> callingTheServiceArray = RetrofitClient.getInstance().getMyApy().getTheServiceList();
            callingTheServiceArray.enqueue(new Callback<ArrayList<TheService>>() {
                @Override
                public void onResponse(Call<ArrayList<TheService>> call, Response<ArrayList<TheService>> response) {
                    theServList = response.body();
                    if (response.isSuccessful()) {
                        theServList.removeIf(x->x.getIdServiceType() != useId);

                        ArrayAdapter<TheService> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, theServList);
                        spTheService.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<TheService>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error de red", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}