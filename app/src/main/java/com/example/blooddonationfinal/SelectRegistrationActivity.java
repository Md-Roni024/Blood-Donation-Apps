package com.example.blooddonationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.blooddonationfinal.databinding.ActivitySelectRegistrationBinding;

public class SelectRegistrationActivity extends AppCompatActivity {
    ActivitySelectRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.donorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRegistrationActivity.this,DonorRegistrationActivity.class);
                startActivity(intent);
            }
        });
        binding.recipentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRegistrationActivity.this,RecipentRegitrationActivity.class);
                startActivity(intent);
            }
        });
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectRegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}