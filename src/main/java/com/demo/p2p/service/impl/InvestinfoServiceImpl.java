package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Investinfo;
import com.demo.p2p.mapper.InvestinfoMapper;
import com.demo.p2p.service.InvestinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 投资表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
@Service
public class InvestinfoServiceImpl extends ServiceImpl<InvestinfoMapper, Investinfo> implements InvestinfoService {

    @Override
    public Integer sumInmoney() {
        return this.baseMapper.sumInmoney();
    }

    @Override
    public Integer sumProfitmoney() {
        return this.baseMapper.sumProfitmoney();
    }

}
