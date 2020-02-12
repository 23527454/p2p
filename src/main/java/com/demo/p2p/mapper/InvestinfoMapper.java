package com.demo.p2p.mapper;

import com.demo.p2p.entity.Investinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 投资表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
public interface InvestinfoMapper extends BaseMapper<Investinfo> {
    /**
     * 首页累计投资金额
     * @return
     */
    @Select("select sum(inmoney) from investinfo")
      public Integer sumInmoney();

    /**
     * 盈利金额
     * @return
     */
    @Select("SELECT SUM(profitmoney) FROM investinfo")
    public Integer sumProfitmoney();


}
