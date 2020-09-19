package com.uc.uts_protech;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.uc.uts_protech.directory.AdapterUser;
import com.uc.uts_protech.model.SaveData;
import com.uc.uts_protech.model.SimpanData;
import com.uc.uts_protech.model.User;

import java.util.ArrayList;
import java.util.Iterator;

public class DetailActivity extends AppCompatActivity {
    ArrayList<User> listUsers = SaveData.saveList;
    CardView cardView;
    TextView nama,umur,alamat;
    String Name, Age, Address;
    ArrayList<User> listUser;
    Button edit, delete;
    Toolbar toolbaredit;
    //ArrayAdapter<String> adapter;
    AdapterUser mAdapter;
    Dialog dialog;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
       /* nama = findViewById(R.id.textdetails1);
        umur = findViewById(R.id.textdetails3);
        alamat = findViewById(R.id.textdetail5);
        edit = findViewById(R.id.editbutton);

        Name = getIntent().getStringExtra("nama");
        Age = getIntent().getStringExtra("umur");
        Address = getIntent().getStringExtra("alamat");

        nama.setText(Name);
        umur.setText(Age);
        alamat.setText(Address);*/

        final Intent intent = getIntent();
        final int position = intent.getIntExtra("contact_info",0);
        //Log.d("position", String.valueOf(position));
        Name = listUsers.get(position).getNama();
        Age = listUsers.get(position).getUmur();
        Address = listUsers.get(position).getAlamat();

        Log.d("name",Name);
        Log.d("age",Age);
        Log.d("address",Address);

        nama = findViewById(R.id.textdetails1);
        umur = findViewById(R.id.textdetails3);
        alamat = findViewById(R.id.textdetail5);
        toolbaredit = findViewById(R.id.toolbar3);
        final LoadingDialog loadingDialog =  new LoadingDialog(DetailActivity.this);
      edit = findViewById(R.id.editbutton);
       delete= findViewById(R.id.deletebutton);

       nama.setText(Name);
       umur.setText(Age);
       alamat.setText(Address);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,AddUserActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        toolbaredit.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(DetailActivity.this,MainActivity.class);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                listUsers.remove(position);
                                loadingDialog.startLoadingDialog();
                                Handler handler =new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadingDialog.dismissDialog();
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(DetailActivity.this, "Delete success!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                },5000);
//                                Toast.makeText(UserActivity.this, "Delete Success",
//                                        Toast.LENGTH_LONG).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setMessage("Are you sure you want to delete " +Name + " ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });

    }
}
