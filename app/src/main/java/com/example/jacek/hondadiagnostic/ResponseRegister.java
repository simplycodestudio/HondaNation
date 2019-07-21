package com.example.jacek.hondadiagnostic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import com.google.firebase.iid.FirebaseInstanceId;


public class ResponseRegister extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference storeUserDefaultDataReference;
    private ProgressDialog loadingBar;
    private EditText RegisterUserName;
     private EditText RegisterUserEmail;
     private EditText RegisterUserPassword;
     private Button CreateAccountButton;
    private android.support.v7.widget.Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_register);

        mAuth = FirebaseAuth.getInstance();

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.response_register_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RegisterUserEmail = (EditText) findViewById(R.id.email_register);
        RegisterUserName = (EditText) findViewById(R.id.name_register);
        RegisterUserPassword = (EditText) findViewById(R.id.password_register);
        CreateAccountButton = (Button) findViewById(R.id.create_account_button);
        loadingBar = new ProgressDialog(this);




        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  String name = RegisterUserName.getText().toString();
                 String email = RegisterUserEmail.getText().toString();
                 String password = RegisterUserPassword.getText().toString();

                RegisterAccount (name, email, password);

            }
        });

    }



    private void RegisterAccount(final String name, String email, String password) {

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(ResponseRegister.this, "Please write your name.",
            Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(ResponseRegister.this, "Please write your email.",
                    Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(password)|| password.length() < 6)
        {
            Toast.makeText(ResponseRegister.this, "Hasło musi mieć min. 6 znaków",
                    Toast.LENGTH_LONG).show();
        }


        else
        {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait, while we're creating account for you.");
            loadingBar.show();

         mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task)
             {
                if (task.isSuccessful())
                {
                    String DeviceToken = FirebaseInstanceId.getInstance().getToken();
                    String current_user_Id = mAuth.getCurrentUser().getUid();
                  storeUserDefaultDataReference = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user_Id);

                  storeUserDefaultDataReference.child("user_name").setValue(name);
                  storeUserDefaultDataReference.child("user_status").setValue("Hey there. I am using Response");
                  storeUserDefaultDataReference.child("user_image").setValue("default_profile");
                  storeUserDefaultDataReference.child("device_token").setValue(DeviceToken);
                  storeUserDefaultDataReference.child("user_thumb_image").setValue("default_image")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                   if (task.isSuccessful())
                                   {
                                       Intent mainIntent = new Intent(ResponseRegister.this, ResponseMainActivity.class);
                                       mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                       startActivity(mainIntent);
                                       finish();
                                   }
                                }
                            });

                }

                else
                    {
                        Toast.makeText(ResponseRegister.this, "Error Occured, Try Again...",
                                Toast.LENGTH_LONG).show();
                    }

                    loadingBar.dismiss();
             }
         });
        }

    }
}
