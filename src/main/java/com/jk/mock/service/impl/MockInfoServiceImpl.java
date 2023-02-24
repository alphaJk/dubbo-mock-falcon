package com.jk.mock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jk.mock.entity.MockInfo;
import com.jk.mock.mapper.MockInfoMapper;
import com.jk.mock.service.IMockInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/22
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 * Description:
 */


@Service
public class MockInfoServiceImpl extends ServiceImpl<MockInfoMapper, MockInfo> implements IMockInfoService {
}
