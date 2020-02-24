package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Withdrawal;
import com.demo.p2p.ht.mapper.Bk_WithdrawalMapper;
import com.demo.p2p.ht.service.Bk_WithdrawalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    Bk_WithdrawalMapper bk_withdrawalMapper;

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
}
