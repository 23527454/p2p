package com.demo.p2p.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/backend")
public class BackendController {

    @RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "view/bk_login";
    }
}
