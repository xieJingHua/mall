package com.xiejh.user.dao;

import com.xiejh.user.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-31 09:27:49
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
