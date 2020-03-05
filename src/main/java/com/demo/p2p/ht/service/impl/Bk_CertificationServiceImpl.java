package com.demo.p2p.ht.service.impl;

import com.demo.p2p.ht.entity.Certification;
import com.demo.p2p.ht.mapper.Bk_CertificationMapper;
import com.demo.p2p.ht.service.Bk_CertificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-03-03
 */
@Service
public class Bk_CertificationServiceImpl extends ServiceImpl<Bk_CertificationMapper, Certification> implements Bk_CertificationService {
    @Resource
    private Bk_CertificationMapper bk_certificationMapper;

    @Override
    public Certification findCertificationByBmId(Integer bmid) {
        return bk_certificationMapper.findCertificationByBmId(bmid);
    }

    @Override
    public List<Certification> findCertificationByBmId2(Integer bmid) {
        return bk_certificationMapper.findCertificationByBmId2(bmid);
    }
}
