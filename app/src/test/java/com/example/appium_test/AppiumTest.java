package com.example.appium_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.AppiumDriver;


public class AppiumTest {

    RemoteWebDriver driver;

    @Before
    public void setup(){

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "ce071827f8fb64270d");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("browserName", "Chrome");


        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver(url, capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


}

    @Test
    public void testWebsites() throws IOException {

        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        FileWriter writer = new FileWriter("./resources/StatisticalData.txt", false);

        List<String> ipv6websites = new ArrayList<String>();
        ipv6websites.add("https://www.google.com");
        ipv6websites.add("https://www.youtube.com");
        ipv6websites.add("https://www.facebook.com");
        ipv6websites.add("https://www.wikipedia.org");
        ipv6websites.add("https://www.Netflix.com");
        ipv6websites.add("https://www.Blogspot.com");
        ipv6websites.add("https://www.bing.com");
        ipv6websites.add("https://www.instagram.com");
        ipv6websites.add("https://www.office.com");
        ipv6websites.add("https://www.google.com.hk");
        ipv6websites.add("https://www.yandex.ru");
        ipv6websites.add("https://www.dropbox.com");
        ipv6websites.add("https://www.linkedin.com");
        ipv6websites.add("https://www.google.de");
        ipv6websites.add("https://www.google.com.br");
        ipv6websites.add("https://www.medium.com");
        ipv6websites.add("https://www.cnn.com");
        ipv6websites.add("https://www.google.ru");
        ipv6websites.add("https://www.google.it");
        ipv6websites.add("https://www.google.cn");

        //Set iterations
        int iterations = 2;
        long[][] Messungen = new long[ipv6websites.size()][iterations];

        List<Long> einzelmessungen = new ArrayList<Long>();

        int countloop = 0;
        int countwebsite = 0;
        long loadingTime = 0;

        writer.write("###########################################################################################");


        while(countloop < iterations)
        {

            for (String ipv6website : ipv6websites)
            {
                long navigationStart = (long) jsx.executeScript("return performance.timing.navigationStart;");
                long responseEnd = (long) jsx.executeScript("return performance.timing.responseEnd;");
                driver.get(ipv6website);
                long loadingTimeDif = responseEnd-navigationStart;

                //Speichern der Messergebnisse für die einzelnen Webseiten
                switch(ipv6website)
                {
                    case "https://www.google.com":
                        Messungen[0][countloop] = loadingTimeDif;
                        break;
                    case "https://www.youtube.com":
                        Messungen[1][countloop] = loadingTimeDif;
                        break;
                    case "https://www.facebook.com":
                        Messungen[2][countloop] = loadingTimeDif;
                        break;
                    case "https://www.wikipedia.org":
                        Messungen[3][countloop] = loadingTimeDif;
                        break;
                    case "https://www.Netflix.com":
                        Messungen[4][countloop] = loadingTimeDif;
                        break;
                    case "https://www.Blogspot.com":
                        Messungen[5][countloop] = loadingTimeDif;
                        break;
                    case "https://www.bing.com":
                        Messungen[6][countloop] = loadingTimeDif;
                        break;
                    case "https://www.instagram.com":
                        Messungen[7][countloop] = loadingTimeDif;
                        break;
                    case "https://www.office.com":
                        Messungen[8][countloop] = loadingTimeDif;
                        break;
                    case "https://www.google.com.hk":
                        Messungen[9][countloop] = loadingTimeDif;
                        break;
                    case "https://www.yandex.ru":
                        Messungen[10][countloop] = loadingTimeDif;
                        break;
                    case "https://www.dropbox.com":
                        Messungen[11][countloop] = loadingTimeDif;
                        break;
                    case "https://linkedin.com":
                        Messungen[12][countloop] = loadingTimeDif;
                        break;
                    case "https://www.google.de":
                        Messungen[13][countloop] = loadingTimeDif;
                        break;
                    case "https://www.google.com.br":
                        Messungen[14][countloop] = loadingTimeDif;
                        break;
                    case "https://www.medium.com":
                        Messungen[15][countloop] = loadingTimeDif;
                        break;
                    case "https://www.cnn.com":
                        Messungen[16][countloop] = loadingTimeDif;
                        break;
                    case "https://www.google.ru":
                        Messungen[17][countloop] = loadingTimeDif;
                        break;
                    case "https://www.google.it":
                        Messungen[18][countloop] = loadingTimeDif;
                        break;
                    case "https://www.google.cn":
                        Messungen[19][countloop] = loadingTimeDif;
                        break;
                }


                writer.write("\n"+countloop + ") " + "Loading Time for " + ipv6website + ": " + loadingTimeDif );

                //Summierung der Ladezeiten
                loadingTime = loadingTime + loadingTimeDif;
                einzelmessungen.add(loadingTimeDif);

                countwebsite++;

            }

            writer.write("\n");
            countloop++;
            System.out.println("Loop: " + countloop);

        }
        //Berechnung statistischer Kennzahlen
        countwebsite = countwebsite / countloop;

        long[] websitemeans = new long[20];
        long[] websitevariances = new long[20];
        long[] websitestds = new long[20];
        for(int i = 0; i < countwebsite; i++)
        {
            List<Long> einzelmessungenweb = new ArrayList<Long>();
            for(int j = 0; j < countloop; j++)
            {
                websitemeans[i] += Messungen[i][j];
                einzelmessungenweb.add(Messungen[i][j]);
            }
            websitemeans[i] = websitemeans[i]/countloop;

            long sumResults = 0;
            for(long result : einzelmessungen)
            {
                sumResults += (result - websitemeans[i])*(result-websitemeans[i]);
            }
            websitevariances[i] = sumResults / countloop;

            websitestds[i] = (long) Math.sqrt(websitevariances[i]);
        }

        //Calculate the overall mean
        long overallmean = loadingTime / (countwebsite*countloop);

        //Calculate the overall variance
        long sumResults = 0;
        for(long result : einzelmessungen)
        {
            sumResults += (result - overallmean)*(result-overallmean);
        }
        long overallvariance = sumResults / (countloop * countwebsite);

        //Calculate the overall standard deviation
        long overallstd = (long) Math.sqrt(overallvariance);

        writer.write("\n----------------------------------------------------------------");
        writer.write("\nMeans:\n");

        for(int i = 0; i < 20; i++)
        {
            writer.write("\n" + ipv6websites.get(i) + " Mean: " + websitemeans[i]);
        }


        writer.write("\n----------------------------------------------------------------");
        writer.write("\nStandard Deviations:\n");
        for(int i = 0; i < 20; i++)
        {
            writer.write("\n" + ipv6websites.get(i) + " STD: " + websitestds[i]);
        }

        writer.write("\n----------------------------------------------------------------\n");
        writer.write("\nOverall Mean: " + overallmean);
        writer.write("\nOverall Variance: " + overallvariance);
        writer.write("\nOverall Standard Deviation: " + overallstd);

        writer.close();
        driver.quit();
    }



    @After
    public void End() {
        driver.quit();
    }
}
