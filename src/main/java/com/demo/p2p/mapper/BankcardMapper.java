package com.demo.p2p.mapper;

import com.demo.p2p.entity.Bankcard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 银行卡管理表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-21
 */
public interface BankcardMapper extends BaseMapper<Bankcard> {
    @Select("SELECT * FROM bankcard WHERE uid = #{uid}")
    public List<Bankcard> getbank(@Param("uid")int uid);

}
