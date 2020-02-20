package com.demo.p2p.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Dept;
import com.demo.p2p.entity.Dope;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-15
 */
public interface DopeService extends IService<Dope> {
    public List<Dope> select();
    public void batchDeletes(Integer did);
}
