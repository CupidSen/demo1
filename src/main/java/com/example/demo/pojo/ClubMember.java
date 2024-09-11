package com.example.demo.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * 社团成员类
 */
@Data
public class ClubMember {
    private Integer id;
    private LocalDate time;
    private String name;
    private String sex;
    private String major;
    private String wechar;
    private String email;
    private String auditstatus;
}
