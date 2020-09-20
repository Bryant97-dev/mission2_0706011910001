package com.uc.uts_protech;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.uts_protech.directory.AdapterUser;
import com.uc.uts_protech.model.SaveData;
import com.uc.uts_protech.model.User;

import java.util.ArrayList;

import static com.uc.uts_protech.model.SimpanData.list;

public class MainActivity extends AppCompatActivity implements AdapterUser.OnContactListener{
    FloatingActionButton button;
    ArrayList <User> listUsers = SaveData.saveList;
    TextView noData;
    RecyclerView rv;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recycler_view);
        noData = findViewById(R.id.no_data);
        button = findViewById(R.id.button);
        ActionBar actionBar = getSupportActionBar();
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("position",-1);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(intent, options.toBundle());

            }
        });

        if(listUsers.isEmpty()){
            noData.setVisibility(View.VISIBLE);
        }else{
            noData.setVisibility(View.INVISIBLE);
           // showUsers(listUsers);
            rv.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new AdapterUser(listUsers, this);
            rv.setLayoutManager(mLayoutManager);
            rv.setAdapter(mAdapter);
        }

    }

    /*private void showUsers(ArrayList<User> listUsers) {
        rv.setAdapter(null);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        AdapterUser adapter = new AdapterUser(MainActivity.this);
        adapter.setListUser(list);
        rv.setAdapter(adapter);

    }*/

    public boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent b = new Intent(Intent.ACTION_MAIN);
            b.addCategory(Intent.CATEGORY_HOME);
            b.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(b);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(MainActivity.this, "Press back once more to close the apps!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContactClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("contact_info",position);
        startActivity(intent);
    }
}


