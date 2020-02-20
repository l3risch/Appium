package com.example.appium_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest {

    RemoteWebDriver driver;

    @Before
    public void setup(){

        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Christian\\AndroidStudioProjects\\Appium_Test\\app\\resources\\chromedriver_max.exe");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", "ce071827f8fb64270d");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("browserName", "Chrome");
        //capabilities.setCapability("showChromedriverLog", true);

        //capabilities.setCapability("chromedriveExecutable", "C:\\Users\\Christian\\AndroidStudioProjects\\Appium_Test\\app\\resources\\chromedriver_max.exe");


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

        driver.get("https://www.google.com");
        long navigationStart = (long) jsx.executeScript("return performance.timing.navigationStart;");
        long responseEnd = (long) jsx.executeScript("return performance.timing.responseEnd;");

        long loadingTimeDif = responseEnd-navigationStart;
        writer.write("Something");
        writer.write("Loading time: " + loadingTimeDif);
        System.out.println("\nLoading time: " + loadingTimeDif);
     }

    @After
    public void End() {
        driver.quit();
    }
}
