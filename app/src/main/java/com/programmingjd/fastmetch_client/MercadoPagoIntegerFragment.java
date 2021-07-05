package com.programmingjd.fastmetch_client;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MercadoPagoIntegerFragment extends Fragment {

    public static MercadoPagoIntegerFragment newInstance(String param1, String param2) {
        MercadoPagoIntegerFragment fragment = new MercadoPagoIntegerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mercado_pago_integer, container, false);
    }
}