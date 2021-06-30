package com.programmingjd.fastmetch_client.ApiManager;

import com.programmingjd.fastmetch_client.models.Departament;
import com.programmingjd.fastmetch_client.models.Docentes_info;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://mechanicdsiewebservices.azurewebsites.net/";

    @GET("api/DepartamentsApi")
    Call<List<Departament>> getDepartamentList();


}
