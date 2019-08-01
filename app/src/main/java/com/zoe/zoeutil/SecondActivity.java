package com.zoe.zoeutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zoe.utilslib.ICSystem.ICS;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ICS.register("second", new ICS.ICSReceiver() {
            @Override
            public void onReceive(String msg) {
                Log.d("MyDebug","second rec:"+msg);
            }
        });

        ICS.send("main","hello to");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ICS.unregister("second");
    }
}
