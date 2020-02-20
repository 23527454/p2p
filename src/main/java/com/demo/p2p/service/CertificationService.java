package com.demo.p2p.service;

import com.demo.p2p.entity.Certification;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public interface CertificationService extends IService<Certification> {
    public Integer saveCertification(Certification certification);
    public Integer certification();
    public Certification getcserial(String cserial);
    public Certification selById(int id);
}
