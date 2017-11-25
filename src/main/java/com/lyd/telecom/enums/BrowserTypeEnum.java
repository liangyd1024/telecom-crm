package com.lyd.telecom.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * UserReqDto: lyd Date: 2017/11/22 ProjectName:crm Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum BrowserTypeEnum {

    CHROME("webdriver.chrome.driver","谷歌"),

    IE("webdriver.ie.driver","IE"),
    IE_32("webdriver.ie.driver","IE32"),

    HTML("","无浏览器"),

    SAFARI("","SAFARI"),

    FIREFOX("webdriver.firefox.marionette","火狐驱动"),
    FIREFOX_BIN("webdriver.firefox.bin","火狐"),

    ;


    private String type;

    private String desc;


    @Getter
    @AllArgsConstructor
    public enum WebDriverType{

        IE("/src/main/resources/driver/IEDriverServer.exe","IE驱动"),
        IE_32("/src/main/resources/driver/IEDriverServer_32.exe","IE32驱动"),

        CHROME_WINDOWS("/src/main/resources/driver/chromedriver.exe","windows谷歌驱动"),

        FIREFOX_WINDOWS_64("/src/main/resources/driver/geckodriver_64.exe","windows64火狐驱动"),
        FIREFOX_WINDOWS_32("/src/main/resources/driver/geckodriver_32.exe","windows32火狐驱动"),

        ;

        private String path;

        private String desc;
    }

}
