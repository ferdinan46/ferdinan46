package com.ferdinan.finalprojectferdinan.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ferdinan.finalprojectferdinan.R;


public class AdminActivity extends AppCompatActivity {
    private Button btnkeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initView();
        keluar();
    }
    private void keluar(){
        btnkeluar.setOnClickListener(view -> startActivity(new Intent(AdminActivity.this, HalamanLogin.class)));
    }
    private void initView(){
        btnkeluar = findViewById(R.id.btnkeluar);
    }
}
