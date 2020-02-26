package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Biao;
import com.demo.p2p.ht.mapper.Bk_BiaoMapper;
import com.demo.p2p.ht.service.Bk_BiaoService;
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
 * @since 2020-02-25
 */
@Service
public class Bk_BiaoServiceImpl extends ServiceImpl<Bk_BiaoMapper, Biao> implements Bk_BiaoService {
    @Resource
    Bk_BiaoMapper bk_biaoMapper;

    @Override
    public List<Biao> sellist(Map<String, Object> map) {
        return bk_biaoMapper.sellist(map);
    }
}
