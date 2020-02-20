package com.example.appium_test;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiDex.install(this);

        PackageManager m = getPackageManager();
        String s = getPackageName();
        PackageInfo p = null;
        try {
            p = m.getPackageInfo(s, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        s = p.applicationInfo.dataDir;

        System.out.println("Current working dir: " + s);

        RelativeLayout lay = new RelativeLayout(this);
        Button ipv6 = new Button(this);
        Button proxy = new Button(this);

        lay.setBackgroundColor(Color.WHITE);

        ipv6.setBackgroundColor(Color.LTGRAY);
        ipv6.setText("IPv6");
        ipv6.setId(1);

        proxy.setBackgroundColor(Color.LTGRAY);
        proxy.setText("Proxy");
        proxy.setId(2);

        RelativeLayout.LayoutParams directDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        directDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        directDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        lay.addView(ipv6, directDetails);

        RelativeLayout.LayoutParams proxyDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        proxyDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        proxyDetails.addRule(RelativeLayout.BELOW, ipv6.getId());
        proxyDetails.setMargins(0, 150, 0, 0);

        lay.addView(proxy, proxyDetails);

        setContentView(lay);

        ipv6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    IPv6Conncetion.buildConnection();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

        });

        proxy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });

    }
}