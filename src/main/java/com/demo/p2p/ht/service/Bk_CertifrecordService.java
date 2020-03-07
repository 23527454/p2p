package com.demo.p2p.ht.service;

import com.demo.p2p.ht.entity.Certifrecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 认证记录 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public interface Bk_CertifrecordService extends IService<Certifrecord> {
     List<Certifrecord> lssize();//新用户未处理审批的
}
