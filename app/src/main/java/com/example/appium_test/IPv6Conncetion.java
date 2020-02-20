package com.example.appium_test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;


public class IPv6Conncetion {


    public static void buildConnection() throws MalformedURLException {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("./resources/StatisticalData.txt", false));
            writer.write("Something");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

