package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiAll {
    @Autowired
    public UserService userService;

    @Autowired
    public UserMapper userMapper;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/userRegister")
    public Result userRegister(@RequestBody Users user){
        Result x =  userService.listUser(user);
        return x;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/userLoign")
    public Result userLoign(@RequestBody Users user){
            Result users = userService.userDl(user);
            return users;
    }

    /**
     * 更新英语列表
     * @param engling
     * @return
     */
    @PostMapping("/pkUpdate")
    public Result pkUpdate(@RequestBody Engling engling) {
        Result x = userService.pkgx(engling);
        return x;
    }

    /**
     * 清除英语列表
     * @return
     */
    @GetMapping("/pkDelete")
    public Result pkDelete(){
        int x = userMapper.pkqk();
        if (x >= 0){
            return Result.success();
        }else {
            return Result.error("错误");
        }
    }

    /**
     * 查询英语列表
     * @param uid
     * @param pid
     * @return
     */
    @GetMapping("/pkSearch")
    public Result pkSearch(String uid,String pid){
        Result x = userService.pkcx(uid,pid);
        return Result.success(x);
    }

    /**
     * 获取邮箱验证码
     * @param emailRequest
     * @return
     */
    @PostMapping("/send-email")
    public Result sendEmail(@RequestBody EmailRequest emailRequest) {
        userService.sendEmail(emailRequest);
        return Result.success();
    }
    /**
     * 社团成员申请
     */
    @PostMapping("/memberapplication")
    public Result MemberApplication(@RequestBody ClubMember clubMember){
        userService.MemberApplication(clubMember);
        return Result.success();
    }
    /**
     * 查询社团成员申请列表
     */
    @GetMapping("/queryapplicantmember")
    public Result QueryApplicantMember(){
        Result x = userService.QueryApplicantMember();
        return x;
    }
    /**
     * 通过或拒绝审核操作
     */
    @PutMapping("/reviewcheck")
    public Result ReviewCheck(@RequestBody Putyayaya putyayaya){
        userService.ReviewCheck(putyayaya);
        return Result.success();
    }
    /**
     * 申请通过添加到成员列表
     */
    @PostMapping("addmember")
    public Result AddMember(@RequestBody ClubMember clubMember){
        userService.AddMember(clubMember);
        return Result.success();
    }
    /**
     * 查询成员列表
     */
    @GetMapping("/memberqery")
    public Result MemberQuery(){
        Result x = userService.MemberQuery();
        return x;
    }
}
