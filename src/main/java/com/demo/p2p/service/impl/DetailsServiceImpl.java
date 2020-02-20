package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Details;
import com.demo.p2p.mapper.DetailsMapper;
import com.demo.p2p.service.DetailsService;
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
 * @since 2020-02-19
 */
@Service
public class DetailsServiceImpl extends ServiceImpl<DetailsMapper, Details> implements DetailsService {
    @Resource
    private DetailsMapper detailsMapper;

    @Override
    public List<Details> selList(Integer pid) {
        QueryWrapper<Details> wrapper = new QueryWrapper();
        wrapper.eq("pid",pid);
        return detailsMapper.selectList(wrapper);
    }
}
