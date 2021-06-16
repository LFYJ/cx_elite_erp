package com.erp.controller;



import com.erp.config.IdGenerator;
import com.erp.service.impl.RedisUtils;
import com.erp.util.SendMessageUtil;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * 后台控制层
 */

@Controller
@RequestMapping(value = "/later")
public class LaterController {
    //id 生成策略
    @Resource
    private IdGenerator idGenerator;

    @Resource
    private RedisUtils redisUtils;



    //获取短信验证码信息
    @RequestMapping("/short/message")
    public String goShortMessage(){

        String yanzhengam = SendMessageUtil.getRandomCode(6);

        SendMessageUtil.send("13752547419","验证码："+yanzhengam);

        redisUtils.set("验证码",yanzhengam,60L, TimeUnit.MINUTES);

        return  "/index";
    }


    @RequestMapping("/login")
    public String login(){
        //redisUtils.set("time","测试缓存失效时间",10L, TimeUnit.MINUTES);
        return  "/index";
    }

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(){
        return  "/welcome";
    }

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("/backups")
    public String backups(){
        return  "/prototype/backups";
    }
    /**
     * 跳转到人员列表
     * @return
     */
    @RequestMapping("/staff/list")
    public String stafflist(){
        return  "/staff/staff_list";
    }





}
