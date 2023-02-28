package com.jk.mock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jk.mock.entity.MockInfo;
import com.jk.mock.entity.RequestHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/28
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Mapper
public interface RequestHistoryMapper extends BaseMapper<RequestHistory> {
}
