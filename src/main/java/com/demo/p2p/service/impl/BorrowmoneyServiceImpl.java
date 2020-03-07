package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.p2p.entity.Borrowmoney;
import com.demo.p2p.mapper.BorrowmoneyMapper;
import com.demo.p2p.service.BorrowmoneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
@Service
public class BorrowmoneyServiceImpl extends ServiceImpl<BorrowmoneyMapper, Borrowmoney> implements BorrowmoneyService {
    @Resource
    private  BorrowmoneyMapper borrowmoneyMapper;

    @Override
    public int addMoney(Borrowmoney borrowmoney) {
        return borrowmoneyMapper.addMoney(borrowmoney);
    }

    @Override
    public List<Borrowmoney> selHuanKuanList(Integer uid, Page<Borrowmoney> page) {
        return borrowmoneyMapper.selHuanKuanList(uid,page);
    }

    @Override
    public Double sumBorrow(Integer uid) {
        return borrowmoneyMapper.sumBorrow(uid);
    }

}
