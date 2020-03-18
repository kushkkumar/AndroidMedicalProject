package com.example.myapplication;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/temppatientRegistration")
    Call<Void> executetempResgitration(@Body HashMap<String,String> map);

    @POST("/permanentRegistraion")
    Call<Void> executePerRegistraion(@Body HashMap<String,String> map);


    @POST("/bookSlotPermanent")
    Call<permanentPateintResult> executebookSlotPermanent(@Body HashMap<String,String> map);

    @POST("/confirmPermanent")
    Call<storeresultpermanent> confirmRegistraion(@Body HashMap<String,String> map);

    @GET("doctordetails")
    Call<List<doctordetailresult>> getdoctorDetails();

}
