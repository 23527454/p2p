package com.demo.p2p.ht.service;

import com.demo.p2p.ht.entity.Approveitem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.ht.entity.Withdrawal;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 认证项管理 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public interface Bk_ApproveitemService extends IService<Approveitem> {
    public List<Approveitem> selList(Map<String, Object> map);
}
