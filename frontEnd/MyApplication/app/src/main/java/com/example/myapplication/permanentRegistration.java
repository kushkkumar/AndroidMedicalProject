package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class permanentRegistration extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://young-sea-56344.herokuapp.com/";
    private  String selectradio= "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.general_registration,container,false);

        final EditText name,bod,email,phone,address;
        final RadioButton male,female,other;

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);


        name=view.findViewById(R.id.pname);
        bod=view.findViewById(R.id.pbod);
        email=view.findViewById(R.id.pemail);
        phone=view.findViewById(R.id.pphone);
        address=view.findViewById(R.id.paddress);

        male=view.findViewById(R.id.m1);
        female=view.findViewById(R.id.m2);
        other=view.findViewById(R.id.m3);

        if(male.isChecked()){
            selectradio=male.getText().toString();
        }
        else if(female.isChecked()){
            selectradio=female.getText().toString();
        }
        else if(other.isChecked()){
            selectradio=other.getText().toString();
        }

             view.findViewById(R.id.permanent_form_submit_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap<String,String> map1=new HashMap<>();
                    map1.put("name",name.getText().toString());
                    map1.put("bod",bod.getText().toString());
                    map1.put("email",email.getText().toString());
                    map1.put("phone",phone.getText().toString());
                    map1.put("address",address.getText().toString());
                    map1.put("gender", selectradio);

                    Call<Void> call1=retrofitInterface.executePerRegistraion(map1);
                    call1.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            if(response.code()==201){
                                shiftActivity();
                                Toast.makeText(getContext(),"Inserted to the database",Toast.LENGTH_SHORT).show();
                            }
                            if(response.code()==401)
                            {
                                Toast.makeText(getContext(),"Not inserted",Toast.LENGTH_SHORT).show();

                            }


                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });




        return view;
    }

    private void shiftActivity() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new bookslotFragmentForPermentActivity()).addToBackStack(null).commit();

    }
}
