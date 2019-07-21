package com.example.jacek.hondadiagnostic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ToolbarWidgetWrapper;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResponseStatusActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;
    private Button SaveChangeButton;
    private EditText StatusInput;
    private ProgressDialog loadingBar;
    private DatabaseReference changeStatusRef;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_status);

        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        changeStatusRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.response_status_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Change Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SaveChangeButton = (Button) findViewById(R.id.save_status_change_button);
        StatusInput = (EditText) findViewById(R.id.status_input);
        loadingBar = new ProgressDialog(this);

        String old_status = getIntent().getExtras().get("user_status").toString();
        StatusInput.setText(old_status);

        SaveChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              String new_status = StatusInput.getText().toString();

              ChangeProfileStatus(new_status);
            }
        });



    }

    private void ChangeProfileStatus(String new_status)
    {
        if (TextUtils.isEmpty(new_status))
        {
            Toast.makeText(ResponseStatusActivity.this, "Correct your status", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Change Profile Status");
            loadingBar.setMessage("Please wait for updating status");
            loadingBar.show();

           changeStatusRef.child("user_status").setValue(new_status).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task)
               {
                   if (task.isSuccessful())
                   {
                     loadingBar.dismiss();
                     Intent settingsIntent = new Intent(ResponseStatusActivity.this, ResponseSettingsActivity.class);
                     startActivity(settingsIntent);

                     Toast.makeText(ResponseStatusActivity.this,
                             "Profile Status Updated", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       Toast.makeText(ResponseStatusActivity.this,
                               "Error!", Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }
    }
}
