package com.demo.p2p.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.entity.Certification;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public interface CertificationService extends IService<Certification> {
    public Integer saveCertification(Certification certification);

    public Integer certification();

    public Certification selById(int id);

    public List<Certification> getcserial(QueryWrapper<Certification> queryWrapper);

    public int updateMoney(String id, String cashFine);

    public Certification getcserial(String cusername);

    public List<Certification> certificationList();

    public int upmoney(Certification certification);

    public int certificationupup(Certification certification);

    public Certification selByMap(Map<String,Object> map);

    public Certification findCertificationByBmId(Integer bmid);
    public List<Certification> findCertificationByBmId2(Integer bmid);

    public Certification getcserialSelectId(Integer id);
}
