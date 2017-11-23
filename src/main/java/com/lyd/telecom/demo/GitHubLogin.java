package com.lyd.telecom.demo;

import com.lyd.telecom.config.SysConfig;
import com.lyd.telecom.enums.BrowserTypeEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class GitHubLogin {


    private static WebDriver webDriver;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        System.setProperty(BrowserTypeEnum.IE.getType(),SysConfig.PROJECT_PATH+"\\src\\\\main\\resources\\driver\\IEDriverServer.exe");
        webDriver = new InternetExplorerDriver();
//        System.setProperty("webdriver.chrome.driver", SysConfig.PROJECT_PATH+"\\src\\main\\resources\\driver\\chromedriver.exe");
//        webDriver = new ChromeDriver();

//        System.setProperty(BrowserTypeEnum.FIREFOX_BIN.getType(),"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//        System.setProperty("webdriver.firefox.marionette","G:\\\\Project\\\\My\\\\telecom-crm\\\\src\\\\main\\\\resources\\\\driver\\\\geckodriver_64.exe");
//        webDriver = new FirefoxDriver();

        webDriver.get("https://github.com/login");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.id("login_field")).sendKeys("yandong10086@sina.com");
        webDriver.findElement(By.id("password")).sendKeys("liang0377");
        webDriver.findElement(By.name("commit")).submit();

        Thread.sleep(10000);

        webDriver.quit();
    }
}
