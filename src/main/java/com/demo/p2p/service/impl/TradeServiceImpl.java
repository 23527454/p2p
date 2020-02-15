package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Trade;
import com.demo.p2p.mapper.TradeMapper;
import com.demo.p2p.service.TradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 交易记录表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-13
 */
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements TradeService {
    @Resource
    private TradeMapper tradeMapper;

    @Override
    public List<Trade> selectMoney(Integer uid) {
        return tradeMapper.selectMoney(uid);
    }
}
