package com.erp.controller;

import com.erp.common.ExecuteResultState;
import com.erp.common.JsonResult;
import com.erp.config.IdGenerator;
import com.erp.entity.personnel.*;
import com.erp.service.personnel.*;
import com.erp.util.FileUtil;

import com.erp.util.DesUtil;
import com.erp.util.Jwutil;
import com.erp.util.WordUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 人事系统
 */
@Controller
@RequestMapping(value = "/later")
public class PersonnelController {

    //id 生成策略
    @Resource
    private IdGenerator idGenerator;
    @Resource
    private UserService userService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PositionService positionService;
    @Resource
    private GroupingService groupingService;

    @Resource
    private LeaveService leaveService;
    @Resource
    private ReceiveService receiveService;

    @Resource
    private MobilityService mobilityService;

    @Resource
    private RewardPunishService rewardPunishService;

    @Resource
    private SalaryChangeService salaryChangeService;


    @Resource
    HttpServletRequest request;

    @Resource
    private ContractService contractService;


    @Resource
    private DormitoryService dormitoryService;


    /**
     * 跳转到人事主页
     * @return
     */
    @RequestMapping(value = "/personnel",method = RequestMethod.GET)
    public String index(){


        return  "/personnel/personnel";
    }


    /**
     * 查询所有员工档案
     * @return
     */
    @RequestMapping(value = "/user/all",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult userall( @RequestParam(value = "state" , defaultValue = "1") int state){
        //查询所有用户信息
        List<User> listuser =userService.selectByListUseByDepartmentr(new User().setUserState(state));
        return new JsonResult( listuser, ExecuteResultState.SUCCEED,"成功");
    }


    /**
     * 查看员工档案
     * @return
     */
    @RequestMapping(value = "/employee/list",method = RequestMethod.GET)
    public String employee(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                           @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                           @RequestParam(value = "departmentId", defaultValue="" )  String departmentId,
                           @RequestParam(value = "username", defaultValue="" )  String username,
                           @RequestParam(value = "projectTeam", defaultValue="0" )  String projectTeam,
                           @RequestParam(value = "state", defaultValue="0" )  int state,
                           @RequestParam(value = "positionId", defaultValue="0" )  String positionId,
                           Model model){
        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数
        //查询用户信息
        User user=new User();
        if(!departmentId.equals("")){
            user.setDepartmentId(Long.valueOf(departmentId));
        }
        if(!positionId.equals("")){
            user.setPositionId(Long.valueOf(positionId));
        }
        if(!username.equals("")){
            user.setUserName(username);
        }
        if(!projectTeam.equals("")){
            user.setProjectTeam(Long.valueOf(projectTeam));
        }

        if(state!=0){
            user.setUserState(state);
        }

        List<User> listuser =userService.selectByListUseByDepartmentr(user);
        //数据分页
        PageInfo pageInfo=new PageInfo(listuser);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("listUser",listuser);


        //查询部门信息
        List<Department> listDepartment =departmentService.selectByListDepartment(new Department());
        model.addAttribute("listDepartment",listDepartment );
        model.addAttribute("departmentId",departmentId );

        //查询职位
        List<Grouping> listGrouping =groupingService.selectByListGroupingBypid(new Grouping());
        model.addAttribute("listGrouping",listGrouping );
        model.addAttribute("projectTeam",projectTeam );

        //查询岗位
        List<Position> listPosition =positionService.selectByListPositionBypid(new Position());
        model.addAttribute("listPosition",listPosition );
        model.addAttribute("positionId",positionId );



        model.addAttribute("username",username );
        model.addAttribute("state",state );

        return  "/personnel/employee_info";
    }

   //图片上传功能
    @RequestMapping(value = "/employee/uploadfiles",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadfiles(@RequestParam("imgs") MultipartFile imgs) throws Exception{
        String fileName = imgs.getOriginalFilename();
        //设置文件上传路径
        try {
            //获取图片在服务器地址下
            String fangdi= request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort()+"/imgupload/";
            String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
            FileUtil.uploadFile(imgs.getBytes(), filePath, fileName);

            //获取图片在项目路径下的地址
            String basePath=request.getSession().getServletContext().getRealPath("/");

            FileUtil.uploadFile(imgs.getBytes(), basePath, fileName);

            Map<String,String> map=new HashMap<>();
            map.put("file",fangdi+fileName);
            map.put("name","imgupload/"+fileName);

            return new JsonResult( map, ExecuteResultState.SUCCEED,"成功");
        } catch (Exception e) {
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

    }



    /**
     * 跳转添加人员信息
     * @return
     */
    @RequestMapping(value = "/personnel/goadd",method = RequestMethod.GET)
    public String personnelGoadd(Model model){

        //查询部门信息
        List<Department> listDepartment =departmentService.selectByListDepartment(new Department());

        model.addAttribute("listDepartment",listDepartment );

        //查询职位信息
        List<Position> listPosition =positionService.selectByListPositionBypid(new Position());
        model.addAttribute("listPosition",listPosition );


        //查询组别
        List<Grouping> listGrouping =groupingService.selectByListGroupingBypid(new Grouping());
        model.addAttribute("listGrouping",listGrouping );

        return  "/personnel/personnel_add";
    }


    /**
     * 跳转更新人员信息
     * @return
     */
    @RequestMapping(value = "/user/goupdate",method = RequestMethod.GET)
    public String update(@RequestParam("userId") String userId,
                         Model model){

        User  user =userService.selectByUserByDepartmentr(new User().setUserId(Long.valueOf(userId)));
        model.addAttribute("user",user );

        //查询部门信息
        List<Department> listDepartment =departmentService.selectByListDepartment(new Department());

        model.addAttribute("listDepartment",listDepartment );

        //查询职位信息
        List<Position> listPosition =positionService.selectByListPositionBypid(new Position());
        model.addAttribute("listPosition",listPosition );


        //查询组别
        List<Grouping> listGrouping =groupingService.selectByListGroupingBypid(new Grouping());
        model.addAttribute("listGrouping",listGrouping );


        //查询当前用户权限信息
        RightsRelation rightsRelation  =userService.selectByidrightsRelation(
                new RightsRelation().setUserId(Long.valueOf(userId)));
        if (rightsRelation!=null){
            model.addAttribute("authorityName",rightsRelation.getAuthorityName() );
        }else {
            model.addAttribute("authorityName","");
        }



        return  "/personnel/modification";
    }



    /**
     * 员工信息添加
     * @return
     */
    @RequestMapping(value = "/personnel/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult personneadd( @RequestBody Map<String ,String> map ) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        User user =new User();
        user.setAge(Integer.parseInt(map.get("age")));//年龄
        user.setBirthdate(simpleDateFormat.parse(map.get("birthdate")));//出生时间
        user.setSpecialty(map.get("specialty"));//专业
        user.setBank(map.get("bank"));//开户行

        user.setUserId(idGenerator.nextId());//编号
        user.setJobNumber(idGenerator.nextId());//工号
        user.setGender(map.get("gender"));//性别
        user.setUserName(map.get("userName"));//用户名
        user.setPassword(DesUtil.getEncryptString("123456"));//密码
        user.setPositionId(Long.valueOf(map.get("positionId")));//职位
        user.setEducation(map.get("education"));//学历
        user.setIdentityArd(map.get("identityArd"));//身份证
        user.setNation(map.get("nation"));//民族
        user.setPhone(map.get("phone"));//本人手机号
        user.setEmergencyUser(map.get("emergencyUser"));//紧急联系人
        user.setEmergencyPhone(map.get("emergencyPhone"));//紧急联系人电话
        user.setSocialSecurity(map.get("socialSecurity"));//社保
        user.setBankCard(map.get("bankCard"));//银行卡账号
        user.setHukouType(map.get("hukouType"));//户口类型

        user.setFilialityFineCard(map.get("filialityFineCard"));//孝善银行卡账号
        user.setFilialityFineBank(map.get("filialityFineBank"));//孝善开户行

        user.setPresentAddress(map.get("presentAddress"));//现住址
        user.setAddress(map.get("address"));//户籍地址
        user.setSchoolBy(map.get("schoolBy"));//毕业院校
        user.setMarriage(map.get("marriage"));//婚姻情况
        user.setUserState(Integer.parseInt(map.get("userState")));
        user.setUserImg(map.get("userImg"));//员工照片
        user.setOccupational(map.get("occupational"));//occupational
        user.setEntryDate(simpleDateFormat.parse(map.get("entryDate")));//入职日期
        user.setDepartmentId(Long.valueOf(map.get("departmentId")));//部门ID
        //转正日期
        if(map.get("positiveDates")!=null &&!(map.get("positiveDates").equals(""))){
            user.setPositiveDates(simpleDateFormat.parse(map.get("positiveDates")));
        }
        //离职日期
        if(map.get("dimissionDate")!=null &&!(map.get("dimissionDate").equals(""))){
            user.setPositiveDates(simpleDateFormat.parse(map.get("dimissionDate")));
        }

        user.setEducationUndergo(map.get("educationUndergo"));//教育经历
        user.setProjectTeam(Long.valueOf(map.get("projectTeam")));//所属项目组



         if(userService.addUser(user,map.get("arrbox"))){
             return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
         }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }


    /**
     * 查询职位信息
     * @return
     */
    @RequestMapping(value = "/position/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult positionlist(@RequestParam("pid") String pid){

        //查询职位信息
        List<Position> listPosition =positionService.selectByListPositionBypid(new Position().setPid(Long.valueOf(pid)));

        //查询组别
        List<Grouping> listGrouping =groupingService.selectByListGroupingBypid(new Grouping().setPid(Long.valueOf(pid)));

        Map map=new HashMap();
        map.put("listGrouping",listGrouping);
        map.put("listPosition",listPosition);

        return new JsonResult( map, ExecuteResultState.SUCCEED,"成功");
    }


    /**
     * 跳转查看界面
     * @return
     */

    @RequestMapping(value = "/user/examine",method = RequestMethod.GET)
    public String selposition(@RequestParam("userId") String userId,
                              Model model){
        User  user =userService.selectByUserByDepartmentr(new User().setUserId(Long.valueOf(userId)));
        user.setPassword(DesUtil.getDecryptString(user.getPassword()));
        model.addAttribute("user",user );

        //查询部门信息
        List<Department> listDepartment =departmentService.selectByListDepartment(new Department());

        model.addAttribute("listDepartment",listDepartment );

        //查询职位信息
        List<Position> listPosition =positionService.selectByListPositionBypid(new Position());
        model.addAttribute("listPosition",listPosition );


        //查询组别
        List<Grouping> listGrouping =groupingService.selectByListGroupingBypid(new Grouping());
        model.addAttribute("listGrouping",listGrouping );

        //查询调动记录
        List<Mobility> listMobility =mobilityService.personnelTransferList(new Mobility().setUserId(Long.valueOf(userId)) );
        model.addAttribute("listMobility",listMobility );


        //查询当前用户权限信息
        RightsRelation rightsRelation  =userService.selectByidrightsRelation(
                new RightsRelation().setUserId(Long.valueOf(userId)));
        if (rightsRelation!=null){
            model.addAttribute("authorityName",rightsRelation.getAuthorityName() );
        }else {
            model.addAttribute("authorityName","");
        }



        return  "/personnel/examine";
    }




    /**
    * 删除用户
    * @return
    */
    @RequestMapping(value = "/user/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteuser(@RequestParam("id") String id) throws Exception {

        if(id==null  || id.equals("")){
            return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FAILURE,"失败");
        }
        //删除用户
           if(userService.deleteuser(Long.valueOf(id))){
               return new JsonResult( false, ExecuteResultState.SUCCEED,"成功");
           }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }


    /**
     * 更新用户信息
     * @return JsonResult
     */
    @RequestMapping(value = "/user/upd",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult userupd(@RequestBody Map<String ,String> map ) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM

        User user =new User();
        user.setBank(map.get("bank"));
        user.setAge(Integer.parseInt(map.get("age")));
        user.setBirthdate(simpleDateFormat.parse(map.get("birthdate")));//出生时间

        user.setSpecialty(map.get("specialty"));
        user.setUserId(Long.valueOf(map.get("userId")));
        user.setGender(map.get("gender"));
        user.setUserName(map.get("userName"));
        user.setPositionId(Long.valueOf(map.get("positionId")));
        user.setEducation(map.get("education"));
        user.setIdentityArd(map.get("identityArd"));
        user.setNation(map.get("nation"));
        user.setPhone(map.get("phone"));
        user.setEmergencyUser(map.get("emergencyUser"));
        user.setEmergencyPhone(map.get("emergencyPhone"));
        user.setSocialSecurity(map.get("socialSecurity"));
        user.setBankCard(map.get("bankCard"));
        user.setHukouType(map.get("hukouType"));
        user.setFilialityFineCard(map.get("filialityFineCard"));
        user.setFilialityFineBank(map.get("filialityFineBank"));

        user.setPresentAddress(map.get("presentAddress"));
        user.setAddress(map.get("address"));
        user.setSchoolBy(map.get("schoolBy"));
        user.setMarriage(map.get("marriage"));
        user.setUserImg(map.get("userImg"));
        user.setOccupational(map.get("occupational"));
        user.setDepartmentId(Long.valueOf(map.get("departmentId")));
        user.setUserState(Integer.parseInt(map.get("userState")));
        user.setEntryDate(simpleDateFormat.parse(map.get("entryDate")));

        //转正日期
        if(map.get("positiveDates")!=null &&!(map.get("positiveDates").equals(""))){
            user.setPositiveDates(simpleDateFormat.parse(map.get("positiveDates")));
        }
        //离职日期
        if(map.get("dimissionDate")!=null &&!(map.get("dimissionDate").equals(""))){
            user.setPositiveDates(simpleDateFormat.parse(map.get("dimissionDate")));
        }

        user.setEducationUndergo(map.get("educationUndergo"));//教育经历
        user.setProjectTeam(Long.valueOf(map.get("projectTeam")));//所属项目组

        if(userService.updUser(user,map.get("arrbox"))){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }

    /*==========请假管理=============*/
    /**
     * 跳转到请假管理界面
     * @return
     */
    @RequestMapping(value = "/leave/list",method = RequestMethod.GET)
    public String leavelist(@RequestParam(value = "yf" , defaultValue = "0") int yf,
                            @RequestParam(value = "page" , defaultValue = "1") Integer page,
                            @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                            @RequestParam(value = "username", defaultValue="" )  String username,
                            Model model)  {

        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数
        Map<String, Object> map = new HashMap<String, Object>();
        if(yf!=0){
            Calendar a=Calendar.getInstance();
            map.put("nf", a.get(Calendar.YEAR));
            map.put("yf", yf);
        }
        if(!username.equals("")){
            map.put("username", username);
        }
        //查询请假列表
        List<Leave> listLeavet =leaveService.selectByListLeave(map);
        //数据分页
        PageInfo pageInfo=new PageInfo(listLeavet);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("listLeavet",listLeavet);

        model.addAttribute("yf", yf);
        model.addAttribute("username", username);


        return  "/personnel/leave_list";
    }



    /**
     * 删除请假信息
     * @return
     */
    @RequestMapping(value = "/leave/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteleave(@RequestParam("id") String id) throws Exception {

        if(id==null  || id.equals("")){
            return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FAILURE,"失败");
        }
        //删除不请假信息
        if(userService.deleteuser(Long.valueOf(id))){
            return new JsonResult( false, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }


    /*=============员工领取记录============*/

    /**
     * 跳转到员工领取记录列表
     * @return
     */
    @RequestMapping(value = "/receive/list",method = RequestMethod.GET)
    public String getsupplies(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                              @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                              @RequestParam(value = "departmentId", defaultValue="" )  String departmentId,
                              @RequestParam(value = "username", defaultValue="" )  String username,
                              Model model){
        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数

        Map<String, Object> map = new HashMap<String, Object>();
        if(!departmentId.equals("")){
            map.put("departmentId", departmentId);
        }
        if(!username.equals("")){
            map.put("username", username);
        }
        //查询领取信息
        List<Receive> listReceive= receiveService.selectByReceive(map);

        //数据分页
        PageInfo pageInfo=new PageInfo(listReceive);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("listReceive",listReceive);
        //查询部门信息
        List<Department> listDepartment =departmentService.selectByListDepartment(new Department());
        model.addAttribute("listDepartment",listDepartment );
        //部门筛选
        model.addAttribute("departmentId",departmentId );
        //搜索
        model.addAttribute("username",username );

        return "/personnel/receive_list";
    }




    /**
     * 跳转到添加员工领取记录界面
     * @return
     */
    @RequestMapping(value = "/receive/goadd",method = RequestMethod.GET)
    public String receivegoadd(Model model){

        return  "/personnel/receive_add";
    }

    /**
     * 跳转到更新员工领取记录界面
     * @return
     */
    @RequestMapping(value = "/receive/goupt",method = RequestMethod.GET)
    public String receivegoupt(@RequestParam( "id" ) String id,Model model){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        Receive receive =  receiveService.selectByReceivebyid(map);
        model.addAttribute("receive",receive );
        return  "/personnel/receive_upt";
    }




    /**
     * 更新领取记录
     * @return
     */
    @RequestMapping(value = "/receive/upt",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult receiveupt( @RequestParam( "id" ) String id,
                                  @RequestParam( "goods" ) String goods){

        if(id==null||id==""||goods==null||goods==""){
            return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FAILURE,"数据校验失败");
        }

        Receive receive=new   Receive();
        receive.setId(Long.valueOf(id));
        receive.setGoods(goods);

        if(receiveService.uptReceive(receive)>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }




    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping(value = "/receive/sel",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult receiveusersel( @RequestParam( "wno" ) String wno){

        if(wno==null||wno==""){
            return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FAILURE,"数据校验失败");
        }

        User user= userService.selectByUser(new User().setJobNumber(Long.valueOf(wno)));
            if(user!=null){
            return new JsonResult( user, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }

    /**
     * 添加领取记录
     * @return
     */
    @RequestMapping(value = "/receive/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult receiveadd( @RequestParam( "wno" ) String wno,
                                  @RequestParam( "goods" ) String goods){

        if(wno==null||wno==""||goods==null||goods==""){
            return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FAILURE,"数据校验失败");
        }

        Receive receive=new   Receive();
        receive.setId(idGenerator.nextId());
        receive.setCreatedate(new Date());
        receive.setGoods(goods);
        receive.setState(1);
        receive.setWno(Long.valueOf(wno));
        receive.setUserId(userService.selectByUser(new User().setJobNumber(Long.valueOf(wno))).getUserId());
        if(receiveService.addReceive(receive)>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }



    /**
     * 删除领取记录
     * @return
     */
    @RequestMapping(value = "/receive/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deletereceive(@RequestParam("id") String id){

        if(id==null  || id.equals("")){
            return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FAILURE,"失败");
        }
        //删除领取记录
        if(receiveService.deletereceive(Long.valueOf(id))){
            return new JsonResult( false, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }


    /*==============部门==============*/


    /**
     * 跳转查询部门信息
     * @return
     */
    @RequestMapping(value = "/parameters/list",method = RequestMethod.GET)
    public String parameterslist(Model model){


        //查询部门信息
        List<Department> listDepartment =departmentService.selectByListDepartment(new Department());
        model.addAttribute("listDepartment",listDepartment);

        //查询部门信息
        List<Position> listPosition =positionService.selectByListPositionBypid(new Position());

        model.addAttribute("listPosition",listPosition);

        //查询职务信息
        List<Grouping> groupingList= groupingService.selectByListGroupingBypid(new Grouping());

        model.addAttribute("listGrouping",groupingList );



        return  "/personnel/parameters_list";
    }





    /**
     * 跳转查询部门信息
     * @return
     */
    @RequestMapping(value = "/department/list",method = RequestMethod.GET)
    public String departmentlist(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                                 Model model){
        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数
        //查询部门信息
        List<Department> listDepartment =departmentService.selectByListDepartment(new Department());
        //数据分页
        PageInfo pageInfo=new PageInfo(listDepartment);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("listDepartment",listDepartment);
        return  "/personnel/department_list";
    }


    /**
     * 跳转添加部门界面
     * @return
     */
    @RequestMapping(value = "/department/goadd",method = RequestMethod.GET)
    public String departmentgoadd(Model model){


        return  "/personnel/department_add";
    }

    /**
     * 跳转更新部门界面
     * @return
     */
    @RequestMapping(value = "/department/goupt",method = RequestMethod.GET)
    public String departmentgoupt(@RequestParam("id") Long id,Model model){

        Department department =departmentService.selectByDepartment(new Department().setDepartmentId(id));
        model.addAttribute("department",department);

        User user= userService.selectByUser(new User().setUserId(department.getCreateUserId()));
        model.addAttribute("user",user);
        return  "/personnel/department_upd";
    }


    /**
     * 添加部门信息
     * @return
     */
    @RequestMapping(value = "/department/upt",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult departmentupt(@RequestParam("wno") String wno,
                                    @RequestParam("departmentName" )  String departmentName,
                                    @RequestParam("departmentId" )  String departmentId){


        User userby= userService.selectByUser(new User().setJobNumber(Long.valueOf(wno)));

        int x= departmentService.uptDepartment(new  Department().setDepartmentId(Long.valueOf(departmentId))
                .setDepartmentName(departmentName)
                .setCreateUserId(userby.getUserId())
                .setSupervisor(userby.getUserName()));

        //更新部门信息
        if(x>0){
            return new JsonResult( false, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");


    }


    /**
     * 添加部门信息
     * @return
     */
    @RequestMapping(value = "/department/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult departmentadd(Model model,
                                @RequestParam("wno") String wno,
                                @RequestParam("departmentName" )  String departmentName,
                                    HttpServletRequest httpServletRequest){


        //获取当前登录用户信息
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");

        User userby= userService.selectByUser(new User().setJobNumber(Long.valueOf(wno)));

        int x= departmentService.addDepartment(new  Department().setDepartmentId(idGenerator.nextId())
                                                         .setDepartmentName(departmentName)
                                                         .setCreateUserId(userby.getUserId())
                                                         .setSupervisor(userby.getUserName())
                                                         .setCreateUserId(userby.getUserId())
                                                         .setDepartmentCreateDate(new Date())
                                                         .setDepartmentState(1)
                                                         .setCreatorName(user.getUserName()));


        if(x>0){
            return new JsonResult( false, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");


    }


    /**
     * 删除部门
     * @return
     */
    @RequestMapping(value = "/department/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deletedepartment(@RequestParam("id") String id){

        if(id==null  || id.equals("")){
            return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FAILURE,"失败");
        }
        //查询此部门下是否还用户



        if(departmentService.deletedepartment(Long.valueOf(id))>0){
            return new JsonResult( false, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }


    /*================职位=============*/
    /**
     * 跳转到职位列表详情
     * @return
     */
    @RequestMapping(value = "/position/postlist",method = RequestMethod.GET)
    public String departmentlist(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                                 @RequestParam("id") String id , Model model){

        //查询职位信息
        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数
        //查询部门信息
        List<Position> listPosition =positionService.selectByListPositionBypid(new Position().setPid(Long.valueOf(id)));
        //数据分页
        PageInfo pageInfo=new PageInfo(listPosition);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("listPosition",listPosition );

        model.addAttribute("pid",id );

        return  "/personnel/position_list";
    }


    /**
     * 跳转到职位添界面
     * @return
     */
    @RequestMapping(value = "/position/goadd",method = RequestMethod.GET)
    public String positiongoadd( Model model){

        return  "/personnel/position_add";
    }
    /**
     * 跳转到职位更新界面
     * @return
     */
    @RequestMapping(value = "/position/goupt",method = RequestMethod.GET)
    public String positiongoupt(@RequestParam("id") String id , Model model){

        Position position= positionService.selectByPosition(new Position().setPositionId(Long.valueOf(id)));

        model.addAttribute("position",position );

        return  "/personnel/position_upt";
    }

    /**
     * 更新职位信息
     * @return
     */
    @RequestMapping(value = "/position/upt",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult positionupt(@RequestParam("id") String id ,
                                  @RequestParam("jobTitle" ) String jobTitle){


        if(id==""|| id==null || jobTitle==""|| jobTitle==null){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x =positionService.uptPosition(new Position().setPositionId(Long.valueOf(id))
                .setJobTitle(jobTitle));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }


    /**
     * 添加职位
     * @return
     */
    @RequestMapping(value = "/position/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult positionadd(@RequestParam("jobTitle" ) String jobTitle,
                                  HttpServletRequest httpServletRequest){


        if( jobTitle==""|| jobTitle==null){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        //获取当前登录用户信息
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int  x =positionService.addPosition(new Position()
                                                  .setPositionId(idGenerator.nextId())
                                                  .setJobTitle(jobTitle)
                                                  .setPositionCreatorName(user.getUserName())
                                                  .setPositionState(1).setCreateDate(new Date()));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }

    /**
     * 删除职位
     * @return
     */
    @RequestMapping(value = "/position/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult positiondelete(@RequestParam("id") String id ){
        if(id==null || id ==""){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x= positionService.positiondelete(Long.valueOf(id));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }


    /*==================职务======================*/


    /**
     * 跳转到职务列表详情
     * @return
     */
    @RequestMapping(value = "/grouping/list",method = RequestMethod.GET)
    public String groupinglist(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                                 @RequestParam("id") String id , Model model){

        //查询职务信息
        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数
        //查询职务信息
        List<Grouping> listGrouping =groupingService.selectByListGroupingBypid(new Grouping().setPid(Long.valueOf(id)));
        //数据分页
        PageInfo pageInfo=new PageInfo(listGrouping);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("listGrouping",listGrouping );
        model.addAttribute("pid",id );

        return  "/personnel/grouping_list";
    }


    /**
     * 跳转到职务添界面
     * @return
     */
    @RequestMapping(value = "/grouping/goadd",method = RequestMethod.GET)
    public String groupinggoadd( Model model){



        return  "/personnel/grouping_add";
    }
    /**
     * 跳转到职务更新界面
     * @return
     */
    @RequestMapping(value = "/grouping/goupt",method = RequestMethod.GET)
    public String groupinggoupt(@RequestParam("id") String id , Model model){

        Grouping grouping= groupingService.selectByGrouping(new Grouping().setGroupingId(Long.valueOf(id)));

        model.addAttribute("grouping",grouping );

        return  "/personnel/grouping_upt";
    }

    /**
     * 更新职务信息
     * @return
     */
    @RequestMapping(value = "/grouping/upt",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult groupingupt(@RequestParam("id") String id ,
                                  @RequestParam("groupingName" ) String groupingName){

        if(id==""|| id==null || groupingName==""|| groupingName==null){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x =groupingService.uptGrouping(new Grouping().setGroupingId(Long.valueOf(id))
                .setGroupingName(groupingName));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }


    /**
     * 添加职务
     * @return
     */
    @RequestMapping(value = "/grouping/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult groupingadd(@RequestParam("groupingName" ) String groupingName,
                                  HttpServletRequest httpServletRequest){


        if( groupingName==""|| groupingName==null){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        //获取当前登录用户信息
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        int  x =groupingService.addGrouping(new Grouping()
        .setGroupingId(idGenerator.nextId())
                .setGroupingName(groupingName)
                .setPositionCreatorName(user.getUserName()).setPositionState(1).setCreateDate(new Date()));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }

    /**
     * 删除职务
     * @return
     */
    @RequestMapping(value = "/grouping/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult groupingdelete(@RequestParam("id") String id ){
        if(id==null || id ==""){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x= groupingService.groupingdelete(Long.valueOf(id));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }





   //职位变动


    /**
     * 跳转到职位变动
     * @return
     */
    @RequestMapping(value = "/personnel/transfer/list",method = RequestMethod.GET)
    public String personneltransferlist(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                                        @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                                        @RequestParam(value = "type", defaultValue="0" )  int  type,
                                        @RequestParam(value = "username", defaultValue="" )  String username,
                                        Model model){


        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数

        Mobility mobility=new Mobility();
        if(type!=0){
            mobility.setType(type);
        }
        if(!(username.equals(""))){
            mobility.setUsername(username);
        }

        //查询调动记录
        List<Mobility> listMobility =mobilityService.personnelTransferList(mobility);

        //数据分页
        PageInfo pageInfo=new PageInfo(listMobility);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("listMobility",listMobility );

        model.addAttribute("type",type );
        model.addAttribute("username",username );

        return  "/personnel/personnel_transfer_list";
    }

    /**
     * 删除职位变动记录
     * @return
     */
    @RequestMapping(value = "/personnel/transfer/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult personneltransferdelete(@RequestParam("id") String id ){
        if(id==null || id ==""){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x= mobilityService.personneltransferdelete(Long.valueOf(id));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }


    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping(value = "/receive/selbydepartmentr",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult selectByUserByDepartmentr( @RequestParam( "wno" ) String wno)  {

        if(wno==null||wno==""){
            return new JsonResult( false, ExecuteResultState.DATA_VERIFY_FAILURE,"数据校验失败");
        }
        User user= userService.selectByUserByDepartmentr(new User().setJobNumber(Long.valueOf(wno)));
        if(user!=null){
            return new JsonResult( user, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
    }

    /**
     *跳转位置调动添加界面
     * @return
     */
    @RequestMapping(value = "/personnel/transfer/goadd",method = RequestMethod.GET)
    public String personneltransfergoadd( Model model){

        List<Department> listDepartment =departmentService.selectByListDepartment(new Department());
        model.addAttribute("listDepartment",listDepartment );

        //查询职位信息
        List<Position> listPosition =positionService.selectByListPositionBypid(new Position());
        model.addAttribute("listPosition",listPosition );

        //查询组别
        List<Grouping> listGrouping =groupingService.selectByListGroupingBypid(new Grouping());
        model.addAttribute("listGrouping",listGrouping );


        return  "/personnel/personnel_transfer_add";
    }

    /**
     * 添加职位变动记录
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/personnel/transfer/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult personneltransferadd( @RequestBody Map<String ,String> map) throws Exception {

        if(map.get("wno")==null||map.get("wno").equals("")){
            return new JsonResult( false, ExecuteResultState.FAILURE,"此用户存在");
        }
        User user =  userService.selectByUserByDepartmentr(new User().setJobNumber(Long.valueOf(map.get("wno"))));
        if (user==null){
            return new JsonResult( false, ExecuteResultState.FAILURE,"此用户存在");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM

        Mobility mobility=new Mobility();
        mobility.setId(idGenerator.nextId());
        mobility.setUserId(user.getUserId());
        mobility.setUsername(map.get("username"));
        mobility.setType(Integer.parseInt(map.get("type")));
        mobility.setBirthdate(simpleDateFormat.parse(map.get("birthdate")));//出生时间
        mobility.setCreateDate(new Date());
        mobility.setDepartmentId(user.getDepartmentId());
        mobility.setDepartmentName(user.getDepartment().getDepartmentName());
        mobility.setGender(map.get("gender"));
        mobility.setIdentityArd(map.get("identityArd"));
        mobility.setPhone(map.get("phone"));
        mobility.setPositionId(user.getPositionId());
        mobility.setPositionName(user.getPosition().getJobTitle());
        mobility.setGroupingId(user.getProjectTeam());
        mobility.setGroupingName(user.getGrouping().getGroupingName());

        mobility.setToDepartmentId(Long.valueOf(map.get("toDepartmentId")));
        mobility.setToDepartmentName(departmentService.selectByDepartment(new Department()
                .setDepartmentId(Long.valueOf(map.get("toDepartmentId")))).getDepartmentName());


        mobility.setToPositionId(Long.valueOf(map.get("toPositionId")));
        mobility.setToPositionName(positionService.selectByPosition(new Position()
                .setPositionId(Long.valueOf(map.get("toPositionId")))).getJobTitle());


        mobility.setToGroupingId(Long.valueOf(map.get("toGroupingId")));
        mobility.setToGroupingName(groupingService.selectByGrouping(new Grouping()
                .setGroupingId(Long.valueOf(map.get("toGroupingId")))).getGroupingName());

        mobility.setTransferData(simpleDateFormat.parse(map.get("transferData")));


        boolean x=  mobilityService.personneltransferadd(mobility,new User().setUserId(user.getUserId())
                                                      .setDepartmentId(Long.valueOf(map.get("toDepartmentId")))
                                                      .setPositionId(Long.valueOf(map.get("toPositionId")))
                                                      .setProjectTeam(Long.valueOf(map.get("toGroupingId"))));

        if(x){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }


    //岗位移动导出文档
    @RequestMapping(value = "/personnel/export", method = RequestMethod.GET)
    public void downloadWord( @RequestParam("id")  String id,
                              HttpServletResponse response) {

        String strDateFormat = "yyyy年MM月dd";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        Mobility mobility=  mobilityService.personnelTransfer(new Mobility().setId(Long.valueOf(id)));
        Map<String, Object> dataMap =  new HashMap<String, Object>();
        dataMap.put("username", mobility.getUsername());
        dataMap.put("gender", mobility.getGender());
        dataMap.put("birthdate",   sdf.format(mobility.getBirthdate()).toString());
        dataMap.put("phone", mobility.getPhone());
        dataMap.put("identityArd", mobility.getIdentityArd());
        dataMap.put("departmentName", mobility.getDepartmentName());
        dataMap.put("positionName", mobility.getPositionName());
        dataMap.put("toDepartmentName", mobility.getToDepartmentName());
        dataMap.put("toPositionName", mobility.getToPositionName());
        dataMap.put("transferData",  sdf.format(mobility.getTransferData()).toString());


        String fileName=mobility.getUsername()+Jwutil.getStringDate().toString()+".doc";
        WordUtils.get(response,dataMap,fileName);

    }

  /*------------奖罚记录------------*/


    /**
     * 跳转到奖罚记录
     * @return
     */
    @RequestMapping(value = "/reward/punish/list",method = RequestMethod.GET)
    public String rewardpunishlist(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                                   @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                                   @RequestParam(value = "type", defaultValue="0" )  int  type,
                                   @RequestParam(value = "username", defaultValue="" )  String username,
                                   Model model){


        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数

        RewardPunish rewardPunish=new RewardPunish();
        if(type!=0){
            rewardPunish.setType(type);
        }
        if(!(username.equals(""))){
            rewardPunish.setUsername(username);
        }
        //查询调动记录
        List<RewardPunish> rewardPunishlist =rewardPunishService.rewardpunishlist(rewardPunish);
        //数据分页
        PageInfo pageInfo=new PageInfo(rewardPunishlist);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("rewardPunishlist",rewardPunishlist );
        model.addAttribute("type",type );
        model.addAttribute("username",username );

        return  "/personnel/reward_punish_list";
    }


    /**
     * 删除记录
     * @return
     */
    @RequestMapping(value = "/reward/punish/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult rewardpunishdelete(@RequestParam("id") String id ){
        if(id==null || id ==""){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x= rewardPunishService.rewardpunishdelete(Long.valueOf(id));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }



    /**
     *跳转到添加奖罚记录
     * @return
     */
    @RequestMapping(value = "/reward/punish/goadd",method = RequestMethod.GET)
    public String rewardpunishgoadd( Model model){


        return  "/personnel/reward_punish_add";
    }

    /**
     * 添加奖罚记录
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/reward/punish/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult rewardpunishadd( @RequestBody Map<String ,String> map) throws Exception {

        if(map.get("wno")==null||map.get("wno").equals("")){
            return new JsonResult( false, ExecuteResultState.FAILURE,"此用户存在");
        }
        User user =  userService.selectByUserByDepartmentr(new User().setJobNumber(Long.valueOf(map.get("wno"))));
        if (user==null){
            return new JsonResult( false, ExecuteResultState.FAILURE,"此用户存在");
        }


        RewardPunish rewardPunish=new RewardPunish();
        rewardPunish.setId(idGenerator.nextId());
        rewardPunish.setCreatedate(new Date());
        rewardPunish.setDepartmentName(map.get("departmentName"));
        rewardPunish.setPositionName(map.get("positionName"));
        rewardPunish.setExplain(map.get("explain"));
        rewardPunish.setMagnitude(Integer.parseInt(map.get("magnitude")));
        rewardPunish.setUserId(user.getUserId());
        rewardPunish.setUsername(map.get("username"));
        rewardPunish.setType(Integer.parseInt(map.get("type")));

        boolean x= rewardPunishService.rewardpunishadd(rewardPunish);
        if(x){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }


   /*============= 调薪==================*/



    /**
     * 跳转到奖罚记录
     * @return
     */
    @RequestMapping(value = "/salary/change/list",method = RequestMethod.GET)
    public String salarychangelist(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                                   @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                                   @RequestParam(value = "type", defaultValue="0" )  int  type,
                                   @RequestParam(value = "username", defaultValue="" )  String username,
                                   Model model){


        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数

        SalaryChange salaryChange=new SalaryChange();

        if(type!=0){
            salaryChange.setType(type);
        }
        if(!(username.equals(""))){
            salaryChange.setUsername(username);
        }

        //查询调动记录
        List<SalaryChange> salaryChangeList =salaryChangeService.salarychangelist(salaryChange);

        //数据分页
        PageInfo pageInfo=new PageInfo(salaryChangeList);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("salaryChangeList",salaryChangeList );

        model.addAttribute("type",type );
        model.addAttribute("username",username );

        return  "/personnel/salary_change_list";

    }

    /**
     * 删除职位变动记录
     * @return
     */
    @RequestMapping(value = "/salary/change/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult salarychangedelete(@RequestParam("id") String id ){
        if(id==null || id ==""){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x= salaryChangeService.salarychangedelete(Long.valueOf(id));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }

    /**
     *跳转到添加薪资变动
     * @return
     */
    @RequestMapping(value = "/salary/change/goadd",method = RequestMethod.GET)
    public String rewardpunishgoadd( ){


        return  "/personnel/salary_change_add";
    }



    /**
     * 添加薪资变动记录
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/salary/change/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult salarychangeadd( @RequestBody Map<String ,String> map) throws Exception {

        if(map.get("wno")==null||map.get("wno").equals("")){
            return new JsonResult( false, ExecuteResultState.FAILURE,"此用户存在");
        }
        User user =  userService.selectByUserByDepartmentr(new User().setJobNumber(Long.valueOf(map.get("wno"))));
        if (user==null){
            return new JsonResult( false, ExecuteResultState.FAILURE,"此用户存在");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM

        SalaryChange salaryChange=new SalaryChange();
        salaryChange.setId(idGenerator.nextId());
        salaryChange.setUserId(user.getUserId());
        salaryChange.setUsername(map.get("username"));
        salaryChange.setAdjustmentdata(simpleDateFormat.parse(map.get("adjustmentdata")));
        salaryChange.setCreatedate(new Date());
        salaryChange.setDepartmentName(map.get("departmentName"));
        salaryChange.setEntryTime(simpleDateFormat.parse(map.get("entryTime")));
        salaryChange.setPositionName(map.get("positionName"));
        salaryChange.setGender(map.get("gender"));
        salaryChange.setType(Integer.parseInt(map.get("type")));
        salaryChange.setState(1);

        boolean x= salaryChangeService.salarychangeadd(salaryChange);
        if(x){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }


   /*================= 合同================*/


    /**
     * 跳转合同管理
     * @return
     */
    @RequestMapping(value = "/contract/list",method = RequestMethod.GET)
    public String contractlist(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                                   @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                                   @RequestParam(value = "name", defaultValue="" )  String name,
                                   @RequestParam(value = "state", defaultValue="" )  String state,
                                   @RequestParam(value = "signedDate", defaultValue="" )  String signedDate,
                                   @RequestParam(value = "expireDate", defaultValue="" )  String expireDate,
                                   Model model) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM

        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数

        Contract contract=new Contract();
        //查询合同记录
        List<Contract> contractList=new ArrayList<>();

        if(!(name.equals(""))){
            contract.setName(name);
        }
        if(!(state.equals(""))){
            contract.setState(Integer.valueOf(state));
        }

        //时间筛选
        if(!(signedDate.equals("")) && !(expireDate.equals(""))){
            contract.setExpireDate(simpleDateFormat.parse(expireDate));
            contract.setSignedDate(simpleDateFormat.parse(signedDate));
        }

        //查询合同记录
        contractList =contractService.contractlist(contract);

        //数据分页
        PageInfo pageInfo=new PageInfo(contractList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("contractList",contractList);

        model.addAttribute("name",name );
        model.addAttribute("state",state );


        model.addAttribute("expireDate",expireDate );
        model.addAttribute("signedDate",signedDate );



        return  "/personnel/contract_list";
    }



    /**
     * 删除合同
     * @return
     */
    @RequestMapping(value = "/contract/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult contractdelete(@RequestParam("id") String id ){
        if(id==null || id ==""){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x= contractService.contractdelete(Long.valueOf(id));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }

    /**
     *跳转到添加合同
     * @return
     */
    @RequestMapping(value = "/contract/goadd",method = RequestMethod.GET)
    public String contractgoadd( ){




        return  "/personnel/contract_add";
    }

    /**
     * 添加合同信息
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/contract/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult contractadd( @RequestBody Map<String ,String> map) throws Exception {

        if(map.get("wno")==null||map.get("wno").equals("")){
            return new JsonResult( false, ExecuteResultState.FAILURE,"此用户存在");
        }
        User user =  userService.selectByUserByDepartmentr(new User().setJobNumber(Long.valueOf(map.get("wno"))));
        if (user==null){
            return new JsonResult( false, ExecuteResultState.FAILURE,"此用户存在");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM

        Contract   contract=new Contract();
        contract.setId(idGenerator.nextId());
        contract.setName(map.get("name"));
        contract.setUserId(user.getUserId());
        contract.setWno(Long.valueOf(map.get("wno")));
        contract.setExpireDate(simpleDateFormat.parse(map.get("expireDate")));
        contract.setSignedDate(simpleDateFormat.parse(map.get("signedDate")));
        contract.setState(1);

        boolean x= contractService.contractadd(contract);
        if(x){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }



    /**
     *跳转到添加合同
     * @return
     */
    @RequestMapping(value = "/contract/goupt",method = RequestMethod.GET)
    public String contractgoupt(@RequestParam("id") String id  , Model model){

        Contract contract= contractService.contractbyid(new Contract().setId(Long.valueOf(id)));

        model.addAttribute("contract",contract );
        return  "/personnel/contract_upt";
    }





    /**
     * 修改合同
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/contract/upt",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult contractupt( @RequestBody Map<String ,String> map) throws Exception {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Contract   contract=new Contract();
        contract.setId(Long.valueOf(map.get("id")));
        contract.setExpireDate(simpleDateFormat.parse(map.get("expireDate")));
        contract.setSignedDate(simpleDateFormat.parse(map.get("signedDate")));

        boolean x= contractService.contractupt(contract);
        if(x){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }

    /*=================宿舍管理===============*/

    /**
     * 跳转宿舍管理
     * @return
     */
    @RequestMapping(value = "/dormitory/list",method = RequestMethod.GET)
    public String dormitorylist(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                               @RequestParam(value = "pageSize", defaultValue="10" )  Integer pageSize,
                               @RequestParam(value = "dormitoryno", defaultValue="0" )  int dormitoryno,
                               Model model){


        PageHelper.startPage(page,pageSize);//分页起始码以及每页页数
        Dormitory dormitory=new Dormitory();
        if(dormitoryno!=0){
            dormitory.setDormitoryno(dormitoryno);
            model.addAttribute("dormitoryno",dormitoryno );
        }else {
            model.addAttribute("dormitoryno","" );
        }

        //查询调动记录
        List<Dormitory> dormitoryList =dormitoryService.dormitorylist(dormitory);

        //数据分页
        PageInfo pageInfo=new PageInfo(dormitoryList);
        model.addAttribute("pageInfo",pageInfo );
        model.addAttribute("dormitoryList",dormitoryList );

        return  "/personnel/dormitory_list";
    }




    /**
     * 删除宿舍
     * @return
     */
    @RequestMapping(value = "/dormitory/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult dormitorydelete(@RequestParam("id") String id ){
        if(id==null || id ==""){
            return new JsonResult( false, ExecuteResultState.FAILURE,"失败");
        }

        int  x= dormitoryService.dormitorydelete(Long.valueOf(id));
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }
        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }





    /**
     *跳转到添加宿舍
     * @return
     */
    @RequestMapping(value = "/dormitory/goadd",method = RequestMethod.GET)
    public String dormitorygoadd(){


        return  "/personnel/dormitory_add";
    }

    /**
     * 添加宿舍信息
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/dormitory/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult dormitoryadd( @RequestBody Dormitory dormitory)  {

        dormitory.setId(idGenerator.nextId());
        dormitory.setCreateDate(new Date());
        dormitory.setState(1);

        boolean x= dormitoryService.dormitoryadd(dormitory);
        if(x){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }


    /**
     *跳转到修改宿舍信息
     * @return
     */
    @RequestMapping(value = "/dormitory/goupt",method = RequestMethod.GET)
    public String dormitorygoupt(@RequestParam("id") String id,Model model){

        Dormitory dormitory= dormitoryService.dormitory(new Dormitory().setId(Long.valueOf(id)));
        model.addAttribute("dormitory",dormitory );

        return  "/personnel/dormitory_upt";
    }

    //修改宿舍信息
    @RequestMapping(value = "/dormitory/upt",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult dormitoryupt( @RequestBody Dormitory dormitory)  {

        int x= dormitoryService.dormitoryupt(dormitory);
        if(x>0){
            return new JsonResult( true, ExecuteResultState.SUCCEED,"成功");
        }

        return new JsonResult( false, ExecuteResultState.FAILURE,"失败");

    }
}
