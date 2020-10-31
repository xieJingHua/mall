package com.xiejh.order.dao;

import com.xiejh.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-31 09:29:29
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
