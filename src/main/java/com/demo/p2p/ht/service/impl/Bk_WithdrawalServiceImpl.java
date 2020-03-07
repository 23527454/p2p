package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Withdrawal;
import com.demo.p2p.ht.mapper.Bk_WithdrawalMapper;
import com.demo.p2p.ht.service.Bk_WithdrawalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提现管理表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Service
public class Bk_WithdrawalServiceImpl extends ServiceImpl<Bk_WithdrawalMapper, Withdrawal> implements Bk_WithdrawalService {
    @Resource
    private   Bk_WithdrawalMapper bk_withdrawalMapper;

    @Override
    public List<Withdrawal> sellist(Map<String, Object> map) {
        return bk_withdrawalMapper.sellist(map);
    }

    @Override
    public double sumtxmoney(Map<String, Object> map) {
        return bk_withdrawalMapper.sumtxmoney(map);
    }

    @Override
    public double sumdzmoney(Map<String, Object> map) {
        return bk_withdrawalMapper.sumdzmoney(map);
    }

    @Override
    public double sumsxf(Map<String, Object> map) {
        return bk_withdrawalMapper.sumsxf(map);
    }

    @Override
    public int updmoney(double txmoney, int uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("txmoney", txmoney);
        map.put("uid", uid);
        return bk_withdrawalMapper.updmoney(map);
    }

    @Override
    public int updwith(int gg, int wid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gg", gg);
        map.put("wid", wid);
        return	bk_withdrawalMapper.updwith(map);
    }

    @Override
    public int updwiths(int gg, int wid, String wname) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("gg", gg);
        map.put("wid", wid);
        map.put("shwho", wname);
        map.put("zztime", new Date());
        map.put("shtime", new Date());
        return bk_withdrawalMapper.updwiths(map);
    }

    @Override
    public int intmoney(Withdrawal w, int i) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", w.getuID());
        map.put("uname", w.getUname());
        map.put("zname", w.getZname());
        map.put("jymoney",w.getTxmoney());
        if(i==0){
            map.put("what", "提现失败");
        }else if(i==1){
            map.put("what", "转账失败");
        }else if(i==2){
            map.put("what", "转账成功");
        }
        map.put("jytime", new Date());
        map.put("other", "无");
        return bk_withdrawalMapper.intmoney(map);
    }

    @Override
    public List<Withdrawal> lsnum() {
        return bk_withdrawalMapper.lsnum();
    }
}
