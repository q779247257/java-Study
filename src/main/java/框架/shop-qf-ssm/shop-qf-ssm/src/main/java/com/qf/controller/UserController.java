package com.qf.controller;

import com.qf.pojo.SsmUser;
import com.qf.service.SsmUserService;
import com.qf.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Author: 轩轩
 * @Date: 2020/3/6 15:57
 * @description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired SsmUserService userService;

    /**
     * 登录验证
     * @param username 账户 xuanxuan
     * @param password 密码 123456
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(String username, String password, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        Integer resulInt = userService.login(username,password);
        //账号和密码有null
        if (resulInt == 3){
            modelAndView.setViewName("/error");
            modelAndView.addObject("message","账号和密码不能为空");
            modelAndView.addObject("title","请重新登录");
        }
        if (resulInt == 4){
            modelAndView.setViewName("/error");
            modelAndView.addObject("message","账户不存在");
            modelAndView.addObject("title","请先注册账户");
        }
        if (resulInt == 5){
            modelAndView.setViewName("/error");
            modelAndView.addObject("message","您的密码错误，请重新输入密码");
            modelAndView.addObject("title","密码错误");
        }
        if (resulInt == -1){
            modelAndView.setViewName("/error");
            modelAndView.addObject("message","发生了未知错误，请稍后再试");
            modelAndView.addObject("title","错误");
        }
        if (resulInt == 0){
            SsmUser userByName =  userService.selectByName(username);
            modelAndView.setViewName("system/main");
            //获取sessionid
            String sessionId=request.getSession().getId();
            modelAndView.addObject("sessionId",sessionId);
            modelAndView.addObject("user",userByName);
            request.getSession().setAttribute("userInfo",userByName.getUserCall()); //用户信息放入session
        }
        return modelAndView;
    }

    /**
     * 查询所有的用户
     * @param page 当前页
     * @param limit 每页展示数量
     */
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public ModelAndView selectAll(@RequestParam(defaultValue = "1") Integer page , @RequestParam(defaultValue = "10") Integer limit) {

        System.out.println("触发查询方法--------------------");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userManager");
//        PageHelper.startPage(page, limit);
        List<SsmUser> userList =  userService.selectAll();
        modelAndView.addObject("userList",userList);

        return modelAndView;
    }
    //注册用户
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView register(SsmUser user , @RequestParam("file")MultipartFile uploadFile){
        ModelAndView modelAndView = new ModelAndView("/error");
        System.out.println("user:"+user);
        String originalFilename = uploadFile.getOriginalFilename();
        System.out.println("fileName:"+originalFilename);

        //检测账户是否重复
        SsmUser userByName = userService.selectByName(user.getUsername());
        System.out.println("user is null : "+ userByName);
        //账户已存在，无法注册该账户
        if (userByName != null){
            modelAndView.setViewName("/error");
            modelAndView.addObject("message","您注册的账户已经存在，请重新注册");
            modelAndView.addObject( "title","注册错误");
            return modelAndView;
        }
        //检测上传文件是否为null
        if (!uploadFile.isEmpty()){
            //上传文件夹
            String filePath = OssUtil.picOSS(uploadFile);
            //设置用户头像
            user.setUserHeadPortraitUrl(filePath);
        }
        //开始注册
         userService.innert(user);

        modelAndView.setViewName("/error");
        modelAndView.addObject("message","您已成功注册账户，请使用您的账户登录");
        modelAndView.addObject( "title","注册成功");
       return modelAndView;
    }

    /**
     * 根据id查询用户
     * @param id 用户id
     */
    @ResponseBody
    @RequestMapping(value = "/selectUserById" , method = RequestMethod.POST)
    public SsmUser selectUserById(Integer id){
        SsmUser user = userService.selectById(id);
        System.out.println("返回数据："+user);
        return user;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String editUser(SsmUser user , @RequestParam("file")MultipartFile uploadFile){
        System.out.println("修改的user为："+user);
        System.out.println("文件为："+uploadFile.getOriginalFilename());
        //文件上传不为null
        if (!uploadFile.isEmpty()){
            //上传文件，获得url路径
            String fileUrl = OssUtil.picOSS(uploadFile);
            user.setUserHeadPortraitUrl(fileUrl);
        }

       Integer resultNum =  userService.updateUserById(user);
        return "redirect:/user/selectAll";
    }
}
