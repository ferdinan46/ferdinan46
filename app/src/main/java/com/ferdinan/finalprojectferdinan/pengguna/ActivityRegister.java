package com.ferdinan.finalprojectferdinan.pengguna;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.ferdinan.finalprojectferdinan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegister extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtNoTelp;
    private Button btnRegister, btnLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        registerUser();
    }

    private void registerUser() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRegister.this, HalamanLogin.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menampung imputan user
                String emailUser = edtEmail.getText().toString().trim();
                String passwordUser = edtPassword.getText().toString().trim();
                String nomortelponUser = edtNoTelp.getText().toString().trim();

                //validasi email dan password
                // jika email kosong
                if (emailUser.isEmpty()) {
                    edtEmail.setError("Email tidak boleh kosong");
                }
                // jika email not valid
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    edtEmail.setError("Email tidak valid");
                }
                // jika password kosong
                else if (passwordUser.isEmpty()) {
                    edtPassword.setError("Password tidak boleh kosong");
                }
                //jika password kurang dari 6 karakter
                else if (passwordUser.length() < 6) {
                    edtPassword.setError("Password minimal terdiri dari 6 karakter");
                }
                // jika NoTelp kosong
                if (nomortelponUser.isEmpty()) {
                    edtNoTelp.setError("NoTelp tidak boleh kosong");
                }
                // jika no not valid
                else if (!Patterns.PHONE.matcher(emailUser).matches()) {
                    edtNoTelp.setError("Masukan NoTelp yang benar");
                } else {
                    //create user dengan firebase auth
                    auth.createUserWithEmailAndPassword(emailUser, passwordUser)
                            .addOnCompleteListener(ActivityRegister.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //jika gagal register do something
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(ActivityRegister.this,
                                                "Register gagal karena " + task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        //jika sukses akan menuju ke login activity
                                        startActivity(new Intent(ActivityRegister.this, HalamanLogin.class));
                                    }
                                }
                            });
                }
            }
        });
    }

    private void initView() {
        edtEmail = findViewById(R.id.edt_email_register);
        edtPassword = findViewById(R.id.edt_password_register);
        edtNoTelp = findViewById(R.id.edt_no_telp);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_sign_up);
        auth = FirebaseAuth.getInstance();
    }
}