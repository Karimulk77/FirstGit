package com.cse.mist.bookstack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddBook extends AppCompatActivity implements View.OnClickListener {


    public final static String Book_ID_KEY = "com.cse.mist.Bid";
    public final static String Book_NAME_KEY = "com.cse.mist.BookName";
    public final static String Book_GENRE_KEY = "com.cse.mist.BookGenre";
    public final static String Book_writer_KEY = "com.cse.mist.Writer";
    public final static String Book_type_KEY = "com.cse.mist.Type";
    DatabaseReference databasebook;
    List<Books> bookList;
    ListView bookListView;
    private EditText nam, writer;
    private Button addi;
    private Spinner spinner, spinner1;

    @Override
    protected void onStart() {
        super.onStart();


        databasebook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bookList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //getting book
                    Books temp = snapshot.getValue(Books.class);

                    //adding book to the list
                    bookList.add(temp);

                }


                BookAdapter bookAdapter = new BookAdapter(AddBook.this, bookList);
                //BookAdapter bookAdapter1 = new BookAdapter(Main7Activity, bookList);

                //set the adapter to the list view
                bookListView.setAdapter(bookAdapter);
                //bookListView.setAdapter(bookAdapter1);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook);

        databasebook = FirebaseDatabase.getInstance().getReference("books");


        bookList = new ArrayList<>();

        nam = (EditText) findViewById(R.id.name);
        writer = (EditText) findViewById(R.id.writer);
        addi = (Button) findViewById(R.id.ad);
        spinner = (Spinner) findViewById(R.id.spiner);
        spinner1 = (Spinner) findViewById(R.id.spiner2);


        bookListView = (ListView) findViewById(R.id.listViewBook);
        addi.setOnClickListener(this);

        //adding listner to list view


        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // getting the selected artist
                Books book = bookList.get(i);

                // creating an intent
                Intent intent = new Intent(getApplicationContext(), Bookprofile.class);
                //  Intent intent1 = new Intent(getApplicationContext(), Bookprofile.class);

                //sending Book name and id to intent
                intent.putExtra(Book_ID_KEY, book.getBid());
                intent.putExtra(Book_NAME_KEY, book.getBookName());
                intent.putExtra(Book_writer_KEY, book.getWriter());
                intent.putExtra(Book_GENRE_KEY, book.getBookGenre());
                intent.putExtra(Book_type_KEY, book.getType());

                //starting the intent
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == addi) {
            addbook();
        }
    }


    private void addbook() {
        String name = nam.getText().toString().trim();
        String wname = writer.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();
        String genre1 = spinner1.getSelectedItem().toString();


        if (!TextUtils.isEmpty(name)) {
            String id = databasebook.push().getKey();
            Books boi = new Books(id, name, genre, wname, genre1);
            databasebook.child(id).setValue(boi);

            nam.setText("");
            writer.setText("");
            Toast.makeText(this, "Book added", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(this, "You should enter name book and writer name", Toast.LENGTH_LONG).show();
        }
    }


}

