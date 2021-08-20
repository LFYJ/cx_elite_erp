package com.erp.controller;



import com.erp.common.ExecuteResultState;
import com.erp.common.JsonResult;
import com.erp.config.IdGenerator;
import com.erp.entity.personnel.User;
import com.erp.service.personnel.UserService;
import com.erp.service.RedisUtils;

import com.erp.util.DesUtil;
import com.erp.util.Jwutil;
import com.erp.util.SendMessageUtil;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * 登录
 */

@Controller
/*@RequestMapping(value = "/later")*/
public class LaterController {
    //id 生成策略
    @Resource
    private IdGenerator idGenerator;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UserService userService;




    //获取短信验证码信息
    @RequestMapping(value = "/later/short/message",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult goShortMessage( @RequestParam("phone") String phone){

        //验证手机号是否已存在
        User user=userService.selectByUser(new User().setPhone(phone).setUserState(1));
        if(user!=null){

            String yanzhengam = SendMessageUtil.getRandomCode(6);
            System.out.println("随机生成验证吗   "+yanzhengam);

            //Integer x= SendMessageUtil.send(phone,"验证码："+yanzhengam);
           /* if(x==1 && redisUtils.set(phone,yanzhengam,60L, TimeUnit.MINUTES)){ */

            if(redisUtils.set(phone,yanzhengam,60L, TimeUnit.MINUTES)){

                return new JsonResult( false, ExecuteResultState.SUCCEED,"发送成功");
            }else {
                return new JsonResult( false, ExecuteResultState.FAILURE,"发送失败");
            }


        }
        return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FORMAT,"手机号不存在");


    }

    //验证手机短信登录
    @RequestMapping(value = "/later/login/message",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult isShortMessage( @RequestParam("phone") String phone,
                                      @RequestParam("verify") String verify,
                                      HttpServletRequest httpServletRequest){

        //验证手机号是否已存在
        User user=userService.selectByUser(new User().setPhone(phone).setUserState(1));
        if(user!=null){
            //redis验证手机号登录
            if (verify.equals(redisUtils.get(phone))){
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("user",user);

                redisUtils.set(user.getUserId().toString(),Jwutil.getIP(httpServletRequest));

                return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
            }
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"验证码或手机号错误");

    }


    /**
     * 验证用户名密码登录信息
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/later/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestParam("username") String username ,
                            @RequestParam("password") String password,
                            HttpServletRequest httpServletRequest){
        //redisUtils.set("time","测试缓存失效时间",10L, TimeUnit.MINUTES);

        User user= userService.selectByUser(
                new User().setPassword(DesUtil.getEncryptString(password)).setJobNumber(
                        Long.parseLong(username)).setUserState(1));

        if(user!=null){
            //储存到session
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user",user);
            redisUtils.set(user.getUserId().toString(),Jwutil.getIP(httpServletRequest));


            return new JsonResult( false, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"用户名密码错误");

    }


    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "/later/goindex",method = RequestMethod.GET)
    public String index(){
        return  "/index";
    }

    /**
     * main
     * @return
     */
    @RequestMapping(value = "/later/welcome",method = RequestMethod.GET)
    public String welcome(){

        return  "/welcome";
    }





    /**
     * 跳转到人员列表
     * @return
     */
    @RequestMapping("/later/staff/list")
    public String stafflist(){
        return  "/staff/staff_list";
    }




    /**
     * 人事主页加载
     * @return
     */
    @RequestMapping(value = "/later/renshi/main",method = RequestMethod.GET)
    public String renshimain(Model model){

        List<User> userlist =userService.usermain();
        model.addAttribute("userlist",userlist );
        return  "/personnel/renshi_main";
    }

}
