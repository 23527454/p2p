package com.demo.p2p.mapper;

import com.demo.p2p.entity.Borrowcord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 还款记录表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-26
 */
public interface BorrowcordMapper extends BaseMapper<Borrowcord> {
    public List<Borrowcord> selInfo(Map<String, Object> map);
    public Double selSum(Map<String, Object> map);

}
