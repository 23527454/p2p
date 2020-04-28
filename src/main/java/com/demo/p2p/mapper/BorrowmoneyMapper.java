package com.demo.p2p.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.Borrowmoney;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("SELECT b.* FROM borrowmoney b ,product p WHERE b.id=p.bmid AND b.bstate = '1' AND p.pmoney=p.ptotalmoney AND p.pproduce=#{uid} GROUP BY b.id")
    public List<Borrowmoney> selHuanKuanList(@Param("uid") Integer uid, Page<Borrowmoney> page);

    @Select("SELECT SUM(b.bmoney) FROM borrowmoney b ,product p WHERE b.id=p.bmid AND p.pmoney=p.ptotalmoney AND p.pproduce=#{uid}")
    public Double sumBorrow(Integer uid);
}
