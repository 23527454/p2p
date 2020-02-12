package com.demo.p2p.mapper;

import com.demo.p2p.entity.Borrowmoney;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

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
            "\t(brelname,bpass,btitle, bmoney,bserial, bstate, brecommend, bleixing, beizhu1, beizhu2)\n" +
            "\tVALUES(#{brelname}, #{bpass},#{btitle}, #{bmoney},#{bserial}, \n" +
            "\t#{bstate}, #{brecommend}, #{bleixing}, #{beizhu1}, #{beizhu2});")
    public int addMoney(Borrowmoney borrowmoney);
}
