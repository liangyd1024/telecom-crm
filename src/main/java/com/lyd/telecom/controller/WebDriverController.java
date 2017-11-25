package com.lyd.telecom.controller;

import com.lyd.telecom.cache.SessionCache;
import com.lyd.telecom.enums.BrowserTypeEnum;
import com.lyd.telecom.webdriver.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaa on 2017/11/22.
 */
@Controller
@Slf4j
public class WebDriverController {


    @RequestMapping("/index")
    public String index() {
        return "phone";
    }

    @RequestMapping("/toEleLogin")
    public String toEleLogin(String phone, HttpSession httpSession){
        log.info("call toEleLogin phone:{},sessionId:{}",phone,httpSession.getId());
        new EleThread(httpSession.getId(),phone).start();
        return "sms";
    }

    @RequestMapping("/eleLogin")
    public String eleLogin(String smsCode, ModelMap modelMap, HttpSession httpSession){
        log.info("call toEleLogin smsCode:{}",smsCode);
        httpSession.setAttribute("smsCode",smsCode);
        SessionCache.SMS_CODE_MAP.put(httpSession.getId(),smsCode);
        modelMap.put("result","登录成功");
        return "result";
    }

    @RequestMapping("/loadUrl")
    public String loadUrl(String url,ModelMap modelMap, HttpSession httpSession){
        log.info("call loadUrl:{}",url);
        new LoadUrlThread(httpSession.getId(),url).start();
        modelMap.put("result","跳转成功");
        return "result";
    }

    public static class LoadUrlThread extends Thread{

        private String url;

        private String sessionId;

        public LoadUrlThread(String sessionId,String url){
            this.sessionId = sessionId;
            this.url = url;
        }

        @Override
        public void run() {
            SessionCache.WEB_DRIVER_MAP.get(sessionId).navigate().to(url);
        }
    }


    public static class EleThread extends Thread{

        private String phone;

        private String sessionId;

        public EleThread(String sessionId,String phone){
            this.sessionId = sessionId;
            this.phone = phone;
        }

        @Override
        public void run() {

            WebDriver webDriver = WebDriverFactory.getWebDriver(BrowserTypeEnum.IE_32);
            SessionCache.WEB_DRIVER_MAP.put(sessionId,webDriver);

            webDriver.get("https://h5.ele.me/login/");
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //To click on "Continue to this website (not recommended)." link to load original website.
            webDriver.navigate().to("javascript:document.getElementById('overridelink').click()");

            WebElement phoneWebElement = webDriver.findElement(By.cssSelector("body > div.App-1EAON > div.App-3Q8Qb > div:nth-child(2) > form > section:nth-child(1) > input[type=\"tel\"]"));
            phoneWebElement.sendKeys(phone);

            WebElement smsCodeButtonWebElement = webDriver.findElement(By.className("CountButton-3e-kd"));
            smsCodeButtonWebElement.click();

            while(!SessionCache.SMS_CODE_MAP.containsKey(sessionId)){
                log.info("wait smsCode...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("call run sleep InterruptedException:{}",e);
                }
            }

            WebElement smsCodeWebElement = webDriver.findElement(By.cssSelector("body > div.App-1EAON > div.App-3Q8Qb > div:nth-child(2) > form > section:nth-child(2) > input[type=\"tel\"]"));
            smsCodeWebElement.sendKeys(SessionCache.SMS_CODE_MAP.get(sessionId));
            SessionCache.SMS_CODE_MAP.remove(sessionId);

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
