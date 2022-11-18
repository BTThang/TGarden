package com.example.tgarden.LocationOwner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tgarden.R;
import com.example.tgarden.User.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RetailerAccountFragment extends Fragment {
    private FirebaseAuth mAuth;
    public RetailerAccountFragment(){
//
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retailer_account, container, false);
        TextView logOut = view.findViewById(R.id.layout_logout);
        mAuth = FirebaseAuth.getInstance();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn muốn đăng xuất tài khoản này?");
                builder.setPositiveButton("Không",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                        .setNegativeButton("Có",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        mAuth.signOut();
                                        checkUserStatus();
                                    }
                                });
                builder.create().show();
            }
        });
        return view;

    }
    private void checkUserStatus(){
        startActivity(new Intent(getContext(), MainActivity.class));
    }
}