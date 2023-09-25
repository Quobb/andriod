package com.example.htu_connet_social.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.htu_connet_social.FragmentRepp;
import com.example.htu_connet_social.MainActivity;
import com.example.htu_connet_social.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class FragmentCreateAccont extends Fragment {

    private EditText nameET,emailET,passwordET,confrimPasswordET;
    private TextView logintv;
    private Button signUpBtn;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    public static final String VALID_EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";


   
  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_accont, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view,@NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();
    }

    private void init(View view) {
        nameET = view.findViewById(R.id.nameET);
        emailET = view.findViewById(R.id.EmailET);
        passwordET = view.findViewById(R.id.passwordET);
        confrimPasswordET = view.findViewById(R.id.confirmPassET);
        logintv = view.findViewById(R.id.logET);
        signUpBtn = view.findViewById(R.id.SignUpButton);
        auth = FirebaseAuth.getInstance();
        progressBar = view.findViewById(R.id.progress);

    }
    public void clickListener(){
        logintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentRepp)getActivity()).setFragment( new Login());
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confrimPasswordET.getText().toString();

                if (name.isEmpty() || name.equals(" ")) {
                    nameET.setError("please input valid name");
                    return;
                }
                if (email.isEmpty() || !email.matches(VALID_EMAIL_REGEX)) {
                    emailET.setError("please input valid Email");
                    return;
                }
                if (password.isEmpty() || password.length() < 6) {
                    passwordET.setError("please input valid name");
                    return;
                }
                if (confirmPassword.isEmpty() || !password.equals(confirmPassword)) {
                    confrimPasswordET.setError("please input valid name");
                    return;
                }

                createAccount(name,email,password);
            }
        });
    }
    private void createAccount(String name,String email,String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            uploadUser(user, name, email);
                        } else {

                        }
                        progressBar.setVisibility(View.GONE);
                        String error = task.getException().getMessage();
                        Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
                    }

                });
    }
    private void uploadUser(FirebaseUser user,String name,String email ){
        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("profilImage"," ");
        map.put("uid", user.getUid());

        FirebaseFirestore.getInstance().collection("users").document(user.getUid())
                .set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        assert getContext() != null;
                        progressBar.setVisibility(View.GONE);
                        startActivity( new Intent(getContext().getApplicationContext(), MainActivity.class));
                        getActivity().finish();
                    }else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),"error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    }
                });

    }
}