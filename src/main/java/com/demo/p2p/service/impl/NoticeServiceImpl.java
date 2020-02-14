package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.p2p.entity.Notice;
import com.demo.p2p.mapper.NoticeMapper;
import com.demo.p2p.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hcf
 * @since 2020-02-11
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
   @Resource
   public NoticeMapper noticeMapper;
    @Override
    public List<Notice> select1(QueryWrapper<Notice> queryWrapper)
    {
        return noticeMapper.selectList(queryWrapper);
    }
}
