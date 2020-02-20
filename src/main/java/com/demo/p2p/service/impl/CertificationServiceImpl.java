package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Certification;
import com.demo.p2p.mapper.CertificationMapper;
import com.demo.p2p.service.CertificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
@Service
public class CertificationServiceImpl extends ServiceImpl<CertificationMapper, Certification> implements CertificationService {


    @Override
    public Integer saveCertification(Certification certification) {
        return this.baseMapper.insert(certification);
    }

    @Override
    public Integer certification() {
        return this.baseMapper.certification();
    }

    @Override
    public Certification getcserial(String cserial) {
        return this.baseMapper.getcserial(cserial);
    }

    @Override
    public Certification selById(int id) {
        return this.baseMapper.selectById(id);
    }

}
