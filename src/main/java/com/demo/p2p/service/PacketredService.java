package com.demo.p2p.service;

import com.demo.p2p.entity.Packetred;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public interface PacketredService extends IService<Packetred> {
    public List<Packetred> packetredList();

    public int packetredupdate(Packetred packetred);
}
