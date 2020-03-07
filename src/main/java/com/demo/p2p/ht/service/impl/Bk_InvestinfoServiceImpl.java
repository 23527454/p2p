package com.demo.p2p.ht.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.p2p.ht.entity.Investinfo;
import com.demo.p2p.ht.mapper.Bk_InvestinfoMapper;
import com.demo.p2p.ht.service.Bk_InvestinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 投资表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
@Service
public class Bk_InvestinfoServiceImpl extends ServiceImpl<Bk_InvestinfoMapper, Investinfo> implements Bk_InvestinfoService {
    @Resource
    private Bk_InvestinfoMapper bk_investinfoMapper;

    @Override
    public List<Investinfo> findInMoneySum(Integer bmid) {
        return bk_investinfoMapper.findInMoneySum(bmid);
    }
}
