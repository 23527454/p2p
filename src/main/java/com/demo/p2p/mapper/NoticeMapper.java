package com.demo.p2p.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.entity.Dope;
import com.demo.p2p.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public interface NoticeMapper extends BaseMapper<Notice> {
    public List<Notice> findDope1(Map<String, Object> map);
    public List<Notice> findDope2(Map<String, Object> map);
}
