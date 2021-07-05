package com.programmingjd.fastmetch_client;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.programmingjd.fastmetch_client.ApiManager.RetrofitClient;
import com.programmingjd.fastmetch_client.models.Client;
import com.programmingjd.fastmetch_client.models.Mechanic;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MapsClientFragment extends Fragment implements View.OnClickListener {

    Button btnUpdate;
    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;

    LatLng latLng;
    LatLng latLngMech;

    List<Mechanic> MechList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_maps_client, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        Dexter.withContext(getActivity().getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        update(viewRoot);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        //when permission denied
                        //Request permission
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                        Toast.makeText(getActivity(), "No permissions: 44", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();

        return viewRoot;
    }

    private void getCurrentLocation() {


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    //sync map
                    //mapFragment.getMapAsync(getCallback());
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    mapFragment.getMapAsync(callback);
                }else {
                    Log.d("status", "Location is : "+ null);
                    Toast.makeText(getActivity(), "Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public OnMapReadyCallback getCallback() {
        return callback;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mapFragment != null) {
            //mapFragment.getMapAsync(callback);
        }
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            addMarker(googleMap);
        }
    };

    private void addMarker(GoogleMap googleMap){
        CameraPosition positionCameraPosition = new CameraPosition.Builder().target(latLng).zoom(12).bearing(0).tilt(0).build();
        CameraUpdate cam3 = CameraUpdateFactory.newCameraPosition(positionCameraPosition);
        googleMap.animateCamera(cam3);

        googleMap.addMarker(new MarkerOptions().position(latLng).title("ubicacion actual"));

        // Foreach */ para el retrofit de cargar los mecanicos en el mapa
        Call<List<Mechanic>> callingMech = RetrofitClient.getInstance().getMyApy().getMechanicList();
        callingMech.enqueue(new Callback<List<Mechanic>>() {
            @Override
            public void onResponse(Call<List<Mechanic>> call, Response<List<Mechanic>> response) {
                MechList = response.body();
                if(response.isSuccessful()){
                    for (Mechanic obMech: MechList) {
                        latLngMech = new LatLng(Double.parseDouble(obMech.getLatitudeMechanic().toString()),
                                                Double.parseDouble(obMech.getLongitudeMechanic().toString()));
                        googleMap.addMarker(new MarkerOptions().position(latLngMech).title(obMech.getNameMechanic())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        //icono home repair
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Mechanic>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de red", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void update(View viewRoot) {
        btnUpdate = viewRoot.findViewById(R.id.btnUpdateLocation);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getCurrentLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }
}