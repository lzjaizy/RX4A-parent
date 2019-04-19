package com.rx.authPlat.tenant.controller;

import com.rx.authPlat.tenant.dao.PostDataMapper;

import com.rx.authPlat.tenant.entity.PostDatasEntity;
import com.rx.utils.ResponseMessage;
import com.rx.utils.StateDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @PackageName com.rx.authPlat.tenant.controller
 * @ClassName postDataController
 * @Description TODO
 * @Author MengQingJun
 * @Date 2019/4/17
 * @Version 2.0
 **/
@Controller
@RequestMapping("/postData")
public class postDataController {
    @Autowired
    private PostDataMapper postDataMapper;
    @ResponseBody
    @RequestMapping(value = "/postToSystem",method = RequestMethod.POST)
    public ResponseMessage postData(@RequestBody List<PostDatasEntity> contextData){


        System.out.println(contextData);
        int i=postDataMapper.postData(contextData.get(contextData.size()-1));
        if (i == 0) {
            return new ResponseMessage().setCode(StateDict.FAILCODE_NOFOUND).setMessage(StateDict.FAIL_NOFOUND).setData(i);
        }
        return new ResponseMessage().setCode(StateDict.SUCCESSCODE).setMessage(StateDict.SUCCESS).setData(i);
    }

}
