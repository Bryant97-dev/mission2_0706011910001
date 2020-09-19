package com.uc.uts_protech.model;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.uc.uts_protech.R;

import java.util.ArrayList;

public class SimpanData {

    public SimpanData(){ }

    public static ArrayList<User> list =new ArrayList<>();

    public static final Dialog loadingDialog(Context context){
        final Dialog dialog =  new Dialog(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.loading_dialog, null);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);
        return dialog;
    }
}
