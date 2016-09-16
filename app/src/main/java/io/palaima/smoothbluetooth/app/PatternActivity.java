package io.palaima.smoothbluetooth.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.palaima.bluetooth.app.R;

import io.palaima.smoothbluetooth.SmoothBluetooth;

public class PatternActivity extends AppCompatActivity {

    private Button mSendPattern0;
    private Button mSendPattern1;
    private Button mSendPattern2;
    private Button mSendPattern3;
    private Button mSendPattern4;
    private Button mSendPattern5;
    private Button mSendPattern6;
    private Button mSendPattern7;
    private Button mSendPattern8;
    private Button mSendPattern9;
    private Button mSendPattern10;
    private Button mSendPattern11;
    private Button mSendPattern12;
    private Button mSendPattern13;
    private Button mSendPattern14;
    private Button mSendPattern15;
    private Button mSendPattern16;
    private Button mSendPattern17;

    private SmoothBluetooth mSmoothBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSmoothBluetooth = MainActivity.getmSmoothBluetooth();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

        mSendPattern0 = (Button) findViewById(R.id.sendPattern0);
        mSendPattern0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p0", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern1 = (Button) findViewById(R.id.sendPattern1);
        mSendPattern1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p1", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern2 = (Button) findViewById(R.id.sendPattern2);
        mSendPattern2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p2", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern3 = (Button) findViewById(R.id.sendPattern3);
        mSendPattern3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p3", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern4 = (Button) findViewById(R.id.sendPattern4);
        mSendPattern4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p4", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern5 = (Button) findViewById(R.id.sendPattern5);
        mSendPattern5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p5", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern6 = (Button) findViewById(R.id.sendPattern6);
        mSendPattern6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p6", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern7 = (Button) findViewById(R.id.sendPattern7);
        mSendPattern7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p7", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern8 = (Button) findViewById(R.id.sendPattern8);
        mSendPattern8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p8", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern9 = (Button) findViewById(R.id.sendPattern9);
        mSendPattern9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p9", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern10 = (Button) findViewById(R.id.sendPattern10);
        mSendPattern10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p10", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern11 = (Button) findViewById(R.id.sendPattern11);
        mSendPattern11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p11", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern12 = (Button) findViewById(R.id.sendPattern12);
        mSendPattern12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p12", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern13 = (Button) findViewById(R.id.sendPattern13);
        mSendPattern13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p13", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern14 = (Button) findViewById(R.id.sendPattern14);
        mSendPattern14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p14", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern15 = (Button) findViewById(R.id.sendPattern15);
        mSendPattern15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p15", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern16 = (Button) findViewById(R.id.sendPattern16);
        mSendPattern16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p16", MainActivity.getmCRLFBox().isChecked());
            }
        });

        mSendPattern17 = (Button) findViewById(R.id.sendPattern17);
        mSendPattern17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSmoothBluetooth.send("p17", MainActivity.getmCRLFBox().isChecked());
            }
        });
    }
}
