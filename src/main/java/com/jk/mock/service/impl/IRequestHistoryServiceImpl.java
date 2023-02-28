package com.jk.mock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jk.mock.entity.RequestHistory;
import com.jk.mock.mapper.RequestHistoryMapper;
import com.jk.mock.service.IRequestHistoryService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/28
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class IRequestHistoryServiceImpl extends ServiceImpl<RequestHistoryMapper, RequestHistory> implements IRequestHistoryService {
}
