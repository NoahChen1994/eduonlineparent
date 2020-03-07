package com.eduonline.eduucenter.mapper;

import com.eduonline.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-03-04
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    //统计某天注册人数
    Integer getRegistNumByDay(String day);

}
