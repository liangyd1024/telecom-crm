package com.lyd.telecom.cache;

import com.lyd.telecom.model.req.FileReqDto;
import com.lyd.telecom.model.resp.CertNoRespDto;
import com.lyd.telecom.model.resp.IccIdRespDto;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaa on 2017/11/23.
 */
public class SessionCache {

    public static Map<String,String> SMS_CODE_MAP = new HashMap<>();

    public static Map<String,WebDriver> WEB_DRIVER_MAP = new HashMap<>();

    public static Map<String,FileReqDto> UPLOAD_FILE_MAP = new HashMap<>();

    public static Map<String,CertNoRespDto> CERT_NO_FILE_RESULT_MAP = new HashMap<>();

    public static Map<String,IccIdRespDto> ICCID_FILE_RESULT_MAP = new HashMap<>();

}
