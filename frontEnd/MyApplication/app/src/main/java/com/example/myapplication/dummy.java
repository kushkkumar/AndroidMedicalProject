package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dummy extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://young-sea-56344.herokuapp.com/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.dummy,container,false);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        view.findViewById(R.id.dummy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String,String> map1=new HashMap<>();
                map1.put("confirm",((EditText)view.findViewById(R.id.confirm)).getText().toString());

                Call<storeresultpermanent> call3=retrofitInterface.confirmRegistraion(map1);
                call3.enqueue(new Callback<storeresultpermanent>() {
                    @Override
                    public void onResponse(Call<storeresultpermanent> call, Response<storeresultpermanent> response) {
                        if(response.code()==210){
                            storeresultpermanent storeresultpermanent=response.body();

                            setter setter=new setter();
                            setter.setPtid(storeresultpermanent.getPpid());

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new previewSamplePermanentQrActivity()).commit();

                            Toast.makeText(getContext(),""+storeresultpermanent.getPpid(),Toast.LENGTH_SHORT).show();
                        }
                        if(response.code()==410){
                            Toast.makeText(getContext(),"error in database",Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<storeresultpermanent> call, Throwable t) {

                        Toast.makeText(getContext(),"error"+t,Toast.LENGTH_LONG).show();

                    }
                });
            }
        });





        return view;
    }
}
