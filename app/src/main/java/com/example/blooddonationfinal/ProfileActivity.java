package com.example.blooddonationfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.blooddonationfinal.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView type,name,email,phonenumber,bloodgroup;
    CircleImageView profileImage;
    Button backButton;
    DatabaseReference reference;
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        type = findViewById(R.id.type);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phoneNumber);
        bloodgroup = findViewById(R.id.bloodGroup);
        profileImage = findViewById(R.id.profile_image);
        backButton = findViewById(R.id.backButton);
        reference = FirebaseDatabase.getInstance().getReference("users").child(
                FirebaseAuth.getInstance().getCurrentUser().getUid()
        );
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    Toast.makeText(ProfileActivity.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                    String fullName= String.valueOf(snapshot.child("name").getValue());
                    name.setText(fullName);

                    String emailName= String.valueOf(snapshot.child("email").getValue());
                    email.setText(emailName);

                    String phoneName = String.valueOf(snapshot.child("").getValue());
                    bloodgroup.setText(bloodgroupName);

                    String bloodgroupName = String.valueOf(snapshot.child("bloodGroup").getValue());
                    bloodgroup.setText(bloodgroupName);

                    String typeName= String.valueOf(snapshot.child("type").getValue());
                    type.setText(typeName);

                    String imageUrl = String.valueOf(snapshot.child("profilePicture").getValue());
                    Glide.with(getApplicationContext()).load(imageUrl).into(profileImage);
                }
                else{
                    Toast.makeText(ProfileActivity.this, "Failed to Navigate", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//     public boolean onOptionsItemSelected(@NonNull MenuItem item){
//            return super.onOptionsItemSelected(item);
//        }

    }
}