package com.demo.p2p.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.p2p.entity.Investinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 投资表 服务类
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
public interface InvestinfoService extends IService<Investinfo> {

    /**
     * 首页累计投资金额
     * @returnf
     */
    public Integer sumInmoney();

    /**
     * 盈利金额
     * @return
     */
    public Integer sumProfitmoney();

    /**
     * 分页查询资金记录
     * @param uid
     * @param page
     * @return
     */
    public Page<Investinfo> selInvestinfoPageByUId(Integer uid,Page<Investinfo> page);

    /**
     * 计算总金额
     * @param map
     * @return
     */
    public Double sumMoney(Map<String, Object> map);

    public Integer getMoney(Integer uid);

    /**
     * 显示
     * @param userid
     * @return
     */
    public List<Investinfo> getFive(@Param("userid")int userid);

    public List<Investinfo> selInfo(Map<String, Object> map);

    public int addInfo(Investinfo investinfo);

    public int upByMap(Map<String, Object> map);

    public List<Investinfo> selByMap(Map<String, Object> map);

    public Double getInmoney(Map<String, Object> map);

    public Double getProfitmoney(Map<String, Object> map);

    public List<Investinfo> findInMoneySum(Integer bmid);
}
