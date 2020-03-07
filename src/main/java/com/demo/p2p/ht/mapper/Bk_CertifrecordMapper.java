package com.demo.p2p.ht.mapper;

import com.demo.p2p.ht.entity.Certifrecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 认证记录 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public interface Bk_CertifrecordMapper extends BaseMapper<Certifrecord> {
    @Select("SELECT crid FROM certifrecord WHERE crispass = 1;")
    public List<Certifrecord> lssize();//新用户未处理审批的
}
