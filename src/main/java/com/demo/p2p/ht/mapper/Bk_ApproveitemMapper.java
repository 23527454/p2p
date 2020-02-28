package com.demo.p2p.ht.mapper;

import com.demo.p2p.ht.entity.Approveitem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 认证项管理 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public interface Bk_ApproveitemMapper extends BaseMapper<Approveitem> {
    public List<Approveitem> selList(Map<String, Object> map);
}
