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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrFragmentActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.qrscreen_fragment,container,false);

        String data= setter.getPtid();
        ((TextView)view.findViewById(R.id.patientid)).setText(data);

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();

        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,200,200); //encode the string
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);  //conver into the qr code
            ((ImageView) view.findViewById(R.id.imageView)).setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();

        }

        return view;
    }
}
