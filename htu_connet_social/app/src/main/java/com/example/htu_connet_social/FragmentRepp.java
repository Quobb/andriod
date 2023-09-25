package com.example.htu_connet_social;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.htu_connet_social.fragment.FragmentCreateAccont;
import com.example.htu_connet_social.fragment.Login;

public class FragmentRepp extends AppCompatActivity {
    private FrameLayout framelayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_repp);
        framelayout = findViewById(R.id.fameLayout);
        setFragment(new Login());
    }
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        if (fragment instanceof FragmentCreateAccont) {
            fragmentTransaction.addToBackStack(null);

        }

        fragmentTransaction.replace(framelayout.getId(),fragment);
        fragmentTransaction.commit();

    }
}