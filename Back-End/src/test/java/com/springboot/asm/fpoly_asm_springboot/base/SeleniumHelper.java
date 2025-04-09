package com.springboot.asm.fpoly_asm_springboot.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    private void testChromeDriver() {
        driver.get("https://www.google.com");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
