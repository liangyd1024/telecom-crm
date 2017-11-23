package com.lyd.telecom.demo;

import com.lyd.telecom.config.SysConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
//        System.setProperty("webdriver.ie.driver","C:\\Users\\aaa\\Desktop/IEDriverServer.exe");
//        webDriver = new InternetExplorerDriver();
        System.setProperty("webdriver.chrome.driver", SysConfig.PROJECT_PATH+"\\src\\main\\resources\\driver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        WebDriver.Options options = webDriver.manage();
        webDriver.get("https://www.jd.com/");
//        options.window().setSize(new Dimension(0,0));
//        options.window().setPosition(new Point(0,0));
        options.timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Cookie cookie1 = new Cookie("__jdv","122270672|direct|-|none|-|1511329470090","/");
        webDriver.manage().addCookie(cookie1);
        Cookie cookie2 = new Cookie("o2Control","lastvisit=16|webp","/");
        webDriver.manage().addCookie(cookie2);
        Cookie cookie3 = new Cookie("o2-webp","true","/");
        webDriver.manage().addCookie(cookie3);
        Cookie cookie4 = new Cookie("wlfstk_smdl","zwz0e18bwzfnjllcbeq98ho1hkn1vmzu","/");
        webDriver.manage().addCookie(cookie4);
        Cookie cookie5 = new Cookie("_jrda","1","/");
        webDriver.manage().addCookie(cookie5);
        Cookie cookie6 = new Cookie("_jrdb","1511329500601","/");
        webDriver.manage().addCookie(cookie6);
        Cookie cookie7 = new Cookie("TrackID","1s_89OAWfn-ICz2ifDMUyC2aE8_JzeA-a8tLMPc9BUSGS8Hgn_vnXRYmb_SIooPvTiRdBaKqACOUxjVQdf14eUfB1_w4IFV9AX3SDJBH9vUc","/");
        webDriver.manage().addCookie(cookie7);
        Cookie cookie8 = new Cookie("pinId","kt_o-HojVu-bZUOy8uQY3A","/");
        webDriver.manage().addCookie(cookie8);
        Cookie cookie9 = new Cookie("pin","%E6%99%93%E9%9B%A80377","/");
        webDriver.manage().addCookie(cookie9);
        Cookie cookie10 = new Cookie("unick","%E6%99%93%E9%9B%A80377","/");
        webDriver.manage().addCookie(cookie10);
        Cookie cookie11 = new Cookie("_tp","7yJ3mDhxNJC0V4c1lQ8k872v4B8LZ8MaXH50JVkRt3s%3D","/");
        webDriver.manage().addCookie(cookie11);
        Cookie cookie12 = new Cookie("_pst","%E6%99%93%E9%9B%A80377","/");
        webDriver.manage().addCookie(cookie12);
        Cookie cookie13= new Cookie("ceshi3.com","103","/");
        webDriver.manage().addCookie(cookie13);
        Cookie cookie14 = new Cookie("thor","D5E8E0E896508CB0469C8520F3B0DF435049EA8AFF62A6E808102F4F1F9A9B13BED02948FF8441D44BDCB65EC0378295F89A2F66727DB034D5238E04E2346BDE054C1C4A656C5F3BE52C01BCDE856943F6FD2C728D11CF366CA6FDCAF0C56B599FCCC75AA6E0AE9F40E6DB80EE054AACA7A58A4E456099762A84EA99037D0232","/");
        webDriver.manage().addCookie(cookie14);
        Cookie cookie15 = new Cookie("__jda","122270672.1723144571.1478099022.1506679900.1511329470.11","/");
        webDriver.manage().addCookie(cookie15);
        Cookie cookie16 = new Cookie("__jdb","122270672.15.1723144571|11.1511329470","/");
        webDriver.manage().addCookie(cookie16);
        Cookie cookie17 = new Cookie("__jdc","122270672","/");
        webDriver.manage().addCookie(cookie17);
        Cookie cookie18 = new Cookie("3AB9D23F7A4B3C9B","MHUDSJKESLHEFGRK6RS2RCWCSLVASEFT6V5LM2MM2N3P53J7IZA7A4V6C66DM2W5JX7ROILGB4U3B44BUHWZ4QE3DE","/");
        webDriver.manage().addCookie(cookie18);
        Cookie cookie19 = new Cookie("__jdu","1723144571","/");
        webDriver.manage().addCookie(cookie19);
        Cookie cookie20 = new Cookie("userInfo2016","1","/");
        webDriver.manage().addCookie(cookie20);
        Cookie cookie21 = new Cookie("unpl","V2_ZzNtbUFXFxN3XBUEchsMBGJXG1oSBEcWcQ5GBngbXFczB0ENclRCFXMURlRnGl8UZwYZXEZcRxdFCEZkexhdBGYKFF9AX3MldDhFVEscbAVhABBdR1JHE3U4dlZ7KWzR56fGwvyB1bWis%2fdkeildNWEEFVlHVkYVdzgNOnpUXANkARJYR1NFFUUJdlc%3d","/");
        webDriver.manage().addCookie(cookie21);
        Cookie cookie22 = new Cookie("CCC_SE","ADC_%2fwprxhJ2PvMRQwhS86xRb951C4KuTG7mgQhWmsoFhONLlhieMboIvHTIbXq7TH8hrxBA1QnjVIOKKVEWwx%2fcH4WXs%2bp%2bmfJs4VAECJs2tvPlbPUys1ulK9Tng%2bCqJ5ZPSFp5pcRXad3nEbbuUhPZd4RC8mIqQ5NAFrRR25yiGOl0ifoEUPHjvtMwKdXZCnJeWhKJuqlleFYUnl4DRDRP7axLMWHIErG1PAPP7l1SWdDo9HOLv1D23HkysW%2b5DejDdwHaNCuvbJ%2bm6aGC9md1RQIwAc%2bUTNqkI2FlIZQBxv4XJRDcYqr4bGjvg8zbXzfsqmHN6%2fbYpi9d9EotfIC2A7HzAxHK%2bF7x9%2bWU1PS4F3YK9sPsgMaPwEjadGme7NEAkQhmA0J%2bm4QmdjTCnfgHp6uxbAav2t%2fjlERr%2fIEpTWJlMRN7rzozRZBgVKAYuUZKO7ijWLs33lxSqmCG3zDwnw%3d%3d","/");
        webDriver.manage().addCookie(cookie22);

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
