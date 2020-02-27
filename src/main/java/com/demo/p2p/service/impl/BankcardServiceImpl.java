package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Bankcard;
import com.demo.p2p.mapper.BankcardMapper;
import com.demo.p2p.service.BankcardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 银行卡管理表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-21
 */
@Service
public class BankcardServiceImpl extends ServiceImpl<BankcardMapper, Bankcard> implements BankcardService {

    @Resource
    private  BankcardMapper bankcardMapper;

    @Override
    public List<Bankcard> getbank(int uid) {
        return bankcardMapper.getbank(uid);
    }

    @Override
    public Bankcard getInfo(int bid) {
        return bankcardMapper.getInfo(bid);
    }

    @Override
    public List<Bankcard> bankcardList() {
        return this.baseMapper.bankcardList();
    }

    @Override
    public int savebankcard(Bankcard bankcard) {
        return this.baseMapper.insert(bankcard);
    }
}
