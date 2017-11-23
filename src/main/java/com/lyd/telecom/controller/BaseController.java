package com.lyd.telecom.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.Map;

/**
 * Created by aaa on 2017/11/22.
 */
@Controller
@Slf4j
public class BaseController {

    public static String SMS_CODE;

    @RequestMapping("/welcome")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", 123);
        // return模板文件的名称，对应src/main/resources/templates/welcome.html
        return "welcome";
    }


    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public String uploadFile(MultipartHttpServletRequest request,ModelMap modelMap){

        try {
            log.info("call uploadFile ");
            Map<String,MultipartFile> multipartFileMap = request.getFileMap();
            log.info("call uploadFile multipartFileMap:{}",multipartFileMap);


            for (Map.Entry<String,MultipartFile> entry :multipartFileMap.entrySet()){
                File tempFile = File.createTempFile("temp",null);
                entry.getValue().transferTo(tempFile);

                FileReader fileReader = new FileReader(tempFile);
                BufferedReader bufferedReader = new BufferedReader (fileReader);
                String line;
                while (null != (line = bufferedReader.readLine())){
                    log.info("line:{}",line);
                }
                tempFile.deleteOnExit();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        modelMap.put("result","上传成功");
        return "result";
    }

}
