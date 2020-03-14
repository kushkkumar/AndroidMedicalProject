package com.example.myapplication;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/temppatientRegistration")
    Call<Void> executetempResgitration(@Body HashMap<String,String> map);

    @POST("/permanentRegistraion")
    Call<Void> executePerRegistraion(@Body HashMap<String,String> map);


}
