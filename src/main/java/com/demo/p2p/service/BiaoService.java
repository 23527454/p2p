package com.demo.p2p.service;

import com.demo.p2p.entity.Biao;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-15
 */
public interface BiaoService extends IService<Biao> {
    public List<Biao> selList();

    public List<Biao> biaoList();
}
