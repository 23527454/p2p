package com.demo.p2p.mapper;

import com.demo.p2p.entity.Biao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-15
 */
public interface BiaoMapper extends BaseMapper<Biao> {
    public List<Biao> selList(@Param("params") Map<String, Object> map);
}
