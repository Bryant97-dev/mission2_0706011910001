package com.uc.uts_protech.directory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.uc.uts_protech.AddUserActivity;
import com.uc.uts_protech.DetailActivity;
import com.uc.uts_protech.MainActivity;
import com.uc.uts_protech.R;
import com.uc.uts_protech.model.SimpanData;
import com.uc.uts_protech.model.User;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.CardViewHolder>  {

    private Context context;

    private  ArrayList<User> listUser;
    private OnContactListener mOnContactListener;

   /* public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }

    public AdapterUser(Context context) {
        this.context = context;

    }*/
   


    public AdapterUser(ArrayList<User> listUser, OnContactListener onContactListener) {
        this.listUser = listUser;
        this.mOnContactListener = onContactListener;
    }

    /*public AdapterUser(ArrayList<User> listUser, OnContactListener mOnContactListener){

    }*/

    @NonNull
    @Override
    public AdapterUser.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_user, parent, false);
        return new AdapterUser.CardViewHolder(view, mOnContactListener);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final AdapterUser.CardViewHolder holder, int position) {
       // final User u = getListUser().get(position);
        /*holder.txt_name.setText(u.getNama());
        holder.txt_age.setText(u.getUmur()+" years old");
        holder.txt_address.setText(u.getAlamat());*/

        holder.txt_name.setText(listUser.get(position).getNama());
        holder.txt_age.setText(listUser.get(position).getUmur()+" years old");
        holder.txt_address.setText(listUser.get(position).getAlamat());

      /* holder.cview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("nama",u.getNama());
                i.putExtra("umur",u.getUmur());
                i.putExtra("alamat",u.getAlamat());
                context.startActivity(i);
            }
        });*/

    }

    @Override
    public int getItemCount() {
       //return getListUser().size();
        return listUser.size();
    }

     public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name, txt_age, txt_address;
        CardView cview;
       // Button edit;
         OnContactListener onContactListener;


         public CardViewHolder(View itemView, OnContactListener onContactListener) {
            super(itemView);


            txt_name = itemView.findViewById(R.id.display_name);
            txt_age = itemView.findViewById(R.id.display_age);
            txt_address = itemView.findViewById(R.id.display_address);
            //cview= itemView.findViewById(R.id.cardviews);
            //edit= itemView.findViewById(R.id.editbutton);
            this.onContactListener = onContactListener;

            itemView.setOnClickListener(this);



        }

         @Override
        public void onClick(View v) {
             onContactListener.onContactClick(getAdapterPosition());
         }
     }

    public interface OnContactListener{
        void onContactClick(int position);
    }
}
