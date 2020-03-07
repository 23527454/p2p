package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Certifrecord;
import com.demo.p2p.ht.mapper.Bk_CertifrecordMapper;
import com.demo.p2p.ht.service.Bk_CertifrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 认证记录 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
@Service
public class Bk_CertifrecordServiceImpl extends ServiceImpl<Bk_CertifrecordMapper, Certifrecord> implements Bk_CertifrecordService {

    @Resource
    private Bk_CertifrecordMapper bk_certifrecordMapper;
    @Override
    public List<Certifrecord> lssize() {
        return bk_certifrecordMapper.lssize();
    }
}
