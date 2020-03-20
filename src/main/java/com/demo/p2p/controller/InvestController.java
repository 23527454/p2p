package com.demo.p2p.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.Biao;
import com.demo.p2p.entity.Product;
import com.demo.p2p.service.BiaoService;
import com.demo.p2p.service.InvestinfoService;
import com.demo.p2p.service.ProductService;
import com.demo.p2p.service.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/invest")
public class InvestController {
    @Resource
    private InvestinfoService investinfoService;
    @Resource
    private TradeService tradeService;
    @Resource
    private BiaoService biaoService;
    @Resource
    private ProductService productService;

    @RequestMapping(value = "/investSel")
    public String investSel(String item, String param, Integer current, Model model, HttpSession session){
        //查询biao表所有数据，将数据显示到页面（项目分类）
        List<Biao> biaos=biaoService.list();
        model.addAttribute("biaos",biaos);

        //查询条件
        Map<String,Object> map=new HashMap<>();
        //为第一次跳转赋值
        if(current==null){
            current=1;
        }
        //获取页面传来的查询条件
        if(item!=null){
            if (item.equals("itemtype")){       //选择项目类型
                if (param.equals("-1")){        //选择不限
                    if(session.getAttribute("biaoId")!=null){       //选择了不限后，清除session中的项目类型
                        session.removeAttribute("biaoId");
                    }
                }else{                          //选择具体类型
                    //将类型id添加到session
                    session.setAttribute("biaoId",param);
                }
            }
            if (item.equals("rate")){       //选择年利率
                if (param.equals("-1")){        //选择不限
                    session.removeAttribute("startR");
                    session.removeAttribute("endR");
                    session.setAttribute("startR",-1);     //选择不限，只设置起始利率为0，以pincome>=0为条件
                }else{                          //选择具体利率
                    if(param.equals("1")){
                        session.setAttribute("startR",0);
                        session.setAttribute("endR",12);
                    }else if(param.equals("2")){
                        session.setAttribute("startR",12);
                        session.setAttribute("endR",14);
                    }else if(param.equals("3")){
                        session.setAttribute("startR",14);
                        session.setAttribute("endR",16);
                    }else if(param.equals("4")){
                        session.setAttribute("startR",16);
                        session.removeAttribute("endR");
                    }
                }
            }
            if (item.equals("timelimit")){       //选择期限
                if (param.equals("-1")){        //选择不限
                    session.removeAttribute("startT");
                    session.removeAttribute("endT");
                    session.setAttribute("startT",-1);     //选择不限，只设置起始天数为0
                }else{                          //选择具体天数
                    if(param.equals("1")){
                        session.setAttribute("startT",0);
                        session.setAttribute("endT",30);
                    }else if(param.equals("2")){
                        session.setAttribute("startT",30);
                        session.setAttribute("endT",90);
                    }else if(param.equals("3")){
                        session.setAttribute("startT",90);
                        session.setAttribute("endT",180);
                    }else if(param.equals("4")){
                        session.setAttribute("startT",180);
                        session.setAttribute("endT",360);
                    }else if(param.equals("5")){
                        session.setAttribute("startT",360);
                        session.removeAttribute("endT");
                    }
                }
            }
            if(item.equals("repayway")){        //选择还款方式
                if (param.equals("-1")){        //选择不限
                    session.removeAttribute("pway");     //选择不限，使查询条件为1=1
                }else{                          //选择具体方式
                    if(param.equals("1")){
                        session.setAttribute("pway","到期还本付息");
                    }else if(param.equals("2")){
                        session.setAttribute("pway","按月付息,到期还本");
                    }else if(param.equals("3")){
                        session.setAttribute("pway","等额本息");
                    }
                }
            }
        }

        //将设置好的查询条件存储在map中
        if (session.getAttribute("startR")==null){
            session.setAttribute("startR",-1);
        }
        if (session.getAttribute("startT")==null){
            session.setAttribute("startT",-1);
        }
        map.put("biaoId",session.getAttribute("biaoId"));
        map.put("startR",session.getAttribute("startR"));
        map.put("endR",session.getAttribute("endR"));
        map.put("startT",session.getAttribute("startT"));
        map.put("endT",session.getAttribute("endT"));
        map.put("pway",session.getAttribute("pway"));

        //分页查询
        Page<Product> page=new Page<Product>(current,5);
        page=productService.findProductPage(map,page);
        List<Product> list=page.getRecords();

        model.addAttribute("list",list);
        model.addAttribute("page",page);
        return "list";
    }


}
