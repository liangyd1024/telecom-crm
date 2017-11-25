package com.lyd.telecom.webdriver;

import com.lyd.telecom.enums.BrowserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import static com.lyd.telecom.enums.BrowserTypeEnum.CHROME;
import static com.lyd.telecom.config.SysConfig.PROJECT_PATH;
import static com.lyd.telecom.enums.BrowserTypeEnum.FIREFOX;
import static com.lyd.telecom.enums.BrowserTypeEnum.IE;
import static com.lyd.telecom.enums.BrowserTypeEnum.IE_32;

/**
 * 描述：驱动工厂
 * <p>
 * #
 * </p>
 * UserReqDto: lyd Date: 2017/11/23 ProjectName:crm Version: 1.0
 */
@Slf4j
public final class WebDriverFactory {


    public static WebDriver getWebDriver(BrowserTypeEnum browserTypeEnum){
        WebDriver webDriver = null;
        if(null == browserTypeEnum){
            webDriver = new InternetExplorerDriver();
        }else{
            switch (browserTypeEnum){
                case CHROME:
                    System.setProperty(CHROME.getType(),PROJECT_PATH+BrowserTypeEnum.WebDriverType.CHROME_WINDOWS.getPath());
                    webDriver = new ChromeDriver();
                    break;
                case FIREFOX:
                    System.setProperty(FIREFOX.getType(),PROJECT_PATH+BrowserTypeEnum.WebDriverType.FIREFOX_WINDOWS_64.getPath());
                    webDriver = new FirefoxDriver();
                    break;
                case IE:
                    System.setProperty(IE.getType(),PROJECT_PATH+BrowserTypeEnum.WebDriverType.IE.getPath());
                    webDriver = new InternetExplorerDriver();
                    break;
                case IE_32:
                    System.setProperty(IE_32.getType(),PROJECT_PATH+BrowserTypeEnum.WebDriverType.IE_32.getPath());
                    webDriver = new InternetExplorerDriver();
                    break;
                case HTML:
                    break;
                case SAFARI:
                    webDriver = new SafariDriver();
                    break;
            }
        }

        log.info("call getWebDriver webDriver:{}",webDriver);
        return webDriver;
    }


    public static void main(String[] args) {
        String a = null;
        switch (a){

        }
    }

}
