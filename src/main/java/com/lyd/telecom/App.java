package com.lyd.telecom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {


    private static WebDriver webDriver;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        System.setProperty("webdriver.chrome.driver","D:\\eclipsework\\test\\driver\\chromedriver.exe");
        webDriver = new FirefoxDriver();
        webDriver.get("http://www.baidu.com");
        webDriver.findElement(By.id("kw")).sendKeys("hello Selenium");
        webDriver.findElement(By.id("su")).click();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.quit();
    }
}
