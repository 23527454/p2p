package com.demo.p2p.ht.service;

import com.demo.p2p.ht.entity.Certification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-03-03
 */
public interface Bk_CertificationService extends IService<Certification> {
    public Certification findCertificationByBmId(Integer bmid);
    public List<Certification> findCertificationByBmId2(Integer bmid);
}
