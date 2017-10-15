package com.cse.mist.bookstack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Bookprofile extends AppCompatActivity implements View.OnClickListener {

    String id;
    private EditText editTextName;
    private EditText editTextName1, editTextemail;
    private TextView textViewGenre, textViewGenre1;
    private Spinner spinnerGenres, spinnerGenres1;
    private Button buttonEditBook, buttonDeleteBook, buttonSaveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookprofile);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName1 = (EditText) findViewById(R.id.editTextName1);
        // editTextemail =(EditText) findViewById(R.id.email);
        textViewGenre = (TextView) findViewById(R.id.textViewGenre);
        textViewGenre1 = (TextView) findViewById(R.id.textViewGenre1);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerGenres);
        spinnerGenres1 = (Spinner) findViewById(R.id.spinnerGenres1);
        buttonEditBook = (Button) findViewById(R.id.buttonEditBook);
        buttonDeleteBook = (Button) findViewById(R.id.buttonDeleteBook);
        buttonSaveInfo = (Button) findViewById(R.id.buttonSaveInfo);

        Intent intent = getIntent();
        id = intent.getStringExtra(AddBook.Book_ID_KEY);

        spinnerGenres.setVisibility(View.GONE);
        spinnerGenres1.setVisibility(View.GONE);        //(View.INVISIBLE);
        buttonSaveInfo.setVisibility(View.GONE);
        editTextName.setText(intent.getStringExtra(AddBook.Book_NAME_KEY));
        editTextName1.setText(intent.getStringExtra(AddBook.Book_writer_KEY));
        textViewGenre.setText(intent.getStringExtra(AddBook.Book_GENRE_KEY));
        textViewGenre1.setText(intent.getStringExtra(AddBook.Book_type_KEY));
        //editTextemail.setText(intent.getStringExtra(AddBook.Book_email_KEY));

        buttonEditBook.setOnClickListener(this);
        buttonDeleteBook.setOnClickListener(this);
        buttonSaveInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonEditBook) {
            editTextName.setEnabled(true);
            editTextName1.setEnabled(true);
            buttonSaveInfo.setVisibility(View.VISIBLE);
            textViewGenre.setVisibility(View.GONE);
            spinnerGenres.setVisibility(View.VISIBLE);
            textViewGenre1.setVisibility(View.GONE);
            spinnerGenres1.setVisibility(View.VISIBLE);
            buttonEditBook.setVisibility(View.GONE);

        }

        if (view == buttonSaveInfo) {
            editTextName.setEnabled(false);
            editTextName1.setEnabled(false);
            //editTextemail.setEnabled(false);
            spinnerGenres.setVisibility(View.GONE);
            textViewGenre.setVisibility(View.VISIBLE);
            spinnerGenres1.setVisibility(View.GONE);
            textViewGenre1.setVisibility(View.VISIBLE);
            buttonEditBook.setVisibility(View.VISIBLE);
            buttonSaveInfo.setVisibility(View.GONE);

            updateBook();

        }
        if (view == buttonDeleteBook) {
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("books").child(id);

            databaseRef.removeValue();

            Toast.makeText(this, "Book Deleted!", Toast.LENGTH_SHORT).show();

        }

    }

    private void updateBook() {

        // getting the specific artist reference
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("books").child(id);

        // getting the value
        String name = editTextName.getText().toString().trim();
        String wname = editTextName1.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();
        String genre1 = spinnerGenres1.getSelectedItem().toString();
        String email = editTextemail.getText().toString().trim();


        // creating the object
        Books boi = new Books(id, name, genre, wname, genre1);
        // set the value
        databaseRef.setValue(boi);

        Toast.makeText(this, "Book Info Updated!", Toast.LENGTH_SHORT).show();
    }
}
