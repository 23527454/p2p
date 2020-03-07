package com.demo.p2p.ht.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.ht.entity.Certification;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-03-03
 */
public interface Bk_CertificationMapper extends BaseMapper<Certification> {
    /**
     * 获取发起借款的用户账户金额
     * @param bmid
     * @return
     */
    @Select("SELECT c.* FROM certification c WHERE c.cusername=(SELECT u.unickname FROM (SELECT * FROM users WHERE uid=(SELECT busername FROM borrowmoney WHERE id=#{bmid})) u)")
    public Certification findCertificationByBmId(Integer bmid);

    /**
     * 获取投资的用户账户金额
     * @param bmid
     * @return
     */
    @Select("SELECT c.* FROM certification c WHERE c.cusername IN (SELECT u.unickname FROM (SELECT * FROM users WHERE uid IN (SELECT userid FROM investinfo WHERE brrowid=(SELECT p.id FROM product p WHERE bmid=#{bmid}))) u)")
    public List<Certification> findCertificationByBmId2(Integer bmid);
}
