package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Recharge;
import com.demo.p2p.ht.mapper.Bk_RechargeMapper;
import com.demo.p2p.ht.service.Bk_RechargeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 充值记录表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Service
public class Bk_RechargeServiceImpl extends ServiceImpl<Bk_RechargeMapper, Recharge> implements Bk_RechargeService {
    @Resource
    private Bk_RechargeMapper rechargeMapper;

    @Override
    public String sumCzMoney() {
        return rechargeMapper.sumCzMoney();
    }

    @Override
    public String sumDzMoney() {
        return rechargeMapper.sumDzMoney();
    }
}
