package com.lyd.telecom.controller;

import com.lyd.telecom.enums.BrowserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaa on 2017/11/22.
 */
@Controller
@Slf4j
public class WebDriverController {

    private static WebDriver webDriver;

    @PostConstruct
    public void init(){
        System.setProperty(
                BrowserTypeEnum.CHROME.getType(),
                "D:\\工作\\Project\\My\\telecom-crm\\src\\main\\resources\\driver\\chromedriver.exe");

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
            webDriver = new ChromeDriver();
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

            WebElement orderWebElement = webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/footer/div/a[3]/svg"));
            orderWebElement.click();

        }
    }


    public static void main(String[] args) {
        WebDriverController webDriverController = new WebDriverController();
        webDriverController.init();

        EleThread eleThread = new EleThread ("18116154560");
        eleThread.run();
    }

}
