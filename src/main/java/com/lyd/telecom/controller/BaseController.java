package com.lyd.telecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aaa on 2017/11/22.
 */
@Controller
public class BaseController {

    public static String SMS_CODE;

    @RequestMapping("/welcome")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", 123);
        // return模板文件的名称，对应src/main/resources/templates/welcome.html
        return "welcome";
    }


}
