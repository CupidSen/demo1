package com.example.demo.service;

import com.example.demo.pojo.*;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {
    public Result listUser(Users user);

    public Result userDl(Users user);

    public Result pkgx(Engling engling);

    public Result pkcx(String uid, String pid);

    public Result sendEmail(EmailRequest emailRequest);
    public Result MemberApplication(ClubMember clubMember);
    public Result QueryApplicantMember();
    public Result ReviewCheck(Putyayaya putyayaya);
    public Result AddMember(ClubMember clubMember);
    public Result MemberQuery();
}
