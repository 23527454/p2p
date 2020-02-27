package com.demo.p2p.ht.controller;


import com.demo.p2p.ht.entity.Employee;
import com.demo.p2p.ht.entity.Withdrawal;
import com.demo.p2p.ht.service.Bk_WithdrawalService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 提现管理表 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/withdrawal")
public class Bk_WithdrawalController {
    @Resource
    Bk_WithdrawalService bk_withdrawalService;

    @RequestMapping(value = "/wlist_do")
    public String wlist_do(String currpage,Integer btn,String wname,String yyy,String yyyy,Integer wstatu,HttpSession session){
        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (wname != null && wname != ""){
            System.out.println("uname："+wname);
            parameters.put("uname",wname);
            session.setAttribute("wname",wname);
        }
        if (yyy != null && yyy != ""){
            System.out.println("yyy："+yyy);
            parameters.put("yyy",yyy);
            session.setAttribute("yyy",yyy);
        }
        if (yyyy != null && yyyy != ""){
            System.out.println("yyyy："+yyyy);
            parameters.put("yyyy",yyyy);
            session.setAttribute("yyyy",yyyy);
        }
        if (wstatu != null){
            System.out.println("statu："+wstatu);
            parameters.put("statu",wstatu);
            session.setAttribute("statu",wstatu);
        }
        if (btn != null){
            System.out.println("statu："+btn);
            parameters.put("statu",btn);
            session.setAttribute("btn",btn);
        }
        List<Withdrawal> list = bk_withdrawalService.sellist(parameters);
        totalrow = list.size();
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }

