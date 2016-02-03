package com.s04.ybb.com.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start;
    private Button stop;

    private Button bindstart;
    private Button bindstop;

    private Myservice.DownBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           Log.d("MyService","activty contect");
           myBinder = (Myservice.DownBinder)service;
           myBinder.startdown();
           Toast.makeText(MainActivity.this,"下载成功",Toast.LENGTH_SHORT).show();
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {

       }
   };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_start:
                Intent startintent = new Intent(this,Myservice.class);
                startService(startintent);
                break;
            case R.id.service_stop:
                Intent stopintent = new Intent(this,Myservice.class);
                stopService(stopintent);
                break;
            case R.id.service_bind:
                Intent startbinderintent = new Intent(this,Myservice.class);
                bindService(startbinderintent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.service_unbind:
                Intent stopbinderintent = new Intent(this,Myservice.class);
                unbindService(connection);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button)findViewById(R.id.service_start);
        stop = (Button)findViewById(R.id.service_stop);
        bindstart = (Button)findViewById(R.id.service_bind);
        bindstop = (Button)findViewById(R.id.service_unbind);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bindstart.setOnClickListener(this);
        bindstop.setOnClickListener(this);
    }
}
