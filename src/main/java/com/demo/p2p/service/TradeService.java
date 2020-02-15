package com.demo.p2p.service;

import com.demo.p2p.entity.Trade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 交易记录表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-13
 */
public interface TradeService extends IService<Trade> {
    
    public List<Trade> selectMoney(Integer uid);
}
