package com.jk.mock.common.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/23
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReq implements Serializable {

    @NotBlank(message = "functionName is empty")
    private String functionName;

    @NotBlank(message = "interfaceName is empty")
    private String interfaceName;

    @NotBlank(message = "parameterTypes is empty")
    private String parameterTypes;

    private String response;

    private String dubboGroup;

    private String version;


}
