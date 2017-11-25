package com.lyd.telecom.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;

import java.util.Set;

/**
 * Created by aaa on 2017/11/24.
 */
@Slf4j
public final class WebDriverUtils {

    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private static WebDriverUtils ME = new WebDriverUtils();

    private static WebDriver webDriver = webDriverThreadLocal.get();

    public static WebDriverUtils me(WebDriver webDriver) {
        webDriverThreadLocal.set(webDriver);
        return ME;
    }

    public static boolean open(String url){
        try {
            webDriver.get(url);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    /**
     * 判断元素对象是否存在
     */
    public static boolean isExistWebElement(By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * 元素组是否存在
     */
    public static boolean isExistWebElements(By by) {
        return webDriver.findElements(by).size() > 0;
    }

    /**
     * 根据id获取元素
     */
    public static WebElement findById(String id) {
        WebElement element = null;
        if (isExistWebElement(By.id(id))) {
            element = webDriver.findElement(By.id(id));
        }
        return element;
    }

    /**
     * 从某组元素组中获取需要的某个元素
     */
    public static WebElement findElementsByIndex(By by, int index) {
        WebElement element = null;
        if (isExistWebElements(by)) {
            element = webDriver.findElements(by).get(index);
        }
        return element;
    }

    public static boolean sendKey(By by,String key){
        try {
            webDriver.findElement(by).sendKeys(key);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean click(By by){
        try {
            webDriver.findElement(by).click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean submit(By by){
        try {
            webDriver.findElement(by).submit();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * 获取框判断
     */
    public static Alert getAlertPresent() {
        try {
            return webDriver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    /**
     * 弹出框判断
     */
    public static boolean isAlertPresent() {
        return null != getAlertPresent();
    }

    /**
     * 确认弹框
     */
    public static void acceptAlert() {
        Alert alert = getAlertPresent();
        if (null != alert) {
            alert.accept();
        }
    }

    /**
     * 取消弹框
     */
    public static void dismissAlert() {
        Alert alert = getAlertPresent();
        if (null != alert) {
            alert.dismiss();
        }
    }

    public static void switchToWindow(String windowTitle) {
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String handle : windowHandles) {
            webDriver.switchTo().window(handle);
            String title = webDriver.getTitle();
            if (title.equals(windowTitle)){
                break;
            }
        }
    }

    public static void switchToFrame(String frameId){
        webDriver.switchTo().frame(frameId);
    }

}
