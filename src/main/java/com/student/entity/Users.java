package com.student.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Users {
    private Integer id;

    private String name;

    private String sex;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birth;

    private Integer salary;

    private Integer detep;

    private String imgurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getDetep() {
        return detep;
    }

    public void setDetep(Integer detep) {
        this.detep = detep;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }
}