package com.jk.mock.config.filter.dubbo;

import com.jk.mock.core.code.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/21
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
@Activate(order = 10000, group = PROVIDER)
public class MockServerFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            //线程存储interface信息
            Util.setUrlInterface(invoker.getUrl().getServiceInterface());
            //线程存储group信息
            Util.setUrlUrlGroup(invoker.getUrl().getGroup());
            Result result = invoker.invoke(invocation);
            return result;
        }finally {
            Util.clear();
        }
    }
}
