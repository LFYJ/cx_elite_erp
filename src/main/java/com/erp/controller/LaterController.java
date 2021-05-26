package com.erp.controller;



import com.erp.config.IdGenerator;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 后台控制层
 */

@Controller
@RequestMapping(value = "/later")
public class LaterController {
    //id 生成策略
    @Resource
    private IdGenerator idGenerator;



    @RequestMapping("/login")
    public String login(){
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
        return  "/staff/list";
    }



}
