package com.demo.p2p.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.entity.Notice;

import java.util.List;

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
   public List<Notice> select2(QueryWrapper<Notice> queryWrapper);
   public List<Notice> select3(QueryWrapper<Notice> queryWrapper);
}
