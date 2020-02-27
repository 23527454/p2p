package com.demo.p2p.service.impl;

import com.demo.p2p.entity.Packetred;
import com.demo.p2p.mapper.PacketredMapper;
import com.demo.p2p.service.PacketredService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
@Service
public class PacketredServiceImpl extends ServiceImpl<PacketredMapper, Packetred> implements PacketredService {

    @Override
    public List<Packetred> packetredList() {
        return this.baseMapper.packetredList();
    }

    @Override
    public int packetredupdate(Packetred packetred) {
        return this.baseMapper.packetredupdate(packetred);
    }
}
