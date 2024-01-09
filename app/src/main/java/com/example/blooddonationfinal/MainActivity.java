package com.example.blooddonationfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.blooddonationfinal.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav_view;
    private CircleImageView nav_profile_image;
    private TextView nav_fullName,nav_email,nav_bloodGroup,nav_type;
    private DatabaseReference reference;
    private FirebaseDatabase firebaseDatabase;
    ActivityMainBinding binding;
    String fullName,type,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar= findViewById(R.id.toolbarID);

        drawerLayout = findViewById(R.id.drawerLayout);
        nav_view= findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(this);

        nav_profile_image =nav_view.getHeaderView(0).findViewById(R.id.nav_user_image);
       nav_fullName =nav_view.getHeaderView(0).findViewById(R.id.nav_user_fullname);
       nav_email = nav_view.getHeaderView(0).findViewById(R.id.nav_user_email);
       nav_type = nav_view.getHeaderView(0).findViewById(R.id.nav_user_type);
       nav_bloodGroup =  nav_view.getHeaderView(0).findViewById(R.id.nav_user_bloodGroup);


        reference = FirebaseDatabase.getInstance().getReference("users").child(
                FirebaseAuth.getInstance().getCurrentUser().getUid()
        );
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    Toast.makeText(MainActivity.this, "Successfully Read", Toast.LENGTH_SHORT).show();
                    String fullName= String.valueOf(snapshot.child("name").getValue());
                    nav_fullName.setText(fullName);

                    String email= String.valueOf(snapshot.child("email").getValue());
                    nav_email.setText(email);

                    String bloodgroup = String.valueOf(snapshot.child("bloodGroup").getValue());
                    nav_bloodGroup.setText(bloodgroup);

                    String type= String.valueOf(snapshot.child("type").getValue());
                    nav_type.setText(type);

                    String imageUrl = String.valueOf(snapshot.child("profilePicture").getValue());
                    Glide.with(getApplicationContext()).load(imageUrl).into(nav_profile_image);
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to Navigate", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}