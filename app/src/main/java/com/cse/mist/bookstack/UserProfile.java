package com.cse.mist.bookstack;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {

    String id1;
    String pass;
    ImageView imageView;
    EditText p_name, p_uni, p_roll, p_contact, p_email;
    DatabaseReference datauser;
    private Button selectImage, editt, savee;
    private int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);

        selectImage = (Button) findViewById(R.id.selectInmage);
        imageView = (ImageView) findViewById(R.id.imageView);
        p_name = (EditText) findViewById(R.id.etNam);//
        p_uni = (EditText) findViewById(R.id.etUn);//
        p_roll = (EditText) findViewById(R.id.etrol);//
        p_contact = (EditText) findViewById(R.id.etcontac);//
        p_email = (EditText) findViewById(R.id.etEmai);//
        editt = (Button) findViewById(R.id.edit);
        savee = (Button) findViewById(R.id.save);

        Intent intent = getIntent();
        //id1 = intent.getStringExtra(SignUp.USER_ID_KEY);
        pass = intent.getStringExtra(SignUp.USER_pass_KEY);

        datauser = FirebaseDatabase.getInstance().getReference("User");


        /*p_name.setEnabled(false);
        p_uni.setEnabled(false);
        p_roll.setEnabled(false);
        p_contact.setEnabled(false);
        p_email.setEnabled(false);*/

        savee.setVisibility(View.GONE);
        editt.setVisibility(View.VISIBLE);

        savee.setOnClickListener(this);
        editt.setOnClickListener(this);


        selectImage.setOnClickListener(new View.OnClickListener() {

                                           @Override
                                           public void onClick(View view) {

                                               Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                                               photoPickerIntent.setType("image/*");
                                               startActivityForResult(photoPickerIntent, REQUEST_CODE);
                                           }
                                       }
        );


    }


    @Override
    public void onClick(View view) {
        if (view == editt) {
            p_name.setEnabled(true);
            p_uni.setEnabled(true);
            p_roll.setEnabled(true);
            p_contact.setEnabled(true);
            p_email.setEnabled(true);
            savee.setVisibility(View.VISIBLE);
            editt.setVisibility(View.GONE);

        }

        if (view == savee) {
            p_name.setEnabled(false);
            p_uni.setEnabled(false);
            p_roll.setEnabled(false);
            p_contact.setEnabled(false);
            p_email.setEnabled(false);
            savee.setVisibility(View.GONE);
            editt.setVisibility(View.VISIBLE);
            // Toast.makeText(this, "profile!", Toast.LENGTH_SHORT).show();

            updateu();

        }
    }

    private void updateu() {

        // getting the specific artist reference
        //DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("user").child(id1);

        // getting the value
        String name = p_name.getText().toString().trim();
        String uni = p_uni.getText().toString().trim();
        String roll = p_roll.getText().toString().trim();
        String contact = p_contact.getText().toString().trim();
        String email = p_email.getText().toString().trim();


        String id = new ParseEmail().Parse(email);
        User use = new User(name, uni, roll, email, pass, contact);
        datauser.child(id).setValue(use);


        Toast.makeText(this, "profile Info Updated!", Toast.LENGTH_SHORT).show();
    }


    protected void onStart() {
        super.onStart();

        String id = new ParseEmail().Parse(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        DatabaseReference resdata;
        resdata = FirebaseDatabase.getInstance().getReference("User").child(id);
        resdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User pc = dataSnapshot.getValue(User.class);

                p_name.setText(pc.getUname());
                p_uni.setText(pc.getUni());
                p_roll.setText(pc.getRoll());
                p_contact.setText(pc.getNumber());
                p_email.setText(pc.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(UserProfile.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(UserProfile.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}

