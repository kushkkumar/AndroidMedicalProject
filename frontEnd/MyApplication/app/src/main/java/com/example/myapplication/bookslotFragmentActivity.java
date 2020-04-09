package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class bookslotFragmentActivity extends Fragment {
    Spinner spinner;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://young-sea-56344.herokuapp.com/";
    private String doctord;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.book_slot_fragment,container,false);



            spinner=view.findViewById(R.id.spinner_doc_details);





        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface=retrofit.create(RetrofitInterface.class);

        Call<List<doctordetailresult>> listCall=retrofitInterface.getdoctorDetails();
        listCall.enqueue(new Callback<List<doctordetailresult>>() {
            @Override
            public void onResponse(Call<List<doctordetailresult>> call, Response<List<doctordetailresult>> response) {
                ArrayList<String> strings=new ArrayList<>();

                for(doctordetailresult x : response.body()){
                    strings.add(x.getName()+" : "+x.getSpeciality());
                }
                final String[] arr=new String[strings.size()];

                int i=0;

                for(String s : strings){
                    arr[i++]=s;
                }

//                spinner.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,arr));
//                spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(getContext(),""+arr[position],Toast.LENGTH_SHORT).show();
//                    }
//                });


                ArrayAdapter ad=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,arr);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(),"You have selected doctor :"+arr[position]+getdate(),Toast.LENGTH_SHORT).show();

                        doctord=arr[position];
                        ;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner.setAdapter(ad);





            }

            @Override
            public void onFailure(Call<List<doctordetailresult>> call, Throwable t) {

            }
        });








        view.findViewById(R.id.get_slot_number).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new qrcodePreviewFragmentActivity()).addToBackStack(null).commit();
                }
            });
            
        return view;
    }

    private String getdate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }


}
