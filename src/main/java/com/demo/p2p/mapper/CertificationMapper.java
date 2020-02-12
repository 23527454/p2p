package com.demo.p2p.mapper;

import com.demo.p2p.entity.Certification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public interface CertificationMapper extends BaseMapper<Certification> {
    /**
     * 首页总余额
     * @return
     */
    @Select("SELECT SUM(ctotalmoney) FROM certification")
    public Integer certification();
}
