package com.demo.p2p.mapper;

import com.demo.p2p.entity.Dope;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

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
public interface DopeMapper extends BaseMapper<Dope> {
    //分页
    public List<Dope> findDope(Map<String, Object> map);
    //查询总行数
    public List total();
}
