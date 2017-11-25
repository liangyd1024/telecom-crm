package com.lyd.telecom.demo;

import com.lyd.telecom.config.SysConfig;
import com.lyd.telecom.enums.BrowserTypeEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class Jd {


    private static WebDriver webDriver;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        System.setProperty(BrowserTypeEnum.IE.getType(),SysConfig.PROJECT_PATH+"\\src\\main\\resources\\driver\\IEDriverServer_32.exe");
        webDriver = new InternetExplorerDriver();
//        System.setProperty(BrowserTypeEnum.CHROME.getType(), SysConfig.PROJECT_PATH+"\\src\\main\\resources\\driver\\chromedriver.exe");
//        webDriver = new ChromeDriver();
        WebDriver.Options options = webDriver.manage();
        webDriver.get("https://www.jd.com/");
//        options.window().setSize(new Dimension(0,0));
//        options.window().setPosition(new Point(0,0));
        options.timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Set<Cookie> cookies = webDriver.manage().getCookies();
        for(Cookie cookie : cookies){
            System.out.println(cookie);
        }
        webDriver.navigate().to("https://www.jd.com/");

        String windowHandle = webDriver.getWindowHandle();
        System.out.println("windowHandle:"+windowHandle);

        WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"settleup\"]/div[1]/a"));
        webElement.click();

        Thread.sleep(2000);

        windowHandle = webDriver.getWindowHandle();
        System.out.println("windowHandle:"+windowHandle);

        for(String handle : webDriver.getWindowHandles()){
//            if(!handle.equals(windowHandle)){
//                webDriver.close();
//            }
            webDriver.switchTo().window(handle);
        }

        webDriver.findElement(By.id("key")).sendKeys("衣服");
        webDriver.findElement(By.xpath("/html/body/div[3]/div[2]/div/input[2]")).click();

        Thread.sleep(2000);

        windowHandle = webDriver.getWindowHandle();
        System.out.println("windowHandle:"+windowHandle);

        for(String handle : webDriver.getWindowHandles()){
//            if(!handle.equals(windowHandle)){
//                webDriver.close();
//            }
            webDriver.switchTo().window(handle);
        }

        System.out.println("start gl-i-wrap");
        List<WebElement> imgElementList = webDriver.findElements(By.className("gl-i-wrap"));
        System.out.println("end gl-i-wrap:"+imgElementList.size());
        for(WebElement webElement1 : imgElementList){
            String imgUrl = webElement1.findElement(By.className("p-img")).findElement(By.className("err-product")).getAttribute("src");
            System.out.println("imgUrl:"+imgUrl);

            String price = webElement1.findElement(By.className("p-price")).findElement(By.tagName("i")).getText();
            System.out.println("price:"+price);
        }

        webDriver.quit();
    }
}
