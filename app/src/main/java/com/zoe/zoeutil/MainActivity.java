package com.zoe.zoeutil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zoe.utilslib.ICSystem.ICS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ICS.superSend("second","hello");
        ICS.register("main", new ICS.ICSReceiver() {
            @Override
            public void onReceive(String msg) {
                Log.d("MyDebug","main recv:"+msg);
            }
        });

        Intent intent=new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ICS.unregister("main");
    }
}
