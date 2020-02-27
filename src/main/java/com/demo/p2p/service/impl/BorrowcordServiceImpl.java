package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Borrowcord;
import com.demo.p2p.entity.Borrowmoney;
import com.demo.p2p.mapper.BorrowcordMapper;
import com.demo.p2p.service.BorrowcordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 还款记录表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-26
 */
@Service
public class BorrowcordServiceImpl extends ServiceImpl<BorrowcordMapper, Borrowcord> implements BorrowcordService {
    @Resource
    private BorrowcordMapper borrowcordMapper;

    @Override
    public List<Borrowcord> selInfo(Map<String, Object> map) {
        return borrowcordMapper.selInfo(map);
    }
}
