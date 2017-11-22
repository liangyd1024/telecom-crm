package com.lyd.telecom.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/11/22 ProjectName:crm Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum BrowserTypeEnum {

    CHROME("webdriver.chrome.driver","谷歌"),
    IE("webdriver.ie.driver","IE"),
    FIREFOX("webdriver.firefox.driver","火狐"),
    FIREFOX_BIN("webdriver.firefox.bin","火狐"),

    ;


    private String type;

    private String desc;

}
