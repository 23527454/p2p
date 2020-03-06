package com.demo.p2p.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.Borrowmoney;
import com.demo.p2p.entity.Users;
import com.demo.p2p.service.BorrowmoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
@Controller
@RequestMapping("/brower")
public class BorrowmoneyController {

    @Resource
    private BorrowmoneyService borrowmoneyService;

    @RequestMapping(value = "/tohuankuan")
    public String tohuankuan(){
        return "";
    }

    /**
     * 进入还款列表
     * @param current
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/toHuanKuanListByUserId")
    public String toHuanKuanListByUserId(Integer current, Model model,HttpSession session){
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        }
        if (current==null){
            current=1;
        }

        Page<Borrowmoney> page=new Page<>(current,5);
        page.setRecords(borrowmoneyService.selHuanKuanList(user.getUid(),page));
        List<Borrowmoney> list=page.getRecords();
        Double ljjk=borrowmoneyService.sumBorrow(user.getUid());

        model.addAttribute("ljjk",ljjk);
        model.addAttribute("list",list);
        model.addAttribute("page",page);
        return "huankuanlist";
    }

    @RequestMapping("toaddborr.do")
    @ResponseBody
    public Object shang(Borrowmoney borrowmoney, HttpServletRequest request ) {
        Map<String,Object> map=new HashMap<String,Object>();
        System.out.println("shang----------------------");
        HttpSession session = request.getSession();
        int num = 0;
        Users users = null;

        users  = (Users) session.getAttribute("loginUser");
        if(users !=  null ){
            System.out.println(borrowmoney.getBtimelimit());
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar c = Calendar.getInstance();
            int inum = Integer.parseInt(borrowmoney.getBtimelimit());
            c.add(Calendar.MONTH,inum);
            borrowmoney.setBlimit(df.format(c.getTime()));
            borrowmoney.setBusername(users.getUid().toString());
            boolean real = borrowmoneyService.save(borrowmoney);
            map.put("jie",true);
            System.out.println(real);
        }else {
            map.put("jie",false);
            System.out.println(borrowmoney.getBrecommend());
        }
        return map;
    }
}

