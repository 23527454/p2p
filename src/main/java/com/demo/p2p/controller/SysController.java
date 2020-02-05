package com.demo.p2p.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysController {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }


}
