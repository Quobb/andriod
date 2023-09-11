package com.example.htu_connet_social.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.htu_connet_social.R;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.ktx.*;


public class CreateAccountFragment extends Fragment {
private EditText nameEt,emailET,passwordEt,confirmPasswordET;
private TextView loginTV;
private Button SignUpBtn;
private Firebase authentication;
FirebaseAuth auth;



    public CreateAccountFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }
}