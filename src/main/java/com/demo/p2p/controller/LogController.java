package com.demo.p2p.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
@Controller
@RequestMapping("/log")
public class LogController {
    public void test2(){
        System.out.println(11111);
    }
}

