package com.demo.p2p.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.Investinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 投资表 Mapper 接口
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
public interface InvestinfoMapper extends BaseMapper<Investinfo> {
    /**
     * 首页累计投资金额
     * @return
     */
    @Select("select sum(inmoney) from investinfo")
      public Integer sumInmoney();

    /**
     * 盈利金额
     * @return
     */
    @Select("SELECT SUM(profitmoney) FROM investinfo")
    public Integer sumProfitmoney();

    @Select("SELECT a.*,p.pname title,p.ptype TYPE FROM (SELECT inv.*,un.uname FROM InvestInfo inv LEFT JOIN users un ON inv.userid = un.uid) AS a LEFT JOIN product p ON a.brrowid =p.id WHERE 1 = 1 AND userid=#{uid}")
    public List<Investinfo> selInvestinfoPage(@Param("uid") Integer uid, Page<Investinfo> page);

    public Double sumMoney(Map<String, Object> map);

    @Select("select sum(inmoney * (interest / 100)) from investinfo where userid = #{uid}")
    public Integer getMoney(Integer uid);

    public List<Investinfo> selInfo(Map<String, Object> map);

    @Select("SELECT io.*,pt.pname FROM investinfo io,product pt\n" +
            "WHERE   pt.id = io.brrowid AND userid = #{userid} LIMIT 5")
    public List<Investinfo> getFive(@Param("userid")int userid);

    public int add(Investinfo investinfo);
}
