package com.jk.mock.core.dubbo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/4/19/019 15:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MockInvocation {

    private String methodName;

    private String interfaceName;

    private List<String> parameterTypes;

    private String group;

    private String version;

    private String response;

    public boolean valid() {
        if (StringUtils.isBlank(methodName)) {
            return false;
        }
        if (StringUtils.isBlank(interfaceName)) {
            return false;
        }
        if (StringUtils.isBlank(group)) {
            return false;
        }
        if (StringUtils.isBlank(version)) {
            return false;
        }
        if (StringUtils.isBlank(response)) {
            return false;
        }
        return true;
    }

}
