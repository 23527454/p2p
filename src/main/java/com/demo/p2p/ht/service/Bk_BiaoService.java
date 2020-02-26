package com.demo.p2p.ht.service;

import com.demo.p2p.ht.entity.Biao;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.ht.entity.Withdrawal;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
public interface Bk_BiaoService extends IService<Biao> {
    public List<Biao> sellist(Map<String, Object> map);
}
