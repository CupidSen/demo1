package com.example.demo.mapper;


import com.example.demo.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {
    //添加用户
    @Insert("insert into user(email, password, name) value (#{email},#{password},#{name})")
    public void userzc(Users user);
    //判断用户是否存在
    @Select("select count(*) from user where email = #{email}")
    public int userzcs(Users user);
    @Select("select id,email,name from user where email = #{email} and password = #{password}")
    public Users userdls(Users users);
    //登录验证
    @Select("select count(*) from user where email = #{email} and password = #{password}")
    public int userdl(Users user);
    //更新英语列表数据
    @Insert("insert into pkym(uid,pid,id,word,chinese) SELECT #{uid},#{pid},id,word,chinese FROM dict ORDER BY RAND() LIMIT 4;")
    public int pkgx(Engling engling);
    //更新前删除
    @Delete("DELETE FROM pkym WHERE uid = #{uid};")
    public int pksc(Engling engling);
    //删除英语列表
    @Delete("DELETE FROM pkym")
    public int pkqk();
    //查询英语列表
    @Select("select uid,pid,id,word,chinese from pkym where uid = #{uid} and pid = #{pid}")
    public List<Engling> pkcx(Engling engling);
    //验证码放入数据库
    @Insert("insert into yzm(email, verification) value (#{email},#{verification});")
    public void yzmdl(Yzm yzm);
    //清除验证码
    @Delete("delete from yzm where email = #{email};")
    public void yzmdelete(Yzm yzm);
    @Select("select count(*) from yzm where email = #{email} and verification = #{verification}")
    public int yzmzcs(Yzm yzm);
    /**
     * 社团成员申请
     */
    @Insert("insert into ClubMemberPreselection(id,time,name,sex,major,wechar,email) value (#{id},#{time},#{name},#{sex},#{major},#{wechar},#{email})")
    public void MemberApplication(ClubMember clubMember);
    /**
     * 查询社团成员申请列表
     */
    @Select("select * from ClubMemberPreselection")
    public List<ClubMember> QueryApplicantMember();
    /**
     * 通过或拒绝审核操作
     */
    @Select("update ClubMemberPreselection set auditstatus = #{x} where id = #{y};")
    public void ReviewCheck(Putyayaya putyayaya);
    /**
     * 申请通过添加到成员列表
     */
    @Insert("insert into ClubMember(time,name,sex,major,wechar,email) value (#{time},#{name},#{sex},#{major},#{wechar},#{email})")
    public void AddMember(ClubMember clubMember);
    /**
     * 查询成员列表
     */
    @Select("select * from ClubMember")
    public List<ClubMember> MemberQuery();
    /**
     * 查询成员列表
     */
    @Select("select * from ClubMember")
    public List<ClubMember> MemberQuery();
}
