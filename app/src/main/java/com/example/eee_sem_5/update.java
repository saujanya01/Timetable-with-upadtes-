package com.example.eee_sem_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class update extends AppCompatActivity
{
    Button notif;
    NotificationCompat.Builder notification;
    public static final int id = 123456;

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<notifs> list;
    adapter_class adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        reference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<notifs>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    notifs n = dataSnapshot1.getValue(notifs.class);
                    list.add(n);
                }
                adapter = new adapter_class(update.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(update.this,"Something is wrong, Please contact Saujanya",Toast.LENGTH_LONG);
            }
        });

        notification = new NotificationCompat.Builder(this,"123456");
        notification.setAutoCancel(true);
        notif = findViewById(R.id.notif);

        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifButton();
            }
        });
    }

    public void notifButton(){
        //Build a notification
        notification.setSmallIcon(R.mipmap.eee);
//        notification.setTicker("This is the Ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Branch Notification");
        notification.setContentText("This is the Content Text");
        notification.setVibrate(new long[] {100,100,100,100,100,100});

        Intent i = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Builds a notification and issues it

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(12345,notification.build());

//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(12345,notification);
    }
}
