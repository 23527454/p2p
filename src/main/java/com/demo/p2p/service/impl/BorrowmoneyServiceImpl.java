package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Borrowmoney;
import com.demo.p2p.entity.Investinfo;
import com.demo.p2p.mapper.BorrowmoneyMapper;
import com.demo.p2p.service.BorrowmoneyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

}
