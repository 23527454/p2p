package com.demo.p2p.ht.service;

import com.demo.p2p.ht.entity.Recharge;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
public interface Bk_RechargeService extends IService<Recharge> {
    public String sumCzMoney();
    public String sumDzMoney();
}
