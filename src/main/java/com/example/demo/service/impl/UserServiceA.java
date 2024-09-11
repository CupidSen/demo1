package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceA implements UserService {
    @Autowired
    public UserMapper userMapper;
    @Autowired
    public EmailService emailService;
    public Result listUser(Users user){
        int x = userMapper.userzcs(user);
        if(x == 0){
            Yzm yzm = new Yzm();
            yzm.setEmail(user.getEmail());
            yzm.setVerification(user.getVerification());
            int y = userMapper.yzmzcs(yzm);
            if(y != 0){
                userMapper.userzc(user);
                return Result.success();
            }else {
                return Result.error("验证码错误");
            }
        } else {
            return Result.error("用户名已存在");
        }
    }

    @Override
    public Result userDl(Users user) {
        int x = userMapper.userdl(user);
        System.out.println(x);
        if (x == 1){
            Users y = userMapper.userdls(user);
            Integer j = 100000;
            y.setId(y.getId()+j);
            return Result.success(y);
        }else {
            return Result.error("账号或密码错误");
        }

    }

    @Override
    public Result pkgx(Engling engling) {
        int x = userMapper.pksc(engling);
        int y = userMapper.pkgx(engling);
        if (x <= 4&&y == 4){
            return Result.success();
        }else {
            return Result.error("运行异常");
        }
    }

    @Override
    public Result pkcx(String uid,String pid) {
        Engling engling = new Engling();
        engling.setUid(uid);
        engling.setPid(pid);
        List<Engling> x = userMapper.pkcx(engling);
        return Result.success(x);
    }
    public Result sendEmail(EmailRequest emailRequest) {
        Random random = new Random();
        // 生成一个6位数的随机数字
        int randomNumber = random.nextInt(900000) + 100000;
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), "您的验证码为："+randomNumber);
        Yzm yzm = new Yzm();
        yzm.setEmail(emailRequest.getTo());
        yzm.setVerification(String.valueOf(randomNumber));
        userMapper.yzmdl(yzm);
        // 创建一个新线程来延时执行代码
        Thread delayedThread = new Thread(() -> {
            try {
                Thread.sleep(60000*5); // 休眠2秒
                userMapper.yzmdelete(yzm);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });
        delayedThread.start(); // 启动新线程
        return Result.success();
    }
    /**
     * 社团成员申请
     */
    public Result MemberApplication(ClubMember clubMember){
        userMapper.MemberApplication(clubMember);
        return Result.success();
    }
    /**
     * 查询社团成员申请列表
     */
    public Result QueryApplicantMember(){
        List<ClubMember> x = userMapper.QueryApplicantMember();
        return Result.success(x);
    }
    /**
     * 通过或拒绝审核操作
     */
    public Result ReviewCheck(Putyayaya putyayaya){
        userMapper.ReviewCheck(putyayaya);
        return Result.success();
    }
    /**
     * 申请通过添加到成员列表
     */
    public Result AddMember(ClubMember clubMember){
        userMapper.AddMember(clubMember);
        return Result.success();
    }
    /**
     * 查询成员列表
     */
    public Result MemberQuery(){
        List<ClubMember> x = userMapper.MemberQuery();
        return Result.success(x);
    }

}