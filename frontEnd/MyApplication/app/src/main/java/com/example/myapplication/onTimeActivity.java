package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class onTimeActivity extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://young-sea-56344.herokuapp.com/";
    private  String selectradio= "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.one_time_patient_registration,container,false);


        final EditText name,bod,email,phone,address;
        final RadioButton male,female,other;

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);



         name=view.findViewById(R.id.tpname);
         bod=view.findViewById(R.id.tpbod);
        email=view.findViewById(R.id.tpemail);
        phone=view.findViewById(R.id.tpphone);
        address=view.findViewById(R.id.tpaddress);
        male=view.findViewById(R.id.radioButton1);
        female=view.findViewById(R.id.radioButton2);
        other=view.findViewById(R.id.radioButton3);

        RadioGroup r=view.findViewById(R.id.radioGroup);
        int radioButtonId=r.getCheckedRadioButtonId();
        final RadioButton radioButton=view.findViewById(radioButtonId);


        if(male.isChecked()){
            selectradio=male.getText().toString();
        }
        if(female.isChecked()){
            selectradio=female.getText().toString();
        }
        if(other.isChecked())
            selectradio=other.getText().toString();


        view.findViewById(R.id.onetime_form_submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String,String> map=new HashMap<>();
                map.put("name",name.getText().toString());
                map.put("bod",bod.getText().toString());
                map.put("email",email.getText().toString());
                map.put("phone",phone.getText().toString());
                map.put("address",address.getText().toString());
                map.put("gender", selectradio.toString());

                Call<Void> call=retrofitInterface.executetempResgitration(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200){
                            shiftActivity();
                            Toast.makeText(getContext(),"Inserted to the database",Toast.LENGTH_SHORT).show();
                        }
                        if(response.code()==400)
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
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new bookslotFragmentActivity()).addToBackStack(null).commit();

    }
}
