package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleTest {

    @Test
    public void verifyGoogleTitle() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);

        Assert.assertEquals(actualTitle, "Google");

        driver.quit();
    }
}