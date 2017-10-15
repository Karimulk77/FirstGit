package com.cse.mist.bookstack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    private Context context;

    private TextView tvRegBtn, tvRegBtn1;
    private EditText etEmail, etPassword;
    private ProgressDialog progressDialog;


    //defining firebaseauth objet
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = this;
        intUi();
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if (firebaseAuth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), MainPageNav.class));
        }

    }

    private void intUi() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        tvRegBtn = (TextView) findViewById(R.id.tvRegBtn);
        tvRegBtn1 = (TextView) findViewById(R.id.tvRegBtn1);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        progressDialog = new ProgressDialog(this);


        tvRegBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String passWord = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    alertDialog.setMessage(getString(R.string.alerEmail));
                    alertDialog.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    alertDialog.show();
                    return;
                } else if (TextUtils.isEmpty(passWord)) {
                    alertDialog.setMessage(getString(R.string.alerPasword));
                    alertDialog.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    alertDialog.show();
                    return;
                } else {
                    progressDialog.setMessage("Logging in Please Wait...");
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(email, passWord)
                            .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    //if the task is successfull
                                    if (task.isSuccessful()) {
                                        //start the profile activity
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), MainPageNav.class));
                                    }
                                }
                            });
                }
            }
        });


        tvRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, SignUp.class);
                startActivity(intent1);
            }
        });

    }
}
