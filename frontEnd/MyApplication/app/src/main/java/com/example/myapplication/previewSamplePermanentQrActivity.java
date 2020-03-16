package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class previewSamplePermanentQrActivity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.preview_sample_qr_fragment_permanent,container,false);


        String data=setter.getPtid();

        ((TextView)view.findViewById(R.id.ptpreviewid)).setText(data);

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();

        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,200,200); //encode the string
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);  //conver into the qr code
            ((ImageView) view.findViewById(R.id.perBarPreview)).setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();

        }


        view.findViewById(R.id.gohome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new secondFragment()).commit();
                clearBackStack();
            }
        });


        return view;
    }

    private void clearBackStack() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
}
