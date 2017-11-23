package com.lyd.telecom.controller;

import com.lyd.telecom.config.SysConfig;
import com.lyd.telecom.enums.BrowserTypeEnum;
import com.lyd.telecom.webdriver.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * Created by aaa on 2017/11/22.
 */
@Controller
@Slf4j
public class WebDriverController {

    private static WebDriver webDriver;

//    @PostConstruct
    public void init(){
        System.setProperty(
                BrowserTypeEnum.CHROME.getType(),
                SysConfig.PROJECT_PATH+"\\src\\main\\resources\\driver\\chromedriver.exe");
    }

    @RequestMapping("/index")
    public String index() {
        return "phone";
    }

    @RequestMapping("/toEleLogin")
    public String toEleLogin(String phone){
        log.info("call toEleLogin phone:{}",phone);
        new EleThread(phone).start();
        return "sms";
    }

    @RequestMapping("/eleLogin")
    public String eleLogin(String smsCode, ModelMap modelMap){
        log.info("call toEleLogin smsCode:{}",smsCode);
        BaseController.SMS_CODE = smsCode;
        modelMap.put("result","登录成功");
        return "result";
    }

    @RequestMapping("/loadUrl")
    public String loadUrl(String url,ModelMap modelMap){
        log.info("call loadUrl:{}",url);
        new LoadUrlThread(url).start();
        modelMap.put("result","跳转成功");
        return "result";
    }

    public static class LoadUrlThread extends Thread{

        private String url;

        public LoadUrlThread(String url){
            this.url = url;
        }

        @Override
        public void run() {
            webDriver.navigate().to(url);
        }
    }


    public static class EleThread extends Thread{

        private String phone;

        public EleThread(String phone){
            this.phone = phone;
        }

        @Override
        public void run() {

            if(null != webDriver){
                webDriver.quit();
            }
            webDriver = WebDriverFactory.getWebDriver(BrowserTypeEnum.IE);
            webDriver.get("https://h5.ele.me/login/");
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement phoneWebElement = webDriver.findElement(By.cssSelector("body > div.App-1EAON > div.App-3Q8Qb > div:nth-child(2) > form > section:nth-child(1) > input[type=\"tel\"]"));
            phoneWebElement.sendKeys(phone);

            WebElement smsCodeButtonWebElement = webDriver.findElement(By.className("CountButton-3e-kd"));
            smsCodeButtonWebElement.click();

            while(null == BaseController.SMS_CODE){
                log.info("wait smsCode...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("call run sleep InterruptedException:{}",e);
                }
            }

            WebElement smsCodeWebElement = webDriver.findElement(By.cssSelector("body > div.App-1EAON > div.App-3Q8Qb > div:nth-child(2) > form > section:nth-child(2) > input[type=\"tel\"]"));
            smsCodeWebElement.sendKeys(BaseController.SMS_CODE);

            WebElement loginButtonWebElement = webDriver.findElement(By.className("SubmitButton-2wG4T"));
            loginButtonWebElement.click();

//            Alert alert = webDriver.switchTo().alert();
//            if(null != alert){
//                log.info("call alert text:{}",alert.getText());
//                alert.accept();
//            }

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                log.error("call run sleep InterruptedException:{}",e);
            }

            WebElement searchWebElement = webDriver.findElement(By.cssSelector("body > div.wrapper > div:nth-child(3) > div.search-wrapper > div > a"));
            searchWebElement.click();

            WebElement searchInputWebElement = webDriver.findElement(By.cssSelector("body > div > section > form > input"));
            searchInputWebElement.sendKeys("一点点");

            WebElement searchButtonWebElement = webDriver.findElement(By.cssSelector("body > div > section > form > button"));
            searchButtonWebElement.click();

        }
    }



    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }

}
