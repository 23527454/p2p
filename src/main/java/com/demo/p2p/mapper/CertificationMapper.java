package com.demo.p2p.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.entity.Certification;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public interface CertificationMapper extends BaseMapper<Certification> {
    /**
     * 首页总余额
     * @return
     */
    @Select("SELECT SUM(ctotalmoney) FROM certification")
    public Integer certification();

    /**
     * 个人中心显示账户余额
     */
    @Select("SELECT * FROM certification WHERE cusername =  #{cusername}")
    public Certification getcserial(@Param("cusername") String cusername);

    @Select("SELECT * FROM certification WHERE cserial =  #{cserial}")
    public Certification getcserialSelectId(@Param("cserial") Integer id);
    /**
     * 提现修改金额
     */
    @Update("UPDATE certification  SET ctotalmoney   = ctotalmoney - #{cashFine}  \n" +
            " WHERE id = #{id}")
    public int updateMoney(@Param("id") String id,@Param("cashFine") String cashFine);

    @Select("SELECT * FROM certification")
    public List<Certification> certificationList();

    public int upmoney(Certification certification);

    @Update("update certification set ctotalmoney = #{ctotalmoney} where id = #{id}")
    public int certificationupup(Certification certification);

    public Certification selByMap(Map<String,Object> map);

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
