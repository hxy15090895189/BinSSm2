package com.student.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PageBean {

    //查询条件
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startBirth;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endBirth;

    private Integer detepId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartBirth() {
        return startBirth;
    }

    public void setStartBirth(Date startBirth) {
        this.startBirth = startBirth;
    }

    public Date getEndBirth() {
        return endBirth;
    }

    public void setEndBirth(Date endBirth) {
        this.endBirth = endBirth;
    }

    public Integer getDetepId() {
        return detepId;
    }

    public void setDetepId(Integer detepId) {
        this.detepId = detepId;
    }
}
