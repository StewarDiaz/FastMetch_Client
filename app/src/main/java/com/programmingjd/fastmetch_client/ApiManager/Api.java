package com.programmingjd.fastmetch_client.ApiManager;

import com.programmingjd.fastmetch_client.models.Client;
import com.programmingjd.fastmetch_client.models.Departament;
import com.programmingjd.fastmetch_client.models.Mechanic;
import com.programmingjd.fastmetch_client.models.ServiceType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://mechanicdsiewebservices.azurewebsites.net/";

    @GET("api/DepartamentsApi")
    Call<List<Departament>> getDepartamentList();

    @GET("api/MechanicsApi")
    Call<List<Mechanic>> getMechanicList();

    @GET("api/ClientsApi")
    Call<List<Client>> getClientList();

    @GET("api/ServiceTypesApi")
    Call<List<ServiceType>> getServiceTypeList();

    @POST("api/ClientsApi")
    @FormUrlEncoded
    Call<Client> postClientData(@Field("NameClient") String NameClient,
                                @Field("SurnameClient") String SurnameClient,
                                @Field("IdentificationNumberClient") String IdentificationNumberClient,
                                @Field("GenderClient") String GenderClient,
                                @Field("PhoneClient") String PhoneClient,
                                @Field("emailAddressClient") String emailClient,
                                @Field("VehicleClient") String VehicleClient,
                                @Field("IdCity") int IdCity,
                                @Field("IdTypeDocument") int IdTypeDocument);

}
