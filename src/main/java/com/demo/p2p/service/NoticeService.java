package com.demo.p2p.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.entity.Dope;
import com.demo.p2p.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hcf
 * @since 2020-02-11
 */
public interface NoticeService extends IService<Notice> {
   public List<Notice> select1(QueryWrapper<Notice> queryWrapper);
   //分页
   public List<Notice> findDope1(Map<String, Object> map);
   public List<Notice> findDope2(Map<String, Object> map);
}
