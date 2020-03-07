package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Borrowcord;
import com.demo.p2p.ht.mapper.Bk_BorrowcordMapper;
import com.demo.p2p.ht.service.Bk_BorrowcordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 还款记录表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-26
 */
@Service
public class Bk_BorrowcordServiceImpl extends ServiceImpl<Bk_BorrowcordMapper, Borrowcord> implements Bk_BorrowcordService {

    @Resource
    private Bk_BorrowcordMapper bk_borrowcordMapper;

    @Override
    public List<Borrowcord> lssum() {
        return bk_borrowcordMapper.lssum();
    }
}
