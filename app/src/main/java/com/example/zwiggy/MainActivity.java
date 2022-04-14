package com.example.zwiggy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText phonenumber;
    Button sendotp;
    CountryCodePicker code;
     String countrycode;
     String phnumber;
    String number;

    FirebaseAuth firebaseAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks;
    String codesent;

    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phonenumber = findViewById(R.id.mgetphonenumber);
        sendotp = findViewById(R.id.send_otp);
        code = findViewById(R.id.ccp);

        firebaseAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.loading);

        countrycode=code.getSelectedCountryCodeWithPlus();

        code.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countrycode=code.getSelectedCountryCodeWithPlus();
            }
        });


        sendotp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                number=phonenumber.getText().toString();
                if (number.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your number", Toast.LENGTH_SHORT).show();
                }
                else if (number.length()<10){
                    Toast.makeText(getApplicationContext(), "Please Enter a Valid phone number", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    sendotp.setVisibility(View.INVISIBLE);
                    phnumber=countrycode+number;

                    PhoneAuthOptions options=PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber(phnumber)
                            .setTimeout(60L,TimeUnit.SECONDS)
                            .setActivity(MainActivity.this)
                            .setCallbacks(mcallbacks)
                            .build();

                    PhoneAuthProvider.verifyPhoneNumber(options);


                }
            }
        });

        mcallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                progressBar.setVisibility(View.INVISIBLE);
                sendotp.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Toast.makeText(getApplicationContext(), "OTP is Sent", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                sendotp.setVisibility(View.VISIBLE);
                codesent=s;
                Intent intent=new Intent(MainActivity.this,otpvalidation.class);
                intent.putExtra("otp",codesent);
                intent.putExtra("phone",phnumber);
                startActivity(intent);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent=new Intent(MainActivity.this,mainpage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}