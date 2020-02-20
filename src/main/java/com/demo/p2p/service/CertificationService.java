package com.demo.p2p.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Certification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public interface CertificationService extends IService<Certification> {
    public Integer certification();
    public Certification selById(int id);
    public List<Certification> getcserial(QueryWrapper<Certification> queryWrapper);
}
