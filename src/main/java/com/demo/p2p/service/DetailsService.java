package com.demo.p2p.service;

import com.demo.p2p.entity.Details;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-19
 */
public interface DetailsService extends IService<Details> {
    public List<Details> selList(Integer pid);
}
