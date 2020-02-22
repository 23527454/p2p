package com.demo.p2p.ht.mapper;

import com.demo.p2p.ht.entity.Recharge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 充值记录表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
public interface Bk_RechargeMapper extends BaseMapper<Recharge> {
    @Select("select sum(czmoney) from recharge")
    public String sumCzMoney();

    @Select("select sum(dzmoney) from recharge")
    public String sumDzMoney();
}
