package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import app.getxray.xray.testng.annotations.XrayTest;

public class LoginTest {

    @Test
    @XrayTest(key = "LOGI-80")
    public void verifyLogin() {
        System.out.println("Login test executed");
        Assert.assertTrue(true);
    }
}