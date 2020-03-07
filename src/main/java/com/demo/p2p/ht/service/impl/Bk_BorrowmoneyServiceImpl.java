package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Borrowmoney;
import com.demo.p2p.ht.mapper.Bk_BorrowmoneyMapper;
import com.demo.p2p.ht.service.Bk_BorrowmoneyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Service
public class Bk_BorrowmoneyServiceImpl extends ServiceImpl<Bk_BorrowmoneyMapper, Borrowmoney> implements Bk_BorrowmoneyService {

    @Resource
    private Bk_BorrowmoneyMapper bk_borrowmoneyMapper;
    @Override
    public List<Borrowmoney> lscount() {
        return bk_borrowmoneyMapper.lscount();
    }
}
