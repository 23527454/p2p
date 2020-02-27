package com.demo.p2p.mapper;

import com.demo.p2p.entity.Packetred;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public interface PacketredMapper extends BaseMapper<Packetred> {
    @Select("SELECT * FROM packetred")
    public List<Packetred> packetredList();

    @Update("UPDATE packetred SET pname =#{pname},ptype=#{ptype}  where dhma = #{dhma}")
    public int packetredupdate(Packetred packetred);
}
