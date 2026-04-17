package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void verifyLogin() {
        System.out.println("Login test executed");
        Assert.assertTrue(true);
    }
}