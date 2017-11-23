package com.lyd.telecom.cache;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaa on 2017/11/23.
 */
public class SessionCache {

    public static Map<String,String> SMS_CODE_MAP = new HashMap<>();

    public static Map<String,WebDriver> WEB_DRIVER_MAP = new HashMap<>();

}
