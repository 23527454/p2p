package com.demo.p2p.ht.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.ht.entity.Dept;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-20
 */
public interface Bk_DeptService extends IService<Dept> {
    public Page<Dept> findAllByPage(Page<Dept> page);
}
