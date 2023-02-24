package com.jk.mock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jk.mock.entity.MockInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/22
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Mapper
public interface MockInfoMapper extends BaseMapper<MockInfo> {
}
