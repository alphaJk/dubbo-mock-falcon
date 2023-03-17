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
 * Date: 2023/2/28
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoReq implements Serializable {

    @NotBlank(message = "dubboGroup is empty")
    String dubboGroup;

}
