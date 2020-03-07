package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.p2p.entity.Certification;
import com.demo.p2p.mapper.CertificationMapper;
import com.demo.p2p.service.CertificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

   @Resource
   public CertificationMapper certificationMapper;
    @Override
    public Integer saveCertification(Certification certification) {
        return this.baseMapper.insert(certification);
    }

    @Override
    public Integer certification() {
        return this.baseMapper.certification();
    }


    @Override
    public Certification selById(int id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<Certification> getcserial(QueryWrapper<Certification> queryWrapper) {
        return certificationMapper.selectList(queryWrapper);
    }

    @Override
    public int updateMoney(String id, String cashFine) {
        return certificationMapper.updateMoney(id,cashFine);
    }

    @Override
    public Certification getcserial(String cusername) {
        return certificationMapper.getcserial(cusername);
    }

    @Override
    public List<Certification> certificationList() {
        return this.baseMapper.certificationList();
    }

    @Override
    public int upmoney(Certification certification) {
        return this.baseMapper.upmoney(certification);
    }


    @Override
    public int certificationupup(Certification certification) {
        return this.baseMapper.certificationupup(certification);
    }

    @Override
    public Certification selByMap(Map<String, Object> map) {
        return certificationMapper.selByMap(map);
    }

    @Override
    public Certification findCertificationByBmId(Integer bmid) {
        return certificationMapper.findCertificationByBmId(bmid);
    }

    @Override
    public List<Certification> findCertificationByBmId2(Integer bmid) {
        return certificationMapper.findCertificationByBmId2(bmid);
    }

}
