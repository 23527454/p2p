package com.demo.p2p.ht.controller;


import com.demo.p2p.ht.service.Bk_BorrowmoneyService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/brower")
public class Bk_BorrowmoneyController {
    @Resource
    private Bk_BorrowmoneyService borrowmoneyService;

    @RequestMapping(value = "/hjyList")
    public String hjyList(){
        return "view/Borrowmoneylist";
    }
}

