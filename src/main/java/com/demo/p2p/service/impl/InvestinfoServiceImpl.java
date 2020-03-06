package com.demo.p2p.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.p2p.entity.Investinfo;
import com.demo.p2p.mapper.InvestinfoMapper;
import com.demo.p2p.service.InvestinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 投资表 服务实现类
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
@Service
public class InvestinfoServiceImpl extends ServiceImpl<InvestinfoMapper, Investinfo> implements InvestinfoService {
    @Resource
    private InvestinfoMapper investinfoMapper;

    @Override
    public Integer sumInmoney() {
        return this.baseMapper.sumInmoney();
    }

    @Override
    public Integer sumProfitmoney() {
        return this.baseMapper.sumProfitmoney();
    }

    @Override
    public Page<Investinfo> selInvestinfoPageByUId(Integer uid,Page<Investinfo> page) {
        page.setRecords(investinfoMapper.selInvestinfoPage(uid,page));
        return page;
    }

    @Override
    public Double sumMoney(Map<String, Object> map) {
        return investinfoMapper.sumMoney(map);
    }

    @Override
    public Integer getMoney(Integer uid) {
        return investinfoMapper.getMoney(uid);
    }

    @Override
    public List<Investinfo> selInfo(Map<String, Object> map) {
        return investinfoMapper.selInfo(map);
    }

    @Override
    public int addInfo(Investinfo investinfo) {
        return investinfoMapper.add(investinfo);
    }

    @Override
    public int upByMap(Map<String, Object> map) {
        return investinfoMapper.upByMap(map);
    }

    @Override
    public List<Investinfo> selByMap(Map<String, Object> map) {
        return investinfoMapper.selByMap(map);
    }

    @Override
    public Double getInmoney(Map<String, Object> map) {
        return investinfoMapper.getInmoney(map);
    }

    @Override
    public Double getProfitmoney(Map<String, Object> map) {
        return investinfoMapper.getProfitmoney(map);
    }

    @Override
    public List<Investinfo> getFive(int userid) {
        return investinfoMapper.getFive(userid);
    }

}
