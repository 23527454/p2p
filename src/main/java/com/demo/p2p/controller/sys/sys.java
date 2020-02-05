package com.demo.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sys {


    @RequestMapping(value = "/index")
    public String index(){
        System.out.println("index=====");
        return "index";
    }


    public int ltx(){
        return 1;
    }

}
