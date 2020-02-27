package com.demo.p2p.ht.mapper;

import com.demo.p2p.ht.entity.Biao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
public interface Bk_BiaoMapper extends BaseMapper<Biao> {
    public List<Biao> sellist(Map<String, Object> map);
}
