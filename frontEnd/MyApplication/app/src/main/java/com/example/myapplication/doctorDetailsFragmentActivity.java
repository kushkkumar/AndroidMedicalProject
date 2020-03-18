package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class doctorDetailsFragmentActivity extends Fragment {

    RecyclerView recyclerView;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://young-sea-56344.herokuapp.com/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.doctor_details_fragment,container,false);


        recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface=retrofit.create(RetrofitInterface.class);

        Call<List<doctordetailresult>> listCall=retrofitInterface.getdoctorDetails();
        listCall.enqueue(new Callback<List<doctordetailresult>>() {
            @Override
            public void onResponse(Call<List<doctordetailresult>> call, Response<List<doctordetailresult>> response) {

                show(response.body());

            }

            @Override
            public void onFailure(Call<List<doctordetailresult>> call, Throwable t) {

            }
        });




        return view;
    }

    private void show(List<doctordetailresult> response) {

        recyclerAdapter recyclerAdapter=new recyclerAdapter(response);
        recyclerView.setAdapter(recyclerAdapter);




    }
}
