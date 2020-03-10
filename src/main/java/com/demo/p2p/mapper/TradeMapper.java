package com.demo.p2p.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.entity.Trade;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 交易记录表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-13
 */
public interface TradeMapper extends BaseMapper<Trade> {

    @Select("select jymoney from trade where uID = #{userid} and what like '%退回本金%'")
    public List<Trade> selectMoney(Integer uid);

    @Select("SELECT * FROM trade where uid=#{uid}")
    public List<Trade> teacherinfor(Integer id);
}
