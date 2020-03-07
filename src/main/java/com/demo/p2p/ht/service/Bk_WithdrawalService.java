package com.demo.p2p.ht.service;

import com.demo.p2p.ht.entity.Withdrawal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提现管理表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
public interface Bk_WithdrawalService extends IService<Withdrawal> {
    public List<Withdrawal> sellist(Map<String, Object> map);

    public double sumtxmoney(Map<String, Object> map);

    public double sumdzmoney(Map<String, Object> map);

    public double sumsxf(Map<String, Object> map);

    public int updmoney(double txmoney, int uid);

    public int updwith(int gg, int wid);

    public int updwiths(int gg, int wid, String wname);

    int intmoney(Withdrawal w, int i);

    List<Withdrawal> lsnum();
}
