package com.lyd.telecom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class ManagerWeb {


    private static WebDriver webDriver;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        System.setProperty("webdriver.ie.driver","C:\\Users\\lyd\\Desktop\\IEDriverServer.exe");
        webDriver = new InternetExplorerDriver();
        webDriver.get("http://192.168.200.54:8034/manage/login.jsp");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.name("system")).click();
        webDriver.findElement(By.id("sysUserName")).sendKeys("1");
        webDriver.findElement(By.id("sysPwd")).sendKeys("1");
        webDriver.findElement(By.xpath("//*[@id=\"system\"]/form/div[1]/input")).sendKeys("123456");
        webDriver.findElement(By.xpath("//*[@id=\"system\"]/form/button")).click();
        webDriver.manage().timeouts().implicitlyWait(111111, TimeUnit.SECONDS);

//        webDriver.quit();
    }
}
