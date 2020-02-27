package com.demo.p2p.mapper;

import com.demo.p2p.entity.Borrowmoney;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.p2p.entity.Investinfo;
import org.apache.ibatis.annotations.Insert;

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
public interface BorrowmoneyMapper extends BaseMapper<Borrowmoney> {

    //房贷车贷的添加
    @Insert("INSERT INTO credit.borrowmoney \n" +
            "\t(busername,brelname,bpass,btitle, bmoney,bserial, bstate, brecommend, bleixing, beizhu1, beizhu2)\n" +
            "\tVALUES(#{busername},#{brelname}, #{bpass},#{btitle}, #{bmoney},#{bserial}, \n" +
            "\t#{bstate}, #{brecommend}, #{bleixing}, #{beizhu1}, #{beizhu2});")
    public int addMoney(Borrowmoney borrowmoney);

    public List<Borrowmoney> selInfo(Map<String, Object> map);
}
