package com.demo.p2p.mapper;

import com.demo.p2p.entity.Certification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 提现修改金额
     */
    @Update("UPDATE certification  SET cbalance = cbalance - #{cashFine}  \n" +
            " WHERE id = #{id}")
    public int updateMoney(@Param("id") String id,@Param("cashFine") String cashFine);

}
