package com.lyd.telecom.controller;

import com.lyd.telecom.cache.SessionCache;
import com.lyd.telecom.enums.BrowserTypeEnum;
import com.lyd.telecom.webdriver.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lyd.telecom.model.req.UserReqDto;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaa on 2017/11/22.
 */
@Controller
@Slf4j
public class CrmController {


    @RequestMapping("/toCrmLogin")
    public String toCrmLogin(UserReqDto userReqDto, HttpSession httpSession){
        log.info("call toCrmLogin userReqDto:{},sessionId:{}",userReqDto,httpSession.getId());
        new CrmThread(httpSession.getId(),userReqDto).start();
        return "sms";
    }

    @RequestMapping("/crmLogin")
    public String eleLogin(String smsCode, ModelMap modelMap, HttpSession httpSession){
        log.info("call crmLogin smsCode:{}",smsCode);
        httpSession.setAttribute("smsCode",smsCode);
        SessionCache.SMS_CODE_MAP.put(httpSession.getId(),smsCode);
        modelMap.put("result","正在进行登录中");
        return "result";
    }

    public static class CrmThread extends Thread{

        private UserReqDto userReqDto;

        private String sessionId;

        public CrmThread(String sessionId, UserReqDto userReqDto){
            this.sessionId = sessionId;
            this.userReqDto = userReqDto;
        }

        @Override
        public void run() {

            WebDriver webDriver = WebDriverFactory.getWebDriver(BrowserTypeEnum.IE_32);
            SessionCache.WEB_DRIVER_MAP.put(sessionId,webDriver);

            webDriver.get("https://www.sh.ctc.com/");
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //To click on "Continue to this website (not recommended)." link to load original website.
//            webDriver.navigate().to("javascript:document.getElementById('overridelink').click()");

            WebElement dynamicWebElement = webDriver.findElement(By.className("dynamic"));
            dynamicWebElement.click();

            WebElement userNameWebElement = webDriver.findElement(By.id("m_userid"));
            userNameWebElement.sendKeys(userReqDto.getUserNo());
            WebElement passwordWebElement = webDriver.findElement(By.id("m_password"));
            passwordWebElement.sendKeys(userReqDto.getPwd());
            WebElement sendsmsWebElement = webDriver.findElement(By.id("sendsms"));
            sendsmsWebElement.click();

            while(!SessionCache.SMS_CODE_MAP.containsKey(sessionId)){
                log.info("wait smsCode...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    log.error("call run sleep InterruptedException:{}",e);
                }
            }

            WebElement smsCodeWebElement = webDriver.findElement(By.id("mobilesec"));
            smsCodeWebElement.sendKeys(SessionCache.SMS_CODE_MAP.get(sessionId));
            SessionCache.SMS_CODE_MAP.remove(sessionId);

            WebElement loginButtonWebElement = webDriver.findElement(By.xpath("//*[@id=\"form3\"]/table/tbody/tr[4]/td/input"));
            loginButtonWebElement.click();

            WebElement crmNewWebElement = webDriver.findElement(By.xpath("//*[@id=\"ns_Z7_IPDA1842L8L970AHUNR4IE0002__wb_ul\"]/li[1]/a"));
            crmNewWebElement.click();

            Alert alert = webDriver.switchTo().alert();
            if(null != alert){
                log.info("call alert text:{}",alert.getText());
                alert.accept();
            }


        }
    }



    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }

}
