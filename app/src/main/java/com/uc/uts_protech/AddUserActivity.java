package com.uc.uts_protech;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.uc.uts_protech.model.SaveData;
import com.uc.uts_protech.model.SimpanData;
import com.uc.uts_protech.model.User;

import java.util.ArrayList;

import static com.uc.uts_protech.model.SimpanData.list;

public class AddUserActivity<dialog> extends AppCompatActivity {

    TextInputLayout fname, age, address;
    TextInputEditText nameTxt, ageTxt, addressTxt;
    Button button;
    String Name, Age, Address;
    String getname, getage, getaddress;
    int pos;
    Dialog dialog;
    //ArrayList <User> listUsers ;
    ArrayList<User> listUsers = SaveData.saveList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        dialog= SimpanData.loadingDialog(AddUserActivity. this);
        fname = findViewById(R.id.input_fname);
        age = findViewById(R.id.input_age);
        address= findViewById(R.id.input_address);
        nameTxt= findViewById(R.id.edit_fname);
        ageTxt= findViewById(R.id.edit_age);
        addressTxt= findViewById(R.id.edit_address);
        button = findViewById(R.id.check_button);
        Toolbar toolbar2 = findViewById(R.id.toolbar_add_user);

        //setSupportActionBar(toolbar2);
      //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fname.getEditText().addTextChangedListener(textwacther);
        age.getEditText().addTextChangedListener(textwacther);
        address.getEditText().addTextChangedListener(textwacther);

        Intent intent = getIntent();
        pos = intent.getIntExtra("position",0);
        

        if(pos >= 0){
            button.setText("Update Data");
            toolbar2.setTitle("Edit User");
            getname = listUsers.get(pos).getNama();
            getage = listUsers.get(pos).getUmur();
            getaddress = listUsers.get(pos).getAlamat();

            nameTxt.setText(getname);
            getage = getage.replaceAll("\\D+","");
            ageTxt.setText(getage);
            addressTxt.setText(getaddress);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                dialog.show();
                    new Handler().postDelayed(new Runnable() {
                     @Override
                      public void run() {
                         dialog.cancel();
                         Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                         if(pos>=0){
                             listUsers.get(pos).setNama(Name);
                             listUsers.get(pos).setUmur(Age);
                             listUsers.get(pos).setAlamat (Address);

                         } else {
                             User user = new User(Name, Age, Address);
                             SimpanData.list.add(user);
                             intent.putExtra("user", user);
                             SaveData.saveList.add(user);
                         }

                         ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation( AddUserActivity. this);
                         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                         startActivity(intent,options.toBundle());
                         finish();
                       }
                    }, 2000);
            }
        });

        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                onBackPressed();
            }
        });

    }

    TextWatcher textwacther = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Name = fname.getEditText().getText().toString().trim();
            Age = age.getEditText().getText().toString().trim();
            Address = address.getEditText().getText().toString().trim();
            button.setEnabled(!Name.isEmpty() && !Age.isEmpty() && !Address.isEmpty() );
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
