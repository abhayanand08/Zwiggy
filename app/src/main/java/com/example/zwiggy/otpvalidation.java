package com.example.zwiggy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpvalidation extends AppCompatActivity {

    EditText inputnumber1,inputnumber2,inputnumber3,inputnumber4,inputnumber5,inputnumber6;
    Button verifyotp;
    TextView resendotp;
    String enteredotp;

    String codereceived;

    FirebaseAuth firebaseAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks;

   ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpvalidation);

        inputnumber1 = findViewById(R.id.inputotp1);
        inputnumber2 = findViewById(R.id.inputotp2);
        inputnumber3 = findViewById(R.id.inputotp3);
        inputnumber4 = findViewById(R.id.inputotp4);
        inputnumber5 = findViewById(R.id.inputotp5);
        inputnumber6 = findViewById(R.id.inputotp6);

        resendotp = findViewById(R.id.resendotp);
        firebaseAuth = FirebaseAuth.getInstance();

        verifyotp = findViewById(R.id.verify_otp);


        progressBar=findViewById(R.id.loading);

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enteredotp = inputnumber1.getText().toString()
                        + inputnumber2.getText().toString()
                        + inputnumber3.getText().toString()
                        + inputnumber4.getText().toString()
                        + inputnumber5.getText().toString()
                        + inputnumber6.getText().toString();

                if (enteredotp.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter the OTP", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    verifyotp.setVisibility(View.INVISIBLE);
                    codereceived = getIntent().getStringExtra("otp");
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(codereceived, enteredotp);
                    signInwithphoneauthcredential(phoneAuthCredential);
                }


            }
        });
        numberotpmove();


    }

    private void signInwithphoneauthcredential(PhoneAuthCredential phoneAuthCredential) {

        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    verifyotp.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),mainpage.class);
                    startActivity(intent);
                    finish();
                }else{
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                        progressBar.setVisibility(View.INVISIBLE);
                        verifyotp.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Login Failed ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void numberotpmove() {

        inputnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             if (!s.toString().trim().isEmpty()){
                 inputnumber2.requestFocus();
             }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputnumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputnumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputnumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputnumber6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}