        int outcount = 0;// 不够一页的数据条数
        int count = 0;//
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }

        outcount = totalrow % pagerow;
        count = totalrow / pagerow;

        totalpage = count;

        if (outcount > 0) {
            totalpage = count + 1;
        }

        if (currpages < 1) {
            currpages = 1;
        }
        if (currpages > totalpage) {
            currpages = totalpage;
        }

        Integer candp = (currpages - 1) * pagerow;
        if (candp < 0) {
            candp = 0;
        }


        double sumtxmoney = 0;
        double sumdzmoney = 0;
        double sumsxf = 0;
        parameters.put("pandc", 5);
        parameters.put("candp", candp);
        List<Withdrawal> sellist = bk_withdrawalService.sellist(parameters);
        for (Withdrawal withdrawal : sellist) {
            sumtxmoney += withdrawal.getTxmoney();
            sumdzmoney += withdrawal.getDzmoney();
            sumsxf += withdrawal.getSxf();
        }
        session.setAttribute("totalrow", totalrow);
        session.setAttribute("currpages", currpages);
        session.setAttribute("totalpage", totalpage);
        session.setAttribute("sumtxmoney",sumtxmoney);
        session.setAttribute("sumdzmoney",sumdzmoney);
        session.setAttribute("sumsxf",sumsxf);
        session.setAttribute("wdlist",sellist);
        return "view/Withdrawallist";
    }


    @RequestMapping(value = "/ajax_do")
    @ResponseBody
    public Withdrawal ajax_do(Integer id,HttpSession session){
        Withdrawal withdrawal = bk_withdrawalService.getById(id);
        return withdrawal;
    }

    @RequestMapping("/zhuans_do")
    public String zhuan(int gg, int wid){
        Withdrawal wone =  bk_withdrawalService.getById(wid);
        if(gg==0){
            //失败
            bk_withdrawalService.updateById(wone);
            wone.setStatu("0");
            Double txmoney = wone.getTxmoney();//体检金额
            Integer uid = wone.getuID();//用户id
            bk_withdrawalService.updmoney(txmoney, uid);
            int i=1;
            //添加失败的交易记录
            bk_withdrawalService.intmoney(wone, i);
        }else if(gg==1){
            //成功
            bk_withdrawalService.updwith(gg, wid);
            int i=2;
            //添加交易成功记录
            bk_withdrawalService.intmoney(wone, i);
        }
        return "redirect:wlist_do";
    }

    @RequestMapping("shen")
    public String shen(int gg,int wid, HttpServletRequest req){
        HttpSession session = req.getSession();
        Employee emp = (Employee) session.getAttribute("loginEmp");
        String shname = emp.getEname();
        if(gg==0){
            bk_withdrawalService.updwiths(gg, wid, shname);
            //退钱
            Withdrawal wone =  bk_withdrawalService.getById(wid);
            double txmoney = wone.getTxmoney();
            Integer uid = wone.getuID();
            bk_withdrawalService.updmoney(txmoney, uid);
            int i=0;
            bk_withdrawalService.intmoney(wone, i);
        }else if(gg==2){
            bk_withdrawalService.updwiths(gg, wid, shname);
        }
        return "redirect:wlist_do";
    }
    @RequestMapping("putexcel_do")
    public String putexcel(HttpServletResponse response,HttpSession session) throws IOException {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("提现管理");
        HSSFRow titleRow = sheet.createRow(0);
        // 标题行
        HSSFCell cell1 = titleRow.createCell(0);
        cell1.setCellValue("用户ID");
        HSSFCell cell2 = titleRow.createCell(1);
        cell2.setCellValue("用户名");
        HSSFCell cell3 = titleRow.createCell(2);
        cell3.setCellValue("真实姓名");
        HSSFCell cell4 = titleRow.createCell(3);
        cell4.setCellValue("账户");
        HSSFCell cell5 = titleRow.createCell(4);
        cell5.setCellValue("提现银行");
        HSSFCell cell6 = titleRow.createCell(5);
        cell6.setCellValue("提现金额");
        HSSFCell cell7 = titleRow.createCell(6);
        cell7.setCellValue("到账金额");
        HSSFCell cell8 = titleRow.createCell(7);
        cell8.setCellValue("手续费");
        HSSFCell cell9 = titleRow.createCell(8);
        cell9.setCellValue("提现时间");
        HSSFCell cell10 = titleRow.createCell(9);
        cell10.setCellValue("转账时间");
        HSSFCell cell11 = titleRow.createCell(10);
        cell11.setCellValue("状态0失败，1已提现,2转账中，3审核中，）");
        HSSFCell cell12 = titleRow.createCell(11);
        cell12.setCellValue("审核人");
        HSSFCell cell13 = titleRow.createCell(12);
        cell13.setCellValue("备注");


        Map<String, Object> parameters = new HashMap<String, Object>();
        String wname = (String) session.getAttribute("wname");
        String yyy = (String) session.getAttribute("yyy");
        String yyyy = (String) session.getAttribute("yyyy");
        Integer wstatu = (Integer) session.getAttribute("wstatu");
        String btn = (String) session.getAttribute("btn");
        if (wname != null && wname != ""){
            System.out.println("uname："+wname);
            parameters.put("uname",wname);
            session.setAttribute("wname",wname);
        }
        if (yyy != null && yyy != ""){
            System.out.println("yyy："+yyy);
            parameters.put("yyy",yyy);
            session.setAttribute("yyy",yyy);
        }
        if (yyyy != null && yyyy != ""){
            System.out.println("yyyy："+yyyy);
            parameters.put("yyyy",yyyy);
            session.setAttribute("yyyy",yyyy);
        }
        if (wstatu != null){
            System.out.println("statu："+wstatu);
            parameters.put("statu",wstatu);
            session.setAttribute("statu",wstatu);
        }
        if (btn != null){
            System.out.println("statu："+btn);
            parameters.put("statu",btn);
            session.setAttribute("btn",btn);
        }
        List<Withdrawal> lw = bk_withdrawalService.sellist(parameters);
        for (int i = 0; i < lw.size(); i++) {
            Withdrawal wi = lw.get(i);
            // 数据行
            HSSFRow dataRow = sheet.createRow(i + 1);
            HSSFCell uid = dataRow.createCell(0);
            uid.setCellValue(wi.getuID());
            HSSFCell uname = dataRow.createCell(1);
            uname.setCellValue(wi.getUname());
            HSSFCell zname = dataRow.createCell(2);
            zname.setCellValue(wi.getZname());
            HSSFCell txnum = dataRow.createCell(3);
            txnum.setCellValue(wi.getTxnum());
            HSSFCell txbank = dataRow.createCell(4);
            txbank.setCellValue(wi.getTxbank());
            HSSFCell txmoney = dataRow.createCell(5);
            txmoney.setCellValue(wi.getTxmoney());
            HSSFCell dzmoney = dataRow.createCell(6);
            dzmoney.setCellValue(wi.getDzmoney());
            HSSFCell sxf = dataRow.createCell(7);
            sxf.setCellValue(wi.getSxf());
            HSSFCell txtime = dataRow.createCell(8);

            HSSFCellStyle dateStyle = workBook.createCellStyle();
            HSSFDataFormat dateFormat = workBook.createDataFormat();
            dateStyle
                    .setDataFormat(dateFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
            txtime.setCellStyle(dateStyle);

            txtime.setCellValue(wi.getTxtime());
            HSSFCell zztime = dataRow.createCell(9);

            zztime.setCellStyle(dateStyle);

            zztime.setCellValue(wi.getZztime());
            HSSFCell statu = dataRow.createCell(10);
            statu.setCellValue(wi.getStatu());
            HSSFCell shwho = dataRow.createCell(11);
            shwho.setCellValue(wi.getShwho());
            HSSFCell nothing = dataRow.createCell(12);
            nothing.setCellValue(wi.getNothing());
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        String path = chooser.getSelectedFile().getPath();

        if(path!=null&&!path.equals("")){
            System.out.println(path);
            FileOutputStream fos = new FileOutputStream(
                    path+"\\提现信息.xls");
            workBook.write(fos);
        }else {
            System.out.println("error!!!");
        }

        return "redirect:wlist_do";
    }
}

