package com.programmingjd.fastmetch_client;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;
import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.adapters.ServiceTypeAdapter;
import com.programmingjd.fastmetch_client.models.ServiceType;
import com.programmingjd.fastmetch_client.models.TheService;

import org.json.JSONException;

import java.math.BigDecimal;
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

    Button btnPay;
    EditText Tmonto;
    String paymentAmount;

    //forPAy
    public static final int PAYPAL_REQUEST_CODE = 123;
    private static final String CONFIG_CLIENT_ID = "AUBXXgKyik6lvgxH6ihhnsb08FB5j7djUq5yL_Tbsa7szx_k9qxhHXfU5si_CKyb2GDpnfDRklYHw63g";


    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(CONFIG_CLIENT_ID);

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

        Intent intentPay = new Intent(this, PayPalService.class);
        intentPay.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intentPay);
    }

    private void getListForSpinner() {
        Call<ArrayList<ServiceType>> callingSerType = RetrofitClient.getInstance().getMyApy().getServiceTypeArrayList();
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
            Call<ArrayList<TheService>> callingTheServiceArray = RetrofitClient.getInstance().getMyApy().getTheServiceArrayList();
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

    private void stratPay(View v){
        getPayment();
    }

    private void getPayment() {
        paymentAmount = Tmonto.getText().toString();

        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)), "USD", "Simplified Coding Fee",
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        startActivity(new Intent(this, ConfirmationPay.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

}