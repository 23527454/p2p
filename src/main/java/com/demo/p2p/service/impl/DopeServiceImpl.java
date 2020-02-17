package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Dope;
import com.demo.p2p.mapper.DopeMapper;
import com.demo.p2p.service.DopeService;
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
 * @since 2020-02-15
 */
@Service
public class DopeServiceImpl extends ServiceImpl<DopeMapper, Dope> implements DopeService {
     @Resource
    public DopeMapper dopeMapper;
     public DopeService dopeService;
    @Override
    public List<Dope> select() {
        return dopeService.list();
    }
}
