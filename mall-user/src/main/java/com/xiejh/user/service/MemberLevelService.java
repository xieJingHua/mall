package com.xiejh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiejh.common.utils.PageUtils;
import com.xiejh.user.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author xiejh
 * @email 645236648@qq.com
 * @date 2020-10-31 09:27:49
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

