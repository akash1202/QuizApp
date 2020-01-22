package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button bt1;
    Button bt2;
    EditText email,pass,pass2;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new DbHandler(this);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        pass2 = findViewById(R.id.pass2);

        bt1 = findViewById(R.id.submit);
        bt2 = findViewById(R.id.reset);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.submit:

            //    String username = email.getText().toString(),pass = password.getText().toString();
                String username = email.getText().toString();
                String p = pass.getText().toString();
                String p2 = pass2.getText().toString();
                //validation goes on....
                if(p.compareTo(p2) == 0)
                {
                    //inserting From edit box users
                    db.addUser(new User(username, p));
                    Toast.makeText(Main2Activity.this,"REGISTED : "+username+". GO BACK TO LOGIN.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Main2Activity.this,"PASSWORD ENTERED DOES NOT MATCH!", Toast.LENGTH_SHORT).show();
                    email.setText("");
                    pass.setText("");
                    pass2.setText("");
                }



                break;
            // Do something
            case R.id.reset:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }

    }
}
