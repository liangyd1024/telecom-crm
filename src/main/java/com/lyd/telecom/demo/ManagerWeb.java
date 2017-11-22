package com.lyd.telecom.demo;

import com.lyd.telecom.enums.BrowserTypeEnum;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class ManagerWeb {


    private static WebDriver webDriver;

    public static void main(String[] args) throws InterruptedException {
        try {

            System.out.println("Hello World!");
//            System.setProperty(BrowserTypeEnum.IE.getType(),"G:\\Project\\My\\telecom-crm\\src\\main\\resources\\driver\\IEDriverServer.exe");
//            webDriver = new InternetExplorerDriver();
            System.setProperty(BrowserTypeEnum.CHROME.getType(),"G:\\Project\\My\\telecom-crm\\src\\main\\resources\\driver\\chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();

//            webDriver.get("http://192.168.200.54:8034/manage/home.jsp");
            webDriver.get("http://192.168.200.54:8034/manage/login.jsp");
//
//            //登录
            webDriver.findElement(By.name("system")).click();
            webDriver.findElement(By.id("sysUserName")).sendKeys("1");
            webDriver.findElement(By.id("sysPwd")).sendKeys("1");
            webDriver.findElement(By.xpath("//*[@id=\"system\"]/form/div[1]/input")).sendKeys("123456");
            webDriver.findElement(By.xpath("//*[@id=\"system\"]/form/button")).click();

            Thread.sleep(2000);

            //处理alert
            Alert alert = webDriver.switchTo().alert();
            if(null != alert){
                System.out.println(alert.getText());
                alert.accept();
            }


//            Cookie addCookie = new Cookie("SESSION","6f4f7f54-a5dd-4fe2-be31-615a05f4048e","/manage/");
//            webDriver.manage().addCookie(addCookie);

            Set<Cookie> cookies = webDriver.manage().getCookies();
            for(Cookie cookie : cookies){
                System.out.println(cookie);
            }
//            webDriver.navigate().to("http://192.168.200.54:8034/manage/home.jsp");

            WebElement menuFrame = webDriver.findElement(By.name("menuFrame"));
            webDriver.switchTo().frame(menuFrame);

            //点击菜单
            webDriver.findElement(By.xpath("//*[@id=\"tree\"]/ul/li[2]")).click();
            webDriver.findElement(By.xpath("//*[@id=\"tree\"]/ul/li[4]")).click();

            webDriver.switchTo().defaultContent();
            WebElement iframeWebElement = webDriver.findElement(By.cssSelector("#tab_20160825152217 > iframe"));
            webDriver.switchTo().frame(iframeWebElement);

            //输入数据
            webDriver.findElement(By.cssSelector("#queryForm > table > tbody > tr:nth-child(1) > td:nth-child(2) > input")).sendKeys("18116154560");
            webDriver.findElement(By.cssSelector("#queryForm > table > tbody > tr:nth-child(4) > td > button:nth-child(1)")).click();

            //获取数据
            WebElement userElement = webDriver.findElement(By.cssSelector("body > div.container-fluid.ng-scope > div:nth-child(2) > table > tbody > tr"));
            List<WebElement> userElementList = userElement.findElements(By.tagName("td"));
            for(WebElement element : userElementList){
                System.out.println(element.getText());
            }

        }finally {
//            webDriver.quit();
        }

    }
}
