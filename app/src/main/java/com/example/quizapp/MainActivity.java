package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button signup;
    Button login;
    EditText usename;
    EditText password;
    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DbHandler(this);

        signup = findViewById(R.id.signup);
        usename = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);

        signup.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup:
                //Toast.makeText(MainActivity.this,"Signup Clicked!",Toast.LENGTH_SHORT).show();
                Log.d("MyApp","signup clicked");

                Intent i = new Intent(this, Main2Activity.class);
                startActivity(i);
                break;
            // Do something
            case R.id.loginButton:
                String username = usename.getText().toString(),pass = password.getText().toString();

                if(checkUser(new User(username,pass)))
                {
                    Toast.makeText(MainActivity.this,"User Exist "+username,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(intent);
                    finish();
                    // Write Code From here to redicct user to home page when pass word is correct -VRAJESH
                }
                else
                {
                    Toast.makeText(MainActivity.this,"User Does Not Exist",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    public boolean checkUser(User user)
    {
        return db.checkUser(user);
    }

}
