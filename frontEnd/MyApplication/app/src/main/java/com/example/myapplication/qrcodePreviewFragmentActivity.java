package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrcodePreviewFragmentActivity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.qrcode_preview_fragment,container,false);

        String data="hello!!!";

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();

        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,200,200); //encode the string
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);  //conver into the qr code
            ((ImageView) view.findViewById(R.id.barcode_view)).setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();

        }

        view.findViewById(R.id.go_to_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,new secondFragment()).commit();
            }
        });




        return view;
    }
}
