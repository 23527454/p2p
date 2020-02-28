package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Approveitem;
import com.demo.p2p.ht.mapper.Bk_ApproveitemMapper;
import com.demo.p2p.ht.service.Bk_ApproveitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 认证项管理 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
@Service
public class Bk_ApproveitemServiceImpl extends ServiceImpl<Bk_ApproveitemMapper, Approveitem> implements Bk_ApproveitemService {
    @Resource
    private Bk_ApproveitemMapper bk_approveitemMapper;

    @Override
    public List<Approveitem> selList(Map<String, Object> map) {
        return bk_approveitemMapper.selList(map);
    }
}
