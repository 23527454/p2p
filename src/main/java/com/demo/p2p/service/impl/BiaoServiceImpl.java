package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Biao;
import com.demo.p2p.mapper.BiaoMapper;
import com.demo.p2p.service.BiaoService;
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
 * @since 2020-02-15
 */
@Service
public class BiaoServiceImpl extends ServiceImpl<BiaoMapper, Biao> implements BiaoService {
    @Resource
    private BiaoMapper biaoMapper;

    @Override
    public List<Biao> selList() {
        QueryWrapper<Biao> wrapper = new QueryWrapper<Biao>();
        return biaoMapper.selectList(wrapper);
    }
}
