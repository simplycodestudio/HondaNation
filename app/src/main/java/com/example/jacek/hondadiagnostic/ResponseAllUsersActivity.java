package com.example.jacek.hondadiagnostic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import com.example.jacek.hondadiagnostic.ResponseProfileActivity;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResponseAllUsersActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView allUsersList;
    private DatabaseReference allDatabaseUserreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_all_users);

        mToolbar = (Toolbar) findViewById(R.id.all_users_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("All Users");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        allUsersList = (RecyclerView) findViewById(R.id.all_users_list);
        allUsersList.setHasFixedSize(true);
        allUsersList.setLayoutManager(new LinearLayoutManager(this));

        allDatabaseUserreference = FirebaseDatabase.getInstance().getReference().child("Users");
        allDatabaseUserreference.keepSynced(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users");

        FirebaseRecyclerOptions<Response_AllUsers> options =
                new FirebaseRecyclerOptions.Builder<Response_AllUsers>()
                        .setQuery(query, Response_AllUsers.class)
                        .build();

        FirebaseRecyclerAdapter firebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<Response_AllUsers, AllUsersViewHolder>
                (options)
        {
            @Override
            protected void onBindViewHolder(@NonNull AllUsersViewHolder holder, final int position, @NonNull Response_AllUsers model)
            {
                holder.setUser_name(model.getUser_name());
                holder.setUser_status(model.getUser_status());
                holder.setUser_thumb_image(getApplicationContext(), model.getUser_thumb_image());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        String visit_user_id = getRef(position).getKey();

                        Intent profileIntent = new Intent(ResponseAllUsersActivity.this,ResponseProfileActivity.class);
                        profileIntent.putExtra("visit_user_id", visit_user_id);
                        startActivity(profileIntent);
                    }
                });
            }

            @NonNull
            @Override
            public AllUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.response_users_display_layout,parent,false);
                return new AllUsersViewHolder(view);
            }


        };
        allUsersList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public static class AllUsersViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public AllUsersViewHolder(View itemView)
        {
            super(itemView);

            mView = itemView;
        }
        public void setUser_name(String user_name)
        {
            TextView name = (TextView) mView.findViewById(R.id.all_users_username);
            name.setText(user_name);
        }

        public void setUser_status(String user_status)
        {
            TextView status = (TextView) mView.findViewById(R.id.all_users_status);
            status.setText(user_status);
        }
        public void setUser_thumb_image(final Context ctx, final String user_thumb_image)
        {
            final CircleImageView thumb_image = (CircleImageView) mView.findViewById(R.id.all_users_profile_image);

//           Picasso.with(ctx).load(user_thumb_image).placeholder(R.drawable.default_profile).into(thumb_image);
            Picasso.with(ctx).load(user_thumb_image).networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.drawable.default_profile).into(thumb_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError()
                {
                    Picasso.with(ctx).load(user_thumb_image).placeholder(R.drawable.default_profile).into(thumb_image);
                }
            });
        }
    }
}

