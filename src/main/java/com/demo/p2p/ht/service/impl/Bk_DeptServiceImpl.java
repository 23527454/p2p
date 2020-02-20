package com.demo.p2p.ht.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.p2p.ht.entity.Dept;
import com.demo.p2p.ht.mapper.Bk_DeptMapper;
import com.demo.p2p.ht.service.Bk_DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-20
 */
@Service
public class Bk_DeptServiceImpl extends ServiceImpl<Bk_DeptMapper, Dept> implements Bk_DeptService {
    @Resource
    private Bk_DeptMapper bk_deptMapper;

    @Override
    public Page<Dept> findAllByPage(Page<Dept> page) {
        return page.setRecords(bk_deptMapper.selectList(null));
    }
}
