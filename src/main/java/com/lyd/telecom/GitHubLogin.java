package com.lyd.telecom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class GitHubLogin {


    private static WebDriver webDriver;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        System.setProperty("webdriver.ie.driver","C:\\Users\\aaa\\Desktop/IEDriverServer.exe");
        webDriver = new InternetExplorerDriver();
        webDriver.get("https://github.com/login");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.id("login_field")).sendKeys("yandong10086@sina.com");
        webDriver.findElement(By.id("password")).sendKeys("liang0377");
        webDriver.findElement(By.name("commit")).submit();
        webDriver.manage().timeouts().implicitlyWait(111111, TimeUnit.SECONDS);

//        webDriver.quit();
    }
}
