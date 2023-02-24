package com.jk.mock.controller;


import com.jk.mock.common.ResponseModel;
import com.jk.mock.core.code.dubbo.MockService;
import com.jk.mock.dao.MockInfoDao;
import com.jk.mock.entity.MockInfo;
import com.jk.mock.exception.BaseException;
import com.jk.mock.exception.ErrorCode;
import com.jk.mock.req.MockInfoReq;
import com.jk.mock.req.UpdateReq;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/23
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Slf4j
@RestController
@RequestMapping( "/mockserver/dubbo")
public class MockInfoController {
    @Resource
    private MockInfoDao mockInfoDao;

    @Resource
    private MockService mockService;

    @RequestMapping(value ="/getAll",method = RequestMethod.GET)
    public ResponseModel getAllMockInfo(){
        ResponseModel responseModel = new ResponseModel();
        try {
            List<MockInfo> list = mockInfoDao.getAllMockInfo();
            responseModel.setData(list);
            return responseModel;
        }catch (Exception e) {
            log.error("get all mock info error: {}, {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.ERROR_CODE_30224.getCode(), e.getMessage());
        }
    }

    @RequestMapping(value = "/reload",method = RequestMethod.GET)
    public ResponseModel reloadMockInfo(){
        ResponseModel responseModel = new ResponseModel();
        mockService.reload();
        return responseModel;
    }

    @PostMapping("/updateResponse")
    public ResponseModel updateResponse(@RequestBody UpdateReq req){
        ResponseModel responseModel = new ResponseModel();
        try {
            boolean success = mockInfoDao.updateResponse(req.getInterfaceName(),req.getFunctionName(),req.getParameterTypes(),req.getResponse());
            if (!success){
                responseModel.setCode(ErrorCode.ERROR_CODE_30225.getCode());
                responseModel.setMsg(ErrorCode.ERROR_CODE_30225.getMsg());
            }
            return responseModel;
        }catch (Exception e){
            log.error("update Response mock info error: {}, {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.ERROR_CODE_30224.getCode(), e.getMessage());
        }

    }

    @PostMapping("/updateDubboGroup")
    public ResponseModel updateDubboGroup(@RequestBody UpdateReq req){
        ResponseModel responseModel = new ResponseModel();
        try {
            boolean success = mockInfoDao.updateDubboGroup(req.getInterfaceName(),req.getFunctionName(),req.getParameterTypes(),req.getDubboGroup());
            if (!success){
                responseModel.setCode(ErrorCode.ERROR_CODE_30225.getCode());
                responseModel.setMsg(ErrorCode.ERROR_CODE_30225.getMsg());
            }
            return responseModel;
        }catch (Exception e){
            log.error("update group mock info error: {}, {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.ERROR_CODE_30224.getCode(), e.getMessage());
        }
    }

    @PostMapping("/updateVersion")
    public ResponseModel updateVersion(@RequestBody UpdateReq req){
        ResponseModel responseModel = new ResponseModel();
        try {
            boolean success = mockInfoDao.updateVersion(req.getInterfaceName(),req.getFunctionName(),req.getParameterTypes(),req.getVersion());
            if (!success){
                responseModel.setCode(ErrorCode.ERROR_CODE_30225.getCode());
                responseModel.setMsg(ErrorCode.ERROR_CODE_30225.getMsg());
            }
            return responseModel;
        }catch (Exception e){
            log.error("update group mock info error: {}, {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.ERROR_CODE_30224.getCode(), e.getMessage());
        }
    }

    @PostMapping("/saveOne")
    public ResponseModel saveOneMockInfo(@RequestBody MockInfoReq req){
        ResponseModel responseModel = new ResponseModel();
        MockInfo mockInfo = mockInfoDao.getOneMockInfo(req.getInterfaceName(),req.getFunctionName(),req.getParameterTypes());
        if (ObjectUtils.isNotEmpty(mockInfo)){
            responseModel.setCode(ErrorCode.ERROR_CODE_30227.getCode());
            responseModel.setMsg(ErrorCode.ERROR_CODE_30227.getMsg());
            return responseModel;
        }
        try {
            boolean success = mockInfoDao.saveOneMockInfo(req.getInterfaceName(),req.getFunctionName(),req.getParameterTypes(),req.getResponse(),req.getDubboGroup(),req.getVersion());
            if (!success){
                responseModel.setCode(ErrorCode.ERROR_CODE_30226.getCode());
                responseModel.setMsg(ErrorCode.ERROR_CODE_30226.getMsg());
            }
            return responseModel;
        }catch (Exception e) {
            log.error("save mock info error: {}, {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.ERROR_CODE_30224.getCode(), e.getMessage());

        }
    }

    @PostMapping("/removeOne")
    public ResponseModel saveOrUpdateMockInfo(@RequestBody MockInfoReq req){
        ResponseModel responseModel = new ResponseModel();
        try {
            boolean success = mockInfoDao.removeOneMock(req.getInterfaceName(),req.getFunctionName(),req.getParameterTypes());
            if (!success){
                responseModel.setDesc("删除对象不存在");
            }
            return responseModel;
        }catch (Exception e){
            log.error("remove one mock info error: {}, {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.ERROR_CODE_30228.getCode(), e.getMessage());
        }

    }


}
