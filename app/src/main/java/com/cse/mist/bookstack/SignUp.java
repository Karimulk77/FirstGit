package com.cse.mist.bookstack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    public final static String USER_NAME_KEY = "com.cse.mist.uname";
    public final static String USER_UNI_KEY = "com.cse.mist.uni";
    public final static String USER_ROLL_KEY = "com.cse.mist.roll";
    public final static String USER_EMAIL_KEY = "com.cse.mist.email";
    public final static String USER_PHONE_KEY = "com.cse.mist.number";
    // public final static String USER_ID_KEY = "com.cse.mist.id";
    public final static String USER_pass_KEY = "com.cse.mist.password";
    DatabaseReference datauser;
    private EditText e_name, e_uni, e_roll, e_pass, e_mail, e_phone;
    private String name, uni, roll, pass, email, phone;
    private Button regbtn;
    private ProgressDialog progressDialog;
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        datauser = FirebaseDatabase.getInstance().getReference("User");
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if (firebaseAuth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), MainPageNav.class));
        }

        e_name = (EditText) findViewById(R.id.etName);
        e_uni = (EditText) findViewById(R.id.etUni);
        e_roll = (EditText) findViewById(R.id.etroll);
        e_pass = (EditText) findViewById(R.id.etPassword);
        e_mail = (EditText) findViewById(R.id.etEmail);
        e_phone = (EditText) findViewById(R.id.etphn);
        regbtn = (Button) findViewById(R.id.tvRegBtn);


        progressDialog = new ProgressDialog(this);
       /* regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();//call when button click and need to check the input
            }
        });*/

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();//call when button click and need to check the input
            }
        });
    }

    public void save() {

        initialize();


        if (validate()) {
            progressDialog.setMessage("Adding user Please Wait...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {

                                String name = e_name.getText().toString().trim();
                                String uv = e_uni.getText().toString().trim();
                                String rl = e_roll.getText().toString().trim();
                                String mail = e_mail.getText().toString().trim();
                                String pass = e_pass.getText().toString().trim();
                                String num = e_phone.getText().toString().trim();

                                String id = new ParseEmail().Parse(email);
                                User use = new User(name, uv, rl, mail, pass, num);
                                datauser.child(id).setValue(use);

                                Toast.makeText(SignUp.this, "Congratulations! You registered successfully", Toast.LENGTH_LONG).show();


                                startActivity(new Intent(getApplicationContext(), MainPageNav.class));
                            } else {
                                //display some message here
                                Toast.makeText(SignUp.this, "Registration Error", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });


        } else {
            Toast.makeText(this, "You should fill up the information correctly", Toast.LENGTH_LONG).show();
        }
    }


    public boolean validate() {
        boolean valid = true;
        if (name.isEmpty() || name.length() > 10) {
            e_name.setError("please enter valid name");
            valid = false;
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e_mail.setError("please enter valid email");
            valid = false;
        }
        if (phone.isEmpty()) {
            e_phone.setError("please enter valid phone number");
            valid = false;
        }
        if (pass.isEmpty()) {
            e_pass.setError("please enter valid password");
            valid = false;
        }
        if (uni.isEmpty()) {
            e_uni.setError("please enter valid password");
            valid = false;
        }
        if (roll.isEmpty()) {
            e_roll.setError("please enter valid password");
            valid = false;
        }
        return valid;

    }

    public void initialize() {
        name = e_name.getText().toString().trim();
        uni = e_uni.getText().toString().trim();
        phone = e_phone.getText().toString().trim();
        email = e_mail.getText().toString().trim();
        pass = e_pass.getText().toString().trim();
        roll = e_roll.getText().toString().trim();
    }


}